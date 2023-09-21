import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.junit.JUnit4CitrusSupport;
import org.junit.Test;

public class TestHelperHTTP extends JUnit4CitrusSupport {
    private TestContext context;

    @Test
    @CitrusTest(name = "HTTP Helper")
    public void getTestAction(){ // @CitrusResource JUnit4CitrusTestRunner runner
        this.context = citrus.getCitrusContext().createTestContext();

//        runner.http((action -> action.client("httpHelperClient")
//                        .send()
//                        .get("users/${userId}")));
//
//
//        runner.http((httpActionBuilder -> httpActionBuilder
//                .client("httpHelperClient")
//                .receive()
//                .response(HttpStatus.OK)
//                .messageType(MessageType.JSON)
//                .payload(new GetResponse().getResponseRate(),"objectMapper")));
    }
}
