package helpers;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;

import com.consol.citrus.junit.spring.JUnit4CitrusSpringSupport;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

public class SqlHelper extends JUnit4CitrusSpringSupport {
    public TestContext context;
    @Autowired
    private DataSource sqlHelper;
    @Test
    @CitrusTest(name = "test db")
    public void getTestActions() {
        this.context = citrus.getCitrusContext().createTestContext();

//        sql(action -> action
//                .dataSource(sqlHelper)
//                .statement("delete from dbo.request_log"));
//
//        query(action -> action
//                .dataSource(sqlHelper)
//                .statement("select client_id from db.offer where offer_id = 2")
//                .extract("client_id","currentId")
//        );

    }

}
