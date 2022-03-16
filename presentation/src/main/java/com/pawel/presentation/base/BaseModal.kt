package com.pawel.presentation.base

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.pawel.worldline_android_technical_test.presentation.R

open class BaseModal : DialogFragment() {

    protected var _binding: ViewBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NORMAL, R.style.FullScreen_Dialog)
    }
}