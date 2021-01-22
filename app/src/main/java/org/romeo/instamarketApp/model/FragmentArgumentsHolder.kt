package org.romeo.instamarketApp.model

import androidx.fragment.app.Fragment
import org.romeo.instamarketApp.activities.authorisatation.AuthActivity

/**
 * A class for keeping the arguments of fragment
 * to avoid using UI classes in Presenter/ViewModel
 * */
class FragmentArgumentsHolder(fragment: Fragment) {
    private val arguments = fragment.arguments

    val instagramUserJSON: String?
        get() =
            arguments?.getString(AuthActivity.USER_JSON, null)
}