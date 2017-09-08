package com.github.lakeshire.frozen;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lakeshire.github.com.frozenframework.view.blurdialogfragment.SupportBlurDialogFragment;

public class PlayFragment extends SupportBlurDialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
        setBlurRadius(4);
        setDownScaleFactor(4.0f);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_play, container);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        return view;
    }
}