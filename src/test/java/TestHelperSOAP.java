import behaviors.RestHelperBehavior;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.junit.spring.JUnit4CitrusSpringSupport;
import helpers.RestHelper;
import org.junit.Test;

public class TestHelperSOAP extends JUnit4CitrusSpringSupport {

    @Test
    @CitrusTest(name = "SOAP Helper")
    public void SoapHelperBehavior() {
        applyBehavior(new RestHelperBehavior());
    }
}
