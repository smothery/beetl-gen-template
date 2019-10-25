package panzer.vor.generate.easyopen;

import org.beetl.core.Template;
import org.beetl.sql.core.JavaType;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.db.ColDesc;
import org.beetl.sql.core.db.TableDesc;
import org.beetl.sql.core.kit.GenKit;
import org.beetl.sql.ext.gen.CodeGen;
import org.beetl.sql.ext.gen.GenConfig;
import org.beetl.sql.ext.gen.SourceGen;

import java.io.IOException;
import java.util.*;

/**
 * @outhor luozc
 * @create 2018-10-11
 */
public class ListRequestParamsCodeGen implements CodeGen {
    private String template;
    private SQLManager sm;

    public ListRequestParamsCodeGen(SQLManager sm) {
        this.sm = sm;
        this.template = (new GenConfig()).getTemplate("/easyopen/ext_list_params.btl");
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    @Override
    public void genCode(String entityPkg, String entityClass, TableDesc tableDesc, GenConfig config, boolean isDisplay) {
        if (entityPkg == null || entityPkg.length() == 0) {
            entityPkg = "panzer.vor.generate";
        }

        String ext = null;
        if (config.getBaseClass() != null) {
            ext = config.getBaseClass();
        }

        Set<String> cols = tableDesc.getCols();
        List<Map> attrs = new ArrayList();
        Iterator var6 = cols.iterator();

        String code;

        while (var6.hasNext()) {
            code = (String) var6.next();
            ColDesc desc = tableDesc.getColDesc(code);
            Map attr = new HashMap();
            attr.put("comment", desc.remark);
            String attrName = this.sm.getNc().getPropertyName((Class) null, desc.colName);
            attr.put("name", attrName);
            attr.put("methodName", this.getMethodName(attrName));
            boolean isKey = tableDesc.getIdNames().contains(desc.colName);
            attr.put("isKey", Boolean.valueOf(isKey));
            String type = JavaType.getType(Integer.valueOf(desc.sqlType), desc.size, desc.digit);
            if (config.isPreferBigDecimal() && type.equals("Double")) {
                type = "BigDecimal";
            }

            if (config.isPreferDate() && type.equals("Timestamp")) {
                type = "Date";
            }

            attr.put("type", type);
            attr.put("desc", desc);
            attrs.add(attr);
        }

        int var10000 = config.getPropertyOrder();
        GenConfig var10001 = config;
        if (var10000 == 1) {
            Collections.sort(attrs, new Comparator<Map>() {
                public int compare(Map o1, Map o2) {
                    ColDesc desc1 = (ColDesc) o1.get("desc");
                    ColDesc desc2 = (ColDesc) o2.get("desc");
                    int score1 = this.score(desc1);
                    int score2 = this.score(desc2);
                    return score1 == score2 ? desc1.colName.compareTo(desc2.colName) : score2 - score1;
                }

                private int score(ColDesc desc) {
                    return tableDesc.getIdNames().contains(desc.colName) ? 99 : (JavaType.isInteger(Integer.valueOf(desc.sqlType)) ? 9 : (JavaType.isDateType(Integer.valueOf(desc.sqlType)) ? -9 : 0));
                }
            });
        }

        String className = entityClass + "ListRequestParams";

        Template template = SourceGen.getGt().getTemplate(this.template);
        template.binding("attrs", attrs);
        template.binding("ext", ext);
        template.binding("package", entityPkg);
        template.binding("entityClass", entityClass);
        template.binding("className", className);
        template.binding("imports", "import panzer.vor.*;" + SourceGen.CR);
        template.binding("comment", tableDesc.getRemark());
        template.binding("catalog", tableDesc.getCatalog());
        template.binding("implSerializable", Boolean.TRUE);
        String mapperCode = template.render();
        if (isDisplay) {
            System.out.println(mapperCode);
        } else {
            try {
                SourceGen.saveSourceFile(GenKit.getJavaSRCPath(), entityPkg, className, mapperCode);
            } catch (IOException var11) {
                throw new RuntimeException("ListRequestParams代码生成失败", var11);
            }
        }
    }

    private String getMethodName(String name) {
        if (name.length() == 1) {
            return name.toUpperCase();
        } else {
            char ch1 = name.charAt(0);
            char ch2 = name.charAt(1);
            if (Character.isLowerCase(ch1) && Character.isUpperCase(ch2)) {
                return name;
            } else if (Character.isUpperCase(ch1) && Character.isUpperCase(ch2)) {
                return name;
            } else {
                char upper = Character.toUpperCase(ch1);
                return upper + name.substring(1);
            }
        }
    }
}
