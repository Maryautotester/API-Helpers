package citrus.behaviors;

import static com.consol.citrus.ws.actions.SoapActionBuilder.soap;

import citrus.com.dataaccess.webservicesserver.NumberToDollars;
import citrus.com.dataaccess.webservicesserver.NumberToDollarsResponse;
import citrus.features.CustomMarshaller;
import com.consol.citrus.TestActionRunner;
import com.consol.citrus.TestBehavior;
import com.consol.citrus.context.TestContext;
import java.math.BigDecimal;

public class SoapHelperBehavior implements TestBehavior {
    public TestContext context;

    @Override
    public void apply(TestActionRunner testActionRunner) {
        CustomMarshaller<Class<NumberToDollars>> ptxRq = new CustomMarshaller<>();
        CustomMarshaller<Class<NumberToDollarsResponse>> ptxRs = new CustomMarshaller<>();

        testActionRunner.run(soap()
                        .client("soapClientHelper")
                        .send()
                        .message()
                        .body(ptxRq.convert(NumberToDollars.class, getNumberToDollarsRequest(),
                                "http://www.dataaccess.com/webservicesserver/", "NumberToDollars"))
        );

        testActionRunner.run(soap()
                        .client("soapClientHelper")
                        .receive()
                        .message()
                        .body(ptxRs.convert(NumberToDollarsResponse.class, getNumberToDollarsResponse(),
                                "http://www.dataaccess.com/webservicesserver/", "NumberToDollarsResponse"))
        );
    }
    public NumberToDollars getNumberToDollarsRequest() {
        NumberToDollars numberToDollars = new NumberToDollars();
        numberToDollars.setDNum(new BigDecimal("15"));
        return numberToDollars;
    }

    public NumberToDollarsResponse getNumberToDollarsResponse() {
        NumberToDollarsResponse numberToDollarsResponse = new NumberToDollarsResponse();
        numberToDollarsResponse.setNumberToDollarsResult("fifteen dollars");
        return numberToDollarsResponse;
    }




}
