package org.romeo.instamarketApp.utils

import android.widget.EditText
import androidx.databinding.BindingAdapter
import org.romeo.instamarketApp.activities.authorisatation.AuthPresenter

@BindingAdapter("username_watcher")
fun bindUsername(editText: EditText, vm: AuthPresenter) {
    vm.username = editText.toString()
}

@BindingAdapter("password_watching")
fun bindPassword(editText: EditText, vm: AuthPresenter) {
    vm.password = editText.toString()
}
