package helpers;

import static com.consol.citrus.ws.actions.SoapActionBuilder.soap;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.junit.spring.JUnit4CitrusSpringSupport;
import features.CustomMarshaller;
import org.junit.Test;
import webservicesserver.NumberToDollars;
import webservicesserver.NumberToDollarsResponse;

import java.math.BigDecimal;


public class SoapHelper extends JUnit4CitrusSpringSupport {
    public TestContext context;

    @Test
    @CitrusTest(name = "Получение информации о пользователе")
    public void getTestActions() {
        this.context = citrus.getCitrusContext().createTestContext();

        CustomMarshaller<Class<NumberToDollars>> ptxRq = new CustomMarshaller<>();
        CustomMarshaller<Class<NumberToDollarsResponse>> ptxRs = new CustomMarshaller<>();

        run(soap()
                        .client("soapClient")
                        .send()
                        .message()
                        .body(ptxRq.convert(NumberToDollars.class, getNumberToDollarsRequest(),
                                "http://www.dataaccess.com/webservicesserver/", "NumberToDollars"))
//                .body("<NumberToDollars xmlns=\"http://www.dataaccess.com/webservicesserver/\">\n" +
//                        "      <dNum>15</dNum>\n" +
//                        "    </NumberToDollars>")
        );

        run(soap()
                        .client("soapClient")
                        .receive()
                        .message()
                        .body(ptxRs.convert(NumberToDollarsResponse.class, getNumberToDollarsResponse(),
                                "http://www.dataaccess.com/webservicesserver/", "NumberToDollarsResponse"))
//                .body("<?xml version=\"1.0\" encoding=\"utf-8\"?><m:NumberToDollarsResponse xmlns:m=\"http://www.dataaccess.com/webservicesserver/\">\n" +
//                        "      <m:NumberToDollarsResult>fifteen dollars</m:NumberToDollarsResult>\n" +
//                        "    </m:NumberToDollarsResponse>")
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
