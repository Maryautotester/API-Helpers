package tests;

import static com.consol.citrus.actions.EchoAction.Builder.echo;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.junit.spring.JUnit4CitrusSpringSupport;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import pojo.data.UserList;

public class Stub_Test extends JUnit4CitrusSpringSupport {
    private TestContext context;

    @Test
    @CitrusTest(name = "получение информации о всех пользователях")
    public void getUsersAll() {
        this.context = citrus.getCitrusContext().createTestContext();
        // Request
        sendRequest("/users/get/all");
        // Send Response
        sendResponse("{\n"
                + "\"name\":\"Test user\",\n"
                + "\"course\":\"QA\",\n"
                + "\"email\":\"test@test.test\",\n"
                + "\"age\":23\n"
                + "}");
        // Receive Response
        receiveResponse();
    }
    @Test
    @CitrusTest(name = "получение оценки пользователя")
    public void getRate() {
        this.context = citrus.getCitrusContext().createTestContext();
        $(echo("We have userId = " + context.getVariable("userId")));
        $(echo("Property \"userId\" = " + "${userId}"));
        // Request
        sendRequest("/user/get/"+ context.getVariable("userId"));
        // Send Response
        sendResponse("{\n"
                + "\"name\":\"Test user\",\n"
                + "\"score\":78\n"
                + "}");
        // Receive Response
        receiveResponse("json/rate.json");
    }
    @Test
    @CitrusTest(name = "Получение информации окурсах")
    public void getCourseList() {
        this.context = citrus.getCitrusContext().createTestContext();

        // Request
        sendRequest("/course/get/all");
        // Send Response
        sendResponse("[\n"
                + "   {\n"
                + "       \"name\":\"QA java\",\n"
                + "       \"price\":15000\n"
                + "   },\n"
                + "   {\n"
                + "       \"name\":\"Java\",\n"
                + "       \"price\":12000\n"
                + "   }\n"
                + "]"
        );
        // Receive Response
        receiveResponse("json/courses.json");
    }

    public void sendRequest(String endpoint) {
        // Request
        run(http()
                .client("restClient")
                .send()
                .get(endpoint)

                // Separate client and mock
                .fork(true)
        );

        // Mock
        run(http()
                .server("restServer")
                .receive()
                .get(endpoint)
        );
    }
    public void sendResponse(String body) {
        run(http()
                .server("restServer")
                .send()
                .response()
                .message()
                .type("application/json")
                .body(body));
    }
    public void receiveResponse(String file) {
        run(http()
                .client("restClient")
                .receive()
                .response(HttpStatus.OK)
                .message()
                .body(new ClassPathResource(file)));

    }
    public void receiveResponse() {
        run(http()
                .client("restClient")
                .receive()
                .response(HttpStatus.OK)
                .message()
                .body(new ObjectMappingPayloadBuilder(getResponseUserList(), "objectMapper")));

    }
    public UserList getResponseUserList() {
        UserList userList = new UserList();

        userList.setName("Test user");
        userList.setCourse("QA");
        userList.setEmail("test@test.test");
        userList.setAge(23L);

        return userList;

    }

}
