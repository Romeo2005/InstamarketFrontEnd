package org.romeo.instamarketApp.activities.content.fragments.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.romeo.instamarketApp.model.FragmentArgumentsHolder

/**
 * Created to inject the FragmentArgumentsHolder into the UserViewModel.
 * */

class UserViewModelFactory(private val holder: FragmentArgumentsHolder) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserViewModel(holder) as T
    }
}