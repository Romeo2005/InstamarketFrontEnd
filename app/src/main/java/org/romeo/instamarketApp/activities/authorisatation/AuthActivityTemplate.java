package org.romeo.instamarketApp.activities.authorisatation;

import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;

public interface AuthActivityTemplate {
    void loadContentActivity(InstagramUser user);

    void initialize();

    void showError(int incorrect_auth_data);

    void showError(String messageText);
}
