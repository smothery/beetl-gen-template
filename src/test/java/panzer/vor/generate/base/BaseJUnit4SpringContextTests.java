package panzer.vor.generate.base;

import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.PostgresStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import panzer.vor.generate.Application;

//import org.springframework.boot.test.SpringApplicationConfiguration;

@ActiveProfiles({"dev"})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class BaseJUnit4SpringContextTests extends AbstractTransactionalJUnit4SpringContextTests {

    protected SQLManager sqlManager;
    protected static final ConnectionSource source = ConnectionSourceHelper
           .getSimple("com.mysql.jdbc.Driver",
                   "jdbc:mysql://127.0.0.1:3306/mdb?useUnicode=true&characterEncoding=utf8&autoConnect=true&rewriteBatchedStatements=TRUE&allowMultiQueries=true&useSSL=false",
                   "root",
                   "root");

    @Before
    public void before() {
//        DBStyle style = new MySqlStyle();
        DBStyle style = new PostgresStyle();
        // sql语句放在classpagth的/sql 目录下
        SQLLoader loader = new ClasspathLoader("/sql");
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
        UnderlinedNameConversion nc = new UnderlinedNameConversion();
        // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
        sqlManager = new SQLManager(style, loader, source, nc, new Interceptor[]{new DebugInterceptor()});
    }
}
