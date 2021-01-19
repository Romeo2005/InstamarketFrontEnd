package org.romeo.instamarketApp.activities.authorisatation;

import android.os.Handler;
import android.os.HandlerThread;

import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.romeo.instamarketApp.R;
import org.romeo.instamarketApp.model.CashPreferences;
import org.romeo.instamarketApp.model.Repository;

public class AuthPresenter {
    private final AuthActivityTemplate activity;
    public static final String DEFAULT_AUTH_VALUE = "-1";
    private final CashPreferences preferences;
    private final Handler userHandler;

    public AuthPresenter(AuthActivityTemplate activity, CashPreferences preferences) {
        this.activity = activity;
        this.preferences = preferences;
        HandlerThread handlerThread = new HandlerThread("USER_HANDLER_THREAD");
        handlerThread.start();
        userHandler = new Handler(handlerThread.getLooper());
    }

    public void init() {
        userHandler.post(() -> {
            String username = preferences.getUsername();
            String password = preferences.getPassword();

            if ((username == null || password == null) ||
                    username.equals(DEFAULT_AUTH_VALUE) &&
                            password.equals(DEFAULT_AUTH_VALUE))

                activity.initialize();
            else
                activity.loadContentActivity(
                        Repository.INSTANCE.getInstagramUserFor(username, password));
        });
    }

    public void saveUser(String username, String password) {
        userHandler.post(() -> {
            InstagramUser user = Repository.INSTANCE.getInstagramUserFor(username, password);

            if (user != null) {
                preferences.saveUser(username, password);
                activity.loadContentActivity(user);
            } else
                activity.showError(R.string.incorrect_auth_data);
        });

    }
}
