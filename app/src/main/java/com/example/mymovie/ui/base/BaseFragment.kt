package com.example.mymovie.ui.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment(){

    val TAG = this.javaClass.simpleName

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    var loadingDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    open fun VB.initUI() {

    }

    open fun VB.initEvent() {

    }

    open fun VB.initObserve() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        if (loadingDialog != null) {
            if (loadingDialog!!.isShowing)
                loadingDialog?.dismiss()

            loadingDialog = null
        }
    }
}