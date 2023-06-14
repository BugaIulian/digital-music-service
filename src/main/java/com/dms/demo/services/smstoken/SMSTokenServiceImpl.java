package com.dms.demo.services.smstoken;

import com.dms.demo.exceptions.user.UserNotFoundException;
import com.dms.demo.models.entities.User;
import com.dms.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class SMSTokenServiceImpl implements SMSTokenService {

    private final UserRepository userRepository;
    int generatedToken;

    public SMSTokenServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int generateAndReturnToken() {
        Random randToken = new Random();
        generatedToken = randToken.nextInt(900000) + 100000;
        return generatedToken;
    }

    @Override
    public void saveTokenToDatabase(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User with id: " + userId + " could not be found"));
        user.setSmsToken(generatedToken);
        user.setTokenExpiryTime(LocalDateTime.now().plusMinutes(1));
        userRepository.save(user);
    }
}