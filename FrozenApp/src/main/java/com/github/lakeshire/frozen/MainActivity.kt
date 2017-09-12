package com.github.lakeshire.frozen

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import lakeshire.github.com.frozenframework.activity.BaseActivity
import lakeshire.github.com.frozenframework.util.DialogUtil

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fm = supportFragmentManager
        var fragment: Fragment? = fm.findFragmentById(R.id.container)
        if (fragment == null) {
            fragment = MainFragment()
            fm.beginTransaction().add(R.id.container, fragment).commit()
        }
    }

    override fun doBeforeBackPressed(): Boolean {
        DialogUtil.showTips(this, "确定退出？", "确定退出？", "确定", DialogInterface.OnClickListener { dialog, which -> backNow() }, "取消", null)
        return true
    }
}
