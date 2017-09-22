package com.github.lakeshire.frozen.fragment

import android.content.DialogInterface
import android.view.View
import com.github.lakeshire.frozen.R
import kotlinx.android.synthetic.main.fra_ios_dialog.*
import lakeshire.github.com.frozenframework.fragment.BaseFragment
import lakeshire.github.com.frozenframework.util.CustomToast
import lakeshire.github.com.frozenframework.view.DialogBuilder
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
        btn_confirm.setOnClickListener(this)
        btn_select.setOnClickListener(this)
        btn_progress.setOnClickListener(this)
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
        DialogBuilder(activity).iosDialog().setTitle("输入专辑名称").enableInput("输入专辑名称", object : DialogBuilder.InputListener {
            override fun onInputReady(result: String) {
                CustomToast(mContext).showToast("输入了：" + result)
            }
        })
    }

    private fun popConfirmDialog() {
        DialogBuilder(activity).iosDialog().setTitle("升级提示").setMessage("有新的版本，建议升级").setPositiveButtonIos().setNegativeButtonIos()
    }

    private fun popProgressDialog() {
        val timer = Timer()
        val dialog = DialogBuilder(activity)
                .iosDialog()
                .setProgress(0)
                .setOnDismissListener(DialogInterface.OnDismissListener {
                    timer.cancel()
                })
        val timerTask = object : TimerTask() {
            internal val max = 100
            internal var progress = 0
            override fun run() {
                progress++
                dialog.updateProgress("正在升级 $progress%", progress)
                if (progress >= max) {
                    dialog.updateProgress("升级完成", progress)
                    dialog.dismiss()
                    timer.cancel()
                }
            }
        }
        timer.schedule(timerTask, 500, 50)
    }

    private fun popSelectDialog() {
        DialogBuilder(activity)
                .iosBottomSheet()
                .addItem("拍照", View.OnClickListener { CustomToast(mContext).showToast("拍照") })
                .addItem("从相册选择", View.OnClickListener { CustomToast(mContext).showToast("从相册选择") })
                .addItem("小视频", View.OnClickListener { CustomToast(mContext).showToast("小视频") })
                .show()
    }

    private fun popSimpleDialog() {
        DialogBuilder(activity).mdDialog().setTitle("升级提示").setMessage("有新版本，建议升级").setPositiveButtonMd("知道了").show()
//        DialogBuilder(activity).iosDialog().setTitle("升级提示").setMessage("有新版本，建议升级").setPositiveButton("知道了")
    }

    override fun isTitleVisible(): Boolean {
        return true
    }

    override fun getTitle(): String {
        return "IOS风格对话框"
    }
}
