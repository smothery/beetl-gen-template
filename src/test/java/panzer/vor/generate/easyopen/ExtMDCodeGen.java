package panzer.vor.generate.easyopen;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.NameConversion;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.db.TableDesc;
import org.beetl.sql.core.kit.GenKit;
import org.beetl.sql.core.kit.StringKit;
import org.beetl.sql.ext.gen.GenConfig;
import org.beetl.sql.ext.gen.MDCodeGen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @outhor luozc
 * @create 2018-10-11
 */
public class ExtMDCodeGen extends MDCodeGen {

    public ExtMDCodeGen() {
        super.setMapperTemplate((new GenConfig()).getTemplate("/easyopen/ext_md.btl"));
    }


    public void genCode(String entityClass, TableDesc tableDesc, GenConfig config, SQLManager sqlManager) {
        final NameConversion nc = sqlManager.getNc();

        String fileName = StringKit.toLowerCaseFirstOne(nc.getClassName(entityClass));
        if (config.getIgnorePrefix() != null && !config.getIgnorePrefix().trim().equals("")) {
            fileName = fileName.replaceFirst(StringKit.toLowerCaseFirstOne(config.getIgnorePrefix()), "");
            fileName = StringKit.toLowerCaseFirstOne(fileName);
        }

        String path = ((ClasspathLoader) sqlManager.getSqlLoader()).getSqlRoot();
        String target = GenKit.getJavaResourcePath() + "/" + path + "/" + fileName + ".md";
        FileWriter writer = null;
        try {
            writer = new FileWriter(new File(target));
            super.genCode(sqlManager.getBeetl()
                    , tableDesc
                    , nc
                    , null
                    , writer);
            System.out.println("gen \"" + entityClass + "\" success at " + target);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("MD代码生成失败", e);
        }
    }
}
