package panzer.vor.generate.easyopen;

import org.beetl.core.Template;
import org.beetl.sql.core.db.TableDesc;
import org.beetl.sql.core.kit.GenKit;
import org.beetl.sql.ext.gen.CodeGen;
import org.beetl.sql.ext.gen.GenConfig;
import org.beetl.sql.ext.gen.SourceGen;

import java.io.IOException;

/**
 * @outhor luozc
 * @create 2018-10-11
 */
public class ServiceCodeGen implements CodeGen {
    private String template;

    public ServiceCodeGen() {
        this.template = (new GenConfig()).getTemplate("/easyopen/ext_service.btl");
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

        String className = entityClass + "Service";

        Template template = SourceGen.getGt().getTemplate(this.template);
        String mapperClass = entityClass;
        template.binding("package", entityPkg);
        template.binding("entityClass", entityClass);
        template.binding("className", className);
        String mapperHead = "import " + "panzer.vor.*;" + SourceGen.CR;
        template.binding("imports", mapperHead);
        String mapperCode = template.render();
        if (isDisplay) {
            System.out.println(mapperCode);
        } else {
            try {
                SourceGen.saveSourceFile(GenKit.getJavaSRCPath(), entityPkg, className, mapperCode);
            } catch (IOException var11) {
                throw new RuntimeException("Service代码生成失败", var11);
            }
        }
    }
}
