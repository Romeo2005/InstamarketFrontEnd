package org.romeo.instamarketApp.activities.content.fragments.user

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser
import org.romeo.instamarketApp.R
import org.romeo.instamarketApp.databinding.FragmentUserBinding
import org.romeo.instamarketApp.model.FragmentArgumentsHolder


/**
 * A fragment showing main info about the user.
 * Gets an InstagramUser in start intent.
 * */
class UserFragment : Fragment() {
    private lateinit var binding: FragmentUserBinding

    private lateinit var viewModel: UserViewModel

    private lateinit var userData: LiveData<UserState>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = ViewModelProvider(this,
                UserViewModelFactory(FragmentArgumentsHolder(this)))
                .get(UserViewModel::class.java)

        initUserLiveData()
    }


    /**
     * This method assigns images and labels to appropriate
     * properties.
     * */
    private fun initUserLiveData() {
        userData = viewModel.getUserData()

        userData.observe(this, { userState ->
            val name = binding.propertyName
            val country = binding.propertyCountry
            val biography = binding.propertyBiography
            val followers = binding.propertyFollowersNumber

            name.propertyImage.setImageBitmap(userState.userImgBitmap)
            name.propertyText.setText(userState.username)

            country.propertyImage
                    .setImageResource(R.drawable.house_icon)
            country.propertyText.setText(userState.city)

            biography.propertyImage
                    .setImageResource(R.drawable.description_icon)
            biography.propertyText.setText(userState.biography)

            followers.propertyImage
                    .setImageResource(R.drawable.followers_icon)
            followers.propertyText.setText(userState.followersNumber.toString())
        })
    }

    // Не уверен нащет правильности реализации создания фрагмента
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