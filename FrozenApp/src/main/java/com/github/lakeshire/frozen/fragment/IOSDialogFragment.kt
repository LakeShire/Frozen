package com.github.lakeshire.frozen.fragment

import android.view.View
import com.github.lakeshire.frozen.R
import com.hss01248.dialog.StyledDialog
import com.hss01248.dialog.StyledDialog.buildBottomItemDialog
import com.hss01248.dialog.interfaces.MyItemDialogListener
import kotlinx.android.synthetic.main.fra_ios_dialog.*
import lakeshire.github.com.frozenframework.fragment.BaseFragment
import lakeshire.github.com.frozenframework.util.CustomToast
import java.util.*


/**
 * Created by louis.liu on 2017/9/21.
 */

class IOSDialogFragment : BaseFragment() {
    override val containerLayoutId: Int
        get() = R.layout.fra_ios_dialog

    override fun loadData() {

    }

    override fun initUi() {
        btn_simple.setOnClickListener(this)
        btn_select.setOnClickListener(this)
        btn_progress.setOnClickListener(this)
        btn_confirm.setOnClickListener(this)
        btn_input.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        var id = view?.id
        when (id) {
            R.id.btn_simple -> popSimpleDialog()
            R.id.btn_confirm -> popConfirmDialog()
            R.id.btn_select -> popSelectDialog()
            R.id.btn_progress -> popProgressDialog()
            R.id.btn_input -> popInputDialog()
        }

    }

    private fun popInputDialog() {

    }

    private fun popConfirmDialog() {

    }

    private fun popProgressDialog() {
        val timer = Timer()
        val dialog = StyledDialog.buildProgress("正在下载", true).show()
        val timerTask = object : TimerTask() {
            internal val max = 100
            internal var progress = 0
            override fun run() {
                progress++
                StyledDialog.updateProgress(dialog, progress, max, "下载完成", true)
                if (progress >= max) {
                    timer.cancel()
                }
            }
        }
        timer.schedule(timerTask, 0, 50)
    }

    private fun popSelectDialog() {
        val items: MutableList<String> = ArrayList()
        items.add("拍照")
        items.add("从相册选择")
        buildBottomItemDialog(items, "取消", object : MyItemDialogListener() {
            override fun onItemClick(msg: CharSequence?, position: Int) {
                CustomToast(mContext).showToast(msg.toString())
            }
        }).setCancelable(true, true).show()
    }

    private fun popSimpleDialog() {
//        buildIosAlert("标题", "消息", DialogListenerAdapter()).show()
//        buildIosAlertVertical("标题", "消息", DialogListenerAdapter()).show()
        StyledDialog.buildLoading().show()
    }

    override fun isTitleVisible(): Boolean {
        return true
    }

    override fun getTitle(): String {
        return "IOS风格对话框"
    }
}
