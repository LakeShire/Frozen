package com.github.lakeshire.frozen;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import lakeshire.github.com.frozenframework.activity.BaseActivity;
import lakeshire.github.com.frozenframework.util.DialogUtil;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.container);
        if (fragment == null) {
            fragment = new MainFragment();
            fm.beginTransaction().add(R.id.container, fragment).commit();
        }
    }

    @Override
    protected boolean doBeforeBackPressed() {
        DialogUtil.showTips(this, "确定退出？", "确定退出？", "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                backNow();
            }
        }, "取消", null);
        return true;
    }
}
