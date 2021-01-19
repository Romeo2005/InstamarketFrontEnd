package org.romeo.instamarketApp.activities.authorisatation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser
import org.romeo.instamarketApp.R
import org.romeo.instamarketApp.activities.content.ContentActivity
import org.romeo.instamarketApp.databinding.ActivityAuthBinding
import org.romeo.instamarketApp.model.CashPreferences

class AuthActivity : AppCompatActivity(), AuthActivityTemplate {
    private var presenter: AuthPresenter? = null
    private lateinit var binding: ActivityAuthBinding

    companion object {
        const val USER_JSON = "USER_JSON"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)

        presenter = AuthPresenter(this, CashPreferences(this))
        presenter!!.init()
    }

    override fun loadContentActivity(user: InstagramUser) {
        val intent = Intent(this, ContentActivity::class.java)
        intent.putExtra(USER_JSON, Gson().toJson(user))
        startActivity(intent)
    }

    override fun initialize() {
        setContentView(binding.root)
        binding.enterButton.setOnClickListener { v: View? ->
            presenter!!.saveUser(
                    binding.usernameText.text.toString(),
                    binding.passwordText.text.toString()
            )
        }
    }

    override fun showError(messageId: Int) {
        val message = resources.getString(messageId)
        Toast.makeText(this,
                message,
                Toast.LENGTH_LONG).show()
    }
}