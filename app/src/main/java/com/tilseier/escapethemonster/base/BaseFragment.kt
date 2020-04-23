package com.tilseier.escapethemonster.base

import android.content.Context
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
    protected var myContext: Context? = null
    private var toast: Toast? = null

    //Attach context to avoid context null on orientation change
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.myContext = context
    }

    /**
     * Shows a [Toast] message.
     *
     * @param message An string representing a message to be shown.
     */
    protected open fun showToastMessage(message: String?) {
        toast = myContext?.let { Toast.makeText(it, message, Toast.LENGTH_SHORT) }//Toast.makeText(activity, message, Toast.LENGTH_SHORT)// activity?.let { Toast.makeText(it, message, Toast.LENGTH_SHORT) }
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