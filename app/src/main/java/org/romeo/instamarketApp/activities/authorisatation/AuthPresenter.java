package org.romeo.instamarketApp.activities.authorisatation;

import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.romeo.instamarketApp.R;
import org.romeo.instamarketApp.model.CashPreferences;
import org.romeo.instamarketApp.model.ServerWorker;

public class AuthPresenter {
    private final AuthActivity activity;
    public static final String DEFAULT_AUTH_VALUE = "-1";
    private final CashPreferences preferences;
    private final ServerWorker serverWorker;

    public AuthPresenter(AuthActivity activity, CashPreferences preferences) {
        serverWorker = new ServerWorker();
        this.activity = activity;
        this.preferences = preferences;
    }

    public void init() {
        String username = preferences.getUsername();
        String password = preferences.getPassword();

        if (username.equals(DEFAULT_AUTH_VALUE) &&
                password.equals(DEFAULT_AUTH_VALUE))
            activity.initialize();
        else
            activity.loadContentActivity(
                    serverWorker.getInstagramUserFor(username, password));
    }

    public void saveUser(String username, String password) {
        InstagramUser user = serverWorker.getInstagramUserFor(username, password);

        if (user != null) {
            preferences.saveUser(username, password);
            activity.loadContentActivity(user);
        } else
            activity.showError(R.string.incorrect_auth_data);
    }
}
