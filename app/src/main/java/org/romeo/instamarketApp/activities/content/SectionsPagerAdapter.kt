package org.romeo.instamarketApp.activities.content

import android.content.Context
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.gson.Gson
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser
import org.romeo.instamarketApp.R
import org.romeo.instamarketApp.activities.authorisatation.AuthActivity
import org.romeo.instamarketApp.activities.content.fragments.AddsFragment
import org.romeo.instamarketApp.activities.content.fragments.FiltersFragment
import org.romeo.instamarketApp.activities.content.fragments.user.UserFragment.Companion.newInstance

/**
 * It can be an inner class in the ContentActivity.
 * But for the clearness of the code it is transported
 * to the separate public class.
 * */

class SectionsPagerAdapter(activity: AppCompatActivity, fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
    @StringRes
    private val tabTitles = intArrayOf(R.string.filters_title, R.string.user_title, R.string.adds_title)
    private val mContext: Context
    private val user: InstagramUser
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return FiltersFragment()
            1 -> return newInstance(user)
            2 -> return AddsFragment()
        }
        return newInstance(user)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mContext.resources.getString(tabTitles[position])
    }

    override fun getCount(): Int {
        return 3
    }

    init {
        mContext = activity
        user = Gson().fromJson(
                activity.intent.getStringExtra(AuthActivity.USER_JSON),
                InstagramUser::class.java)
    }
}