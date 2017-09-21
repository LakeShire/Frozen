package com.github.lakeshire.frozen.fragment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.lakeshire.frozen.R
import lakeshire.github.com.frozenframework.view.blurdialogfragment.SupportBlurDialogFragment

class PlayFragment : SupportBlurDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog)
        setBlurRadius(4)
        setDownScaleFactor(4.0f)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.dialog_play, container)
        return view
    }
}