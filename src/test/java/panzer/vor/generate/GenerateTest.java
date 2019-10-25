package panzer.vor.generate;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.db.MetadataManager;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.gen.GenConfig;
import org.beetl.sql.ext.gen.GenFilter;
import org.beetl.sql.ext.gen.MapperCodeGen;
import org.junit.Test;
import panzer.vor.generate.base.BaseJUnit4SpringContextTests;
import panzer.vor.generate.easyopen.*;

import java.util.Iterator;
import java.util.Set;

/**
 * @outhor luozc
 * @create 2018-10-12
 */
public class GenerateTest extends BaseJUnit4SpringContextTests {
    @Test
    public void easyopen_gen_one_console() throws Exception {
        String table = "salesorder_log";
        String pkg = "panzer.vor.genarate.easyopen";
        String srcPath = "tech/vor/genarate/easyopen";
        GenConfig config = new GenConfig("/easyopen/ext_pojo.btl");
        config.setPreferDate(false);
        sqlManager.genPojoCodeToConsole(table, config);
        sqlManager.genSQLTemplateToConsole(table);
    }

    @Test
    public void easyopen_gen_all() throws Exception {
        GenConfig config = new GenConfig("/easyopen/ext_pojo.btl");
        MapperCodeGen mapperCodeGen = new MapperCodeGen();
        mapperCodeGen.setMapperTemplate((new GenConfig()).getTemplate("/easyopen/ext_mapper.btl"));
        config.codeGens.add(mapperCodeGen);
        config.codeGens.add(new DTOCodeGen(sqlManager));
        config.codeGens.add(new ApiCodeGen());
        config.codeGens.add(new ServiceCodeGen());
        config.codeGens.add(new ServiceImplCodeGen());
        config.codeGens.add(new ListRequestParamsCodeGen(sqlManager));
        config.codeGens.add(new CreateRequestParamsCodeGen(sqlManager));
        config.codeGens.add(new UpdateRequestParamsCodeGen(sqlManager));

        ClasspathLoader loader = new ClasspathLoader("/all/sql/easyopen", new MySqlStyle());
        sqlManager.setSqlLoader(loader);
        sqlManager.genALL("panzer.vor.generate.easyopen", config, new GenFilter() {
            @Override
            public boolean accept(String tableName) {
                return true;
            }
        });

        /**
         * 重新按自定义模版覆盖md
         */

        MetadataManager metaDataManager = sqlManager.getMetaDataManager();
        Set<String> tables = metaDataManager.allTable();
        Iterator var5 = tables.iterator();

        GenFilter filter = new GenFilter() {
            @Override
            public boolean accept(String s) {
                return true;
            }
        };

        while (true) {
            String table;
            do {
                if (!var5.hasNext()) {
                    return;
                }

                table = (String) var5.next();
                table = metaDataManager.getTable(table).getName();
            } while (filter != null && !filter.accept(table));

            try {
                ExtMDCodeGen mdCodeGen = new ExtMDCodeGen();
                mdCodeGen.genCode(table, metaDataManager.getTable(table), config, sqlManager);
            } catch (Exception var8) {
                var8.printStackTrace();
            }
        }
    }

    @Test
    public void test_0() {

    }
}
