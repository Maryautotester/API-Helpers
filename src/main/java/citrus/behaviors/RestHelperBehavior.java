package citrus.behaviors;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

import com.consol.citrus.TestActionRunner;
import com.consol.citrus.TestBehavior;
import com.consol.citrus.context.TestContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;

public class RestHelperBehavior implements TestBehavior {
    public TestContext context;
    @Override
    public void apply(TestActionRunner testActionRunner) {
        // Request
        testActionRunner.run(http()
                .client("restClientHelper")
                .send()
                .get("users/" + context.getVariable("userId"))
        );

        // Response
        testActionRunner.run(http()
                .client("restClientHelper")
                .receive()
                .response(HttpStatus.OK)
                .message()
                // Validation from file
                .body(new ClassPathResource("json/user2.json"))
        );
    }


}
