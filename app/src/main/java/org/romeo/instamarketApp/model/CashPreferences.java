package org.romeo.instamarketApp.model;

import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import org.romeo.instamarketApp.activities.authorisatation.AuthPresenter;

public class CashPreferences {
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    private final SharedPreferences preferences;

    public CashPreferences(AppCompatActivity activity) {
        this.preferences = activity.getPreferences(AppCompatActivity.MODE_PRIVATE);
    }

    public void saveUser(String username, String password) {
        preferences.edit()
                .putString(USERNAME, username)
                .putString(PASSWORD, password)
                .apply();
    }

    public String getUsername() {
        return preferences
                .getString(USERNAME, AuthPresenter.DEFAULT_AUTH_VALUE);
    }

    public String getPassword() {
        return preferences
                .getString(PASSWORD, AuthPresenter.DEFAULT_AUTH_VALUE);
    }
}
