package com.github.lakeshire.frozen;


import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;

/**
 * Created by louis.liu on 2017/9/13.
 */

@CoordinatorLayout.DefaultBehavior(FollowBehavior.class)
public class MyButton extends android.support.v7.widget.AppCompatButton {
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
