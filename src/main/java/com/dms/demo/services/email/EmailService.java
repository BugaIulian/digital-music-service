package com.dms.demo.services.email;

public interface EmailService {

    void sendRegistrationEmail(String registeredEmail, String userName);

    void sendArtistsSuggestionsBasedOnPreferredArtists(String userEmail, String userName);
}