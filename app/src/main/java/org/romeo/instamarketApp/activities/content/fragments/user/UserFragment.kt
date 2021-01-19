package org.romeo.instamarketApp.activities.content.fragments.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser
import org.romeo.instamarketApp.R
import org.romeo.instamarketApp.databinding.FragmentUserBinding

class UserFragment : Fragment(), UserFragmentTemplate {
    private var presenter: UserPresenter? = null
    private lateinit var binding: FragmentUserBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = UserPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        presenter!!.initUserProperties(binding)
    }

    companion object {
        private const val USER_JSON = "USER_JSON"
        @JvmStatic
        fun newInstance(user: InstagramUser?): UserFragment {
            val fragment = UserFragment()
            val args = Bundle()
            args.putString(USER_JSON, Gson().toJson(user!!))
            fragment.arguments = args
            return fragment
        }
    }
}