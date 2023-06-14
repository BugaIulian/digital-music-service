package com.dms.demo.services.smstoken;

public interface SMSTokenService {

    int generateAndReturnToken();

    void saveTokenToDatabase(String userId);
}