package com.dms.demo.services.email;

import com.dms.demo.exceptions.email.SendgridRequestException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Value("${email.from}")
    private String fromEmail;

    @Value("${sendgrid.api.key}")
    private String sgApiKey;

    @Value("${sendgrid.template.id}")
    private String sgTemplateId;

    @Override
    public void sendRegistrationEmail(String registeredEmail, String userName) {

        Mail registrationEmail = setEmailToAndFrom(registeredEmail, userName);
        SendGrid sg = new SendGrid(sgApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(registrationEmail.build());
            Response response = sg.api(request);
            log.info(String.valueOf(response.getStatusCode()));
            log.info(response.getBody());
            log.info(response.getHeaders().toString());
        } catch (IOException e) {
            throw new SendgridRequestException("The request to sendgrid has been denied.");
        }
    }

    @Override
    public void sendArtistsSuggestionsBasedOnPreferredArtists(String userEmail, String userName) {

    }

    private Mail setEmailToAndFrom(String registeredEmail, String userName) {
        Email from = new Email(fromEmail);
        Email to = new Email(registeredEmail);
        Mail mail = new Mail();
        Personalization personalization = new Personalization();
        personalization.addTo(to);
        personalization.addDynamicTemplateData("dynamic_content", userName);
        mail.setFrom(from);
        mail.setTemplateId(sgTemplateId);
        mail.addPersonalization(personalization);
        return mail;
    }
}