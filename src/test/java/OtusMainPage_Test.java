import static com.consol.citrus.actions.EchoAction.Builder.echo;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;

import annotations.Driver;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.junit.spring.JUnit4CitrusSpringSupport;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import exceptions.PathEmptyException;
import extensions.UIExtensions;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import pages.OtusMainPage;
import pojo.reqres.GetResponse;

@ExtendWith(UIExtensions.class)
public class OtusMainPage_Test extends JUnit4CitrusSpringSupport {
    @Driver
    public WebDriver driver;

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
        receiveResponse(new GetResponse().getResponseUserList());
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
        receiveResponse(new GetResponse().getResponseRate());
    }
    @Test
    @CitrusTest(name = "Получение информации о курсах")
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

    @org.junit.jupiter.api.Test
    public void filterCourseTest() throws PathEmptyException {
        OtusMainPage mainPage = new OtusMainPage(driver);
        mainPage.open()
                .mainPageLoaded()
                .pageListCoursesShouldBeVisible()
                .clickCourseThumbsByTitle("Специализация Python");
    }
    @org.junit.jupiter.api.Test
    public void clickOnEarliestCourse() throws PathEmptyException {
        OtusMainPage mainPage = new OtusMainPage(driver)
                .open()
                .mainPageLoaded()
                .pageListCoursesShouldBeVisible();
        System.out.println(mainPage.getListCourses());
        String earliestCourse = mainPage.getCourseNameByStartDate(true);
        System.out.println("Раньше всех стартует курс " + earliestCourse);

        mainPage.clickCourseThumbsByTitle(earliestCourse)
                .pageHeaderShouldBeSameAs(earliestCourse);
    }

    @org.junit.jupiter.api.Test
    public void clickOnLatestCourse() throws PathEmptyException {
        OtusMainPage mainPage = new OtusMainPage(driver)
                .open()
                .mainPageLoaded()
                .pageListCoursesShouldBeVisible();
        String course = mainPage.getCourseNameByStartDate(false);
        System.out.println("Позже всех стартует курс " + course);

        mainPage.clickCourseThumbsByTitle(course)
                .pageHeaderShouldBeSameAs(course.replaceAll("Специализация сетевой инженер", "Network Engineer"));
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
    public void receiveResponse(Object obj) {
        run(http()
                .client("restClient")
                .receive()
                .response(HttpStatus.OK)
                .message()
                .body(new ObjectMappingPayloadBuilder(obj, "objectMapper")));

    }

}
