package org.romeo.instamarketApp.activities.authorisatation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.romeo.instamarketApp.R;
import org.romeo.instamarketApp.activities.ContentActivity;
import org.romeo.instamarketApp.model.CashPreferences;
import org.romeo.instamarketApp.model.RetrofitService;

import java.io.IOException;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AuthActivity{
    public static final String USER_JSON = "USER_JSON";
    private EditText usernameText;
    private EditText passwordText;
    private AuthPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new AuthPresenter(this, new CashPreferences(this));
        presenter.init();

      //  setContentView(R.layout.activity_main);
    }

    @Override
    public void loadContentActivity(InstagramUser user) {
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra(USER_JSON, new Gson().toJson(user));

        startActivity(intent);
    }

    @Override
    public void initialize() {
        setContentView(R.layout.activity_main);

        usernameText = findViewById(R.id.username_text);
        passwordText = findViewById(R.id.password_text);
        Button enterButton = findViewById(R.id.enter_button);

        enterButton.setOnClickListener(l ->
                presenter.saveUser(
                        usernameText.getText().toString(),
                        passwordText.getText().toString()));
    }

    @Override
    public void showError(int messageId) {
        String message = getResources().getString(messageId);

        Toast.makeText(this,
                message,
                Toast.LENGTH_LONG).show();
    }
}