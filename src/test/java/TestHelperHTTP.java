import citrus.behaviors.RestHelperBehavior;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.junit.spring.JUnit4CitrusSpringSupport;
import org.junit.Test;

public class TestHelperHTTP extends JUnit4CitrusSpringSupport {
    @Test
    @CitrusTest(name = "HTTP Helper")
    public void RestHelperBehavior() {
        applyBehavior(new RestHelperBehavior());
    }
}
