package com.tilseier.escapethemonster.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation

abstract class BaseFragment: Fragment() {

    protected lateinit var navController: NavController
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(getLayoutId(), container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    abstract fun getLayoutId(): Int

}