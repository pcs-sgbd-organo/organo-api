package bsi.pcs.organo.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class NotificationService {
	
    private static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
    private static final String API_KEY = System.getenv("SENDGRID_API_KEY");

	public void sendEmail(String emailFrom, String emailTo, String textContent, String subject) throws IOException {
	    Email from = new Email(emailFrom);
	    Email to = new Email(emailTo);
	    Content content = new Content("text/plain", textContent);
	    Mail mail = new Mail(from, subject, to, content);

	    SendGrid sg = new SendGrid(API_KEY);
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	    } catch (IOException ex) {
	      throw ex;
	    }
	}	

    public void sendSms(String toPhoneNumber, String text) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber(toPhoneNumber),
                new PhoneNumber("+12542564076"),
                text)
            .create();

        System.out.println(message.getSid());
    }
}
