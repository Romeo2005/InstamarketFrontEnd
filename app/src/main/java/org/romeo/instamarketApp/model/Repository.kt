package org.romeo.instamarketApp.model

import android.graphics.Bitmap
import android.widget.ImageView
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser
import org.romeo.instamarketApp.model.data_model.UsernamePassword
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object Repository {
    private const val BASE_URL = "https://192.168.1.102:8080/"
    private const val TAG = "REPOSITORY"
    private val retrofitService: RetrofitService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(unsafeOkHttpClient)
                .build()
        retrofitService = retrofit.create(RetrofitService::class.java)
    }

    fun getInstagramUserFor(username: String?, password: String?): InstagramUser? {
        val call = retrofitService.getInstagramUser(
                UsernamePassword(username!!, password!!))
        val user: InstagramUser?

        try {
            user = call.execute().body()
        } catch (e: ConnectException) {
            e.printStackTrace()
            return null
        } catch (e: SocketTimeoutException) {
            e.printStackTrace()
            return null
        }

        return user
    }

    fun getImageFromUrl(url: String): Bitmap = Picasso.get().load(url).get()

    //TODO: Make the connection safe after turning server into httpS
    private val unsafeOkHttpClient: OkHttpClient
        get() = try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(
                    object : X509TrustManager {
                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                        }

                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }
                    }
            )

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory)
            builder.hostnameVerifier { hostname, session -> true }
            builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
}