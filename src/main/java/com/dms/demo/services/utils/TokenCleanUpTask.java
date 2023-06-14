package com.dms.demo.services.utils;

import com.dms.demo.models.entities.User;
import com.dms.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class TokenCleanUpTask {

    @Autowired
    private UserRepository userRepository;

    @Scheduled(fixedRate = 60000)
    public void removeExpiredTokens() {

        List<User> usersWithExpiredTokens = userRepository.findAllByTokenExpiryTimeBefore(LocalDateTime.now());

        for (User user:usersWithExpiredTokens) {
            user.setSmsToken(null);
            user.setTokenExpiryTime(null);
            userRepository.save(user);
        }
    }
}