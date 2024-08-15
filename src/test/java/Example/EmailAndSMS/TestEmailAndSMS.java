package Example.EmailAndSMS;

import com.mailosaur.models.*;
import mailosaur.Mailosaur;
import org.testng.annotations.Test;


import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;

public class TestEmailAndSMS {

    Mailosaur mailosaur = new Mailosaur();

    @Test
    public void testExample() {
        Message message = mailosaur.setEmail("expect-leaving@fsxrm2da.mailosaur.net");

        System.out.println("Title: " + message.subject());
        System.out.println("Receiving time: " + message.received());
        System.out.println("Body email: " + message.text().body());
        System.out.println("HTML link: " + message.html().links());

        mailosaur.getContentFromEmail(message, "https://tinhte.vn/account-confirmation/3038017/email?c=BUX_WFXLJCMrPL5l");
    }

}
