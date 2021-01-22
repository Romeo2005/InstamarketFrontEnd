package org.romeo.instamarketApp.activities.authorisatation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser
import org.romeo.instamarketApp.activities.content.ContentActivity
import org.romeo.instamarketApp.databinding.ActivityAuthBinding
import org.romeo.instamarketApp.model.PreferencesHolder


/**
 * This is activity for user's authorisation.
 * User enters his instagram username and password
 * and app gets an InstagramUser object.
 *
 * This activity realises MVP pattern, because it
 * is too simple to use MVVM. But it can be simple
 * transformed into MVVM.
 */

class AuthActivity : AppCompatActivity(), AuthActivityTemplate {
    private lateinit var presenter: AuthPresenter
    private lateinit var binding: ActivityAuthBinding

    companion object {
        const val USER_JSON = "USER_JSON"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)

        presenter = AuthPresenter(this, PreferencesHolder(this))

        binding.presenter = presenter
    }

    override fun loadContentActivity(user: InstagramUser) {
        val intent = Intent(this, ContentActivity::class.java)
        intent.putExtra(USER_JSON, Gson().toJson(user))
        startActivity(intent)
    }

    override fun initialize() {
        runOnUiThread {
            setContentView(binding.root)
        }
    }

    /**
     * The next 2 methods will be replaced with errorData: MutableLiveData<String>,
     * in case of integration this activity to MVVM.
     * */
    override fun showError(messageResourseId: Int) {
        val message = resources.getString(messageResourseId)
        Toast.makeText(this,
                message,
                Toast.LENGTH_LONG).show()
    }

    override fun showError(messageText: String?) {
        Toast.makeText(this, messageText, Toast.LENGTH_LONG).show()
    }
}