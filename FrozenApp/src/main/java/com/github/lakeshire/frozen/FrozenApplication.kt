package com.github.lakeshire.frozen

import com.hss01248.dialog.StyledDialog
import lakeshire.github.com.frozenframework.BaseApplication

class FrozenApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        StyledDialog.init(this);
    }
}


