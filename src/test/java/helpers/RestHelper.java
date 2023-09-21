package helpers;

import com.consol.citrus.TestActionRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.junit.spring.JUnit4CitrusSpringSupport;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

public class RestHelper extends JUnit4CitrusSpringSupport {
    public TestContext context;

    @Test
    @CitrusTest(name = "Получение информации о пользователе")
    public void getTestActions(@CitrusResource TestActionRunner actionRunner) {
        this.context = citrus.getCitrusContext().createTestContext();
        // Request
        run(http()
                .client("restClientHelper")
                .send()
                .get("users/" + context.getVariable("userId"))
        );

        // Response
        run(http()
                .client("restClientHelper")
                .receive()
                .response(HttpStatus.OK)
                .message()
                // Validation from file
                .body(new ClassPathResource("json/user2.json"))
        );
    }
}
