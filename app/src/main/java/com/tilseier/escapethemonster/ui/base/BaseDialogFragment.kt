package com.tilseier.escapethemonster.ui.base

import android.widget.Toast
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment: DialogFragment() {

    private var toast: Toast? = null

    /**
     * Shows a [Toast] message.
     *
     * @param message An string representing a message to be shown.
     */
    protected open fun showToastMessage(message: String?) {
        toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT)
        toast?.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        toast?.cancel()
    }

}