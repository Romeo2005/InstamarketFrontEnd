package org.romeo.instamarketApp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.romeo.instamarketApp.R;
import org.romeo.instamarketApp.server_working.RetrofitService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "https://10.0.2.2:8080/";
    //public static final String BASE_URL = "https://api.openweathermap.org/";
    public static final String DEFAULT_AUTH_VALUE = "-1";
    public static final String USER_JSON = "USER_JSON";
    private EditText usernameText;
    private EditText passwordText;

    private Handler instagramHandler;

    private RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HandlerThread instagramHandlerThread = new HandlerThread("instagramHandlerThread");
        instagramHandlerThread.start();
        instagramHandler = new Handler(instagramHandlerThread.getLooper());

        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        retrofitService = retrofit.create(RetrofitService.class);

        if (isAuthCompleted()) {
            authorise();
        } else
            initViews();
    }

    private void initViews() {
        usernameText = findViewById(R.id.username_text);
        passwordText = findViewById(R.id.password_text);
        Button enterButton = findViewById(R.id.enter_button);

        enterButton.setOnClickListener(l -> authorise());
    }

    private void authorise() {
        instagramHandler.post(() -> {
            try {

                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();

                Call<InstagramUser> call = retrofitService.getInstagramUser(username, password);

                Response<InstagramUser> response = call.execute();

                InstagramUser user =
                        response.body();

                if (user != null) {
                    getPreferences(MODE_PRIVATE)
                            .edit()
                            .putString("username", username)
                            .putString("password", password)
                            .apply();

                    Intent intent = new Intent(this, ContentActivity.class);
                    intent.putExtra(USER_JSON, new Gson().toJson(user));

                    startActivity(intent);
                } else
                    Toast.makeText(this,
                            getResources().getString(R.string.incorrect),
                            Toast.LENGTH_LONG).show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private boolean isAuthCompleted() {
        return !getPreferences(MODE_PRIVATE)
                .getString("username", DEFAULT_AUTH_VALUE)
                .equals(DEFAULT_AUTH_VALUE);
    }
}