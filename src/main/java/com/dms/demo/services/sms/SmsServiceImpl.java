package com.dms.demo.services.sms;

import com.dms.demo.exceptions.user.UserNotFoundException;
import com.dms.demo.models.entities.User;
import com.dms.demo.repositories.UserRepository;
import com.dms.demo.services.smstoken.SMSTokenService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {

    private final UserRepository userRepository;
    private final SMSTokenService smsTokenService;

    public SmsServiceImpl(UserRepository userRepository, SMSTokenService smsTokenService) {
        this.userRepository = userRepository;
        this.smsTokenService = smsTokenService;
    }

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${phone.number}")
    private String fromPhoneNumber;


    @Override
    public void sendPasswordToken(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " could not be found"));

        Twilio.init(accountSid, authToken);
        Message smsMessage = Message.creator(
                new PhoneNumber(user.getPhoneNumber()),
                new PhoneNumber(fromPhoneNumber),
                "Hi " + user.getFirstName() + " SoundSync here, you've requested a password change. You can use this token: " + smsTokenService.generateAndReturnToken() + " to change your password. The token will be available 1 minute.").create();
    }
}