package co.gotoinc.core

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment


fun Fragment.hideKeyboard() {
    activity?.let {
        val imm = it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = it.currentFocus
        if (view == null) {
            view = View(it)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Fragment.getDrawable(@DrawableRes id: Int): Drawable? {
    return context?.resources?.getDrawable(id)
}