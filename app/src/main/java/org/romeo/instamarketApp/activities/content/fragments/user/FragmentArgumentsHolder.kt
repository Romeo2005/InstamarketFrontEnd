package org.romeo.instamarketApp.activities.content.fragments.user

import androidx.fragment.app.Fragment
import org.romeo.instamarketApp.activities.authorisatation.AuthActivity

class FragmentArgumentsHolder(fragment: Fragment) {
    private val arguments = fragment.arguments

    val instagramUserJSON: String?
        get() =
            arguments?.getString(AuthActivity.USER_JSON, null)
}