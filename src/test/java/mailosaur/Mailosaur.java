package mailosaur;

import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.Messages;
import com.mailosaur.models.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static org.testng.AssertJUnit.assertNotNull;

public class Mailosaur {

    // Available in the API tab of a server
    private String apiKey = "7y3v4ZwchNyWS0JI3hb6bTy3VX4V2FY4";
    private String serverId = "fsxrm2da";


    public Message  setEmail(String email) {

        MailosaurClient mailosaur = new MailosaurClient(apiKey);
        MessageSearchParams params = new MessageSearchParams();
        params.withServer(serverId);

        SearchCriteria criteria = new SearchCriteria();
        criteria.withSentTo(email);

        try {
            Message message = mailosaur.messages().get(params, criteria);
            assertNotNull(message);
            return message;  // Trả về đối tượng Message
        } catch (Exception e) {
            e.printStackTrace();
            return null;  // Trả về null nếu có lỗi xảy ra
        }
    }

    public void getContentFromEmail(Message message, String content) {
        if (message != null && message.text() != null) {
            Pattern pattern = Pattern.compile(Pattern.quote(content));
            Matcher matcher = pattern.matcher(message.text().body());

            if (matcher.find()) {
                String confirmationLine = matcher.group(0);
                System.out.println("Extracted Line: " + confirmationLine);
            } else {
                System.out.println("Confirmation line not found.");
            }
        } else {
            System.out.println("Message or message content is null.");
        }
    }


}
