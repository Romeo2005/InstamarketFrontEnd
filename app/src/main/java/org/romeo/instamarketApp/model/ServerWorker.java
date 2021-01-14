package org.romeo.instamarketApp.model;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.jetbrains.annotations.NotNull;

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
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerWorker {
    public static final String BASE_URL = "https://192.168.0.111:8080/";
    private static final String TAG = "SERVER_WORKER";
    private final RetrofitService retrofitService;
    private Handler handler;

    public ServerWorker() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getUnsafeOkHttpClient())
                .build();

        retrofitService = retrofit.create(RetrofitService.class);

        HandlerThread handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
    }

    public InstagramUser getInstagramUserFor(String username, String password) {

        Call<InstagramUser> call = retrofitService.getInstagramUser(username, password);

        final InstagramUser[] user = new InstagramUser[1];

/*        call.enqueue(new Callback<InstagramUser>() {   --- не работает
            @Override
            public void onResponse(@NotNull Call<InstagramUser> call, @NotNull Response<InstagramUser> response) {
                user[0] =
                        response.body();

                Log.d(TAG, "getInstagramUserFor: success");
            }

            @Override
            public void onFailure(@NotNull Call<InstagramUser> call, @NotNull Throwable t) {
                user[0] = null;

                Log.d(TAG, "getInstagramUserFor: user = null");
            }
        });*/

        handler.post(() -> { // -- Работает
            try {
                user[0] = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return user[0];
    }

    //TODO: Make connection safe
    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
