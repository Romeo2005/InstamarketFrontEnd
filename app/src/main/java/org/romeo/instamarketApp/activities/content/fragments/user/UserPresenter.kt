package org.romeo.instamarketApp.activities.content.fragments.user

import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser
import org.romeo.instamarketApp.R
import org.romeo.instamarketApp.activities.authorisatation.AuthActivity
import org.romeo.instamarketApp.databinding.FragmentUserBinding

class UserPresenter(fragment: UserFragment) {

    private val user: InstagramUser = Gson().fromJson(
            fragment.arguments!!.getString(AuthActivity.USER_JSON),
            InstagramUser::class.java)

    private lateinit var binding: FragmentUserBinding

    fun initUserProperties(binding: FragmentUserBinding) {
        val name = binding.propertyName
        val country = binding.propertyCountry
        val description = binding.propertyDescription
        val followers = binding.propertyFollowersNumber

        Picasso.get().load(user.getHd_profile_pic_url_info().url)
                .into(name.propertyImage)
        name.propertyText.setText(user.getUsername())

        country.propertyImage
                .setImageResource(R.drawable.house_icon)
        country.propertyText.setText(user.getCity_name())

        description.propertyImage
                .setImageResource(R.drawable.description_icon)
        description.propertyText.setText(user.getBiography())

        followers.propertyImage
                .setImageResource(R.drawable.followers_icon)
        followers.propertyText.setText(user.getFollower_count().toString())
    }
}