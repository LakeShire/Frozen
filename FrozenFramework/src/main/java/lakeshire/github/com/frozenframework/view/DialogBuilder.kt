package lakeshire.github.com.frozenframework.view

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.InputType
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import lakeshire.github.com.frozenframework.R
import lakeshire.github.com.frozenframework.adapter.BaseAdapter
import lakeshire.github.com.frozenframework.adapter.ViewHolder



/**
 * Created by louis.liu on 2017/9/21.
 */

class DialogBuilder(activity: Activity) {

    private var mTvTitle: TextView? = null
    private var mTvMessage: TextView? = null
    private var mLayoutButtons: View? = null
    private var mDivider: View? = null
    private var mTvPositive: TextView? = null
    private var mTvNegative: TextView? = null
    private var mTvNeutral: TextView? = null
    private var mEtInput: EditText? = null
    private var mDivider1: View? = null
    private var mDivider2: View? = null
    private var mProgress: ProgressBar? = null
    private var mTvProgressDescription: TextView? = null
    private var mListView: ListView? = null
    private var mOkListener: View.OnClickListener? = null
    private var mCancelListener: View.OnClickListener? = null
    private var mNeutralListener: View.OnClickListener? = null
    private var builder: AlertDialog.Builder? = null
    private lateinit var dialog: AlertDialog
    private var mActivity: Activity = activity
    private var inputManager: InputMethodManager? = null

    private var type: Int = 0
    private val IOS = 0
    private val MD = 1

    private var itemList: MutableList<Item> = ArrayList()

    fun setTitle(title: String?): DialogBuilder {
        when (type) {
            IOS -> {
                if (title != null) {
                    mTvTitle?.text = title
                    mTvTitle?.visibility = View.VISIBLE
                }
            }
            MD -> {
                builder?.setTitle(title)
            }
        }
        return this
    }

    fun setMessage(message: String?): DialogBuilder {
        when (type) {
            IOS -> {
                if (message != null) {
                    mTvMessage?.text = message
                    mTvMessage?.visibility = View.VISIBLE
                }
            }
            MD -> {
                builder?.setMessage(message)
            }
        }
        return this
    }

    fun setPositiveButtonMd(okString: String = "确定", okListener: DialogInterface.OnClickListener? = null): DialogBuilder {
        builder?.setPositiveButton(okString, okListener)
        return this
    }

    fun setPositiveButtonIos(okString: String? = "确定", okListener: View.OnClickListener? = null): DialogBuilder {
        if (okString != null) {
            mLayoutButtons?.visibility = View.VISIBLE
            mDivider?.visibility = View.VISIBLE
            mTvPositive?.visibility = View.VISIBLE
            mTvPositive?.text = okString
            mOkListener = okListener
        }
        return this
    }

    fun setNegativeButtonMd(des: String = "取消", listener: DialogInterface.OnClickListener? = null): DialogBuilder {
        builder?.setNegativeButton(des, listener)
        return this
    }

    fun setNegativeButtonIos(cancelString: String? = "取消", cancelListener: View.OnClickListener? = null): DialogBuilder {
        if (cancelString != null) {
            mDivider1?.visibility = View.VISIBLE
            mTvNegative?.visibility = View.VISIBLE
            mTvNegative?.text = cancelString
            mCancelListener = cancelListener
        }
        return this
    }

    fun setNeutralButtonMd(des: String, listener: DialogInterface.OnClickListener? = null): DialogBuilder {
        builder?.setNeutralButton(des, listener)
        return this
    }

    fun setNeutralButtonIos(neutralString: String? = "其他", neutralListener: View.OnClickListener? = null): DialogBuilder {
        if (neutralString != null) {
            mDivider2?.visibility = View.VISIBLE
            mTvNeutral?.visibility = View.VISIBLE
            mTvNeutral?.text = neutralString
            mNeutralListener = neutralListener
        }
        return this
    }

    fun setProgress(progress: Int, max: Int = 100, message: String? = null): DialogBuilder {
        mProgress?.visibility = View.VISIBLE
        mProgress?.progress = progress
        mProgress?.max = max
        if (message != null) {
            mTvProgressDescription?.text = message
            mTvProgressDescription?.visibility = View.VISIBLE
        }
        return this
    }

    fun setOnDismissListener(listener: DialogInterface.OnDismissListener): DialogBuilder {
        dialog.setOnDismissListener(listener)
        return this
    }

    fun setCancelable(cancelable: Boolean): DialogBuilder {
        dialog.setCancelable(cancelable)
        return this
    }

    fun setCanceledOnTouchOutside(canceledOnTouchOutside: Boolean): DialogBuilder {
        dialog.setCanceledOnTouchOutside(canceledOnTouchOutside)
        return this
    }

    fun addItem(action: String, listener: View.OnClickListener? = null): DialogBuilder {
        itemList.add(Item(action, listener))
        return this
    }

    fun enableInput(hint: String = "请输入......", inputListener: InputListener? = null): DialogBuilder {
        mEtInput?.visibility = View.VISIBLE
        mEtInput?.hint = hint
        mEtInput?.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
        mEtInput?.isFocusable = true
        mEtInput?.requestFocus()
        mEtInput?.postDelayed({
            inputManager = mEtInput?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager?.showSoftInput(mEtInput, InputMethodManager.SHOW_FORCED)
        }, 300)
        setOnDismissListener(DialogInterface.OnDismissListener {
            inputManager?.hideSoftInputFromWindow(mEtInput?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        })
        setPositiveButtonIos("确定", View.OnClickListener {
            inputListener?.onInputReady(mEtInput?.text.toString())
        })
        return this
    }

    fun dismiss() {
        dialog.dismiss()
    }

    fun updateProgress(message: String, progress: Int) {
        mActivity.runOnUiThread {
            mProgress?.progress = progress
            mTvProgressDescription?.text = message
        }
    }

    fun iosDialog(): DialogBuilder {
        type = IOS
        val view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_ios, null)
        dialog = AlertDialog.Builder(mActivity, R.style.CustomDialog).create()
        dialog.show()
        dialog.window!!.setGravity(Gravity.CENTER)
        dialog.window!!.setContentView(view)

        //  不加这句话没法弹出输入框
        dialog.window!!.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)

        mTvTitle = view.findViewById<TextView>(R.id.tv_title)
        mTvMessage = view.findViewById<TextView>(R.id.tv_message)
        mEtInput = view.findViewById<EditText>(R.id.et_input)

        mLayoutButtons = view.findViewById<View>(R.id.ll_buttons)
        mDivider = view.findViewById<View>(R.id.divider)
        mTvPositive = view.findViewById<TextView>(R.id.tv_positive)
        mTvPositive?.setOnClickListener({
            dialog.dismiss()
            mOkListener?.onClick(mTvPositive)
        })

        mTvNegative = view.findViewById<TextView>(R.id.tv_negative)
        mTvNegative?.setOnClickListener({
            dialog.dismiss()
            mCancelListener?.onClick(mTvNegative)
        })

        mTvNeutral = view.findViewById<TextView>(R.id.tv_neutral)
        mTvNegative?.setOnClickListener({
            dialog.dismiss()
            mNeutralListener?.onClick(mTvNegative)
        })

        mProgress = view.findViewById<ProgressBar>(R.id.progress_bar)
        mTvProgressDescription = view.findViewById<TextView>(R.id.tv_progress_description)

        mDivider1 = view.findViewById<View>(R.id.v_divider_1)
        mDivider2 = view.findViewById<View>(R.id.v_divider_2)

        return this
    }

    fun mdDialog(): DialogBuilder {
        type = MD
        builder = AlertDialog.Builder(mActivity)
        return this
    }

    fun iosBottomSheet(): DialogBuilder {
        val view = LayoutInflater.from(mActivity).inflate(R.layout.bottom_sheet_ios, null)
        dialog = AlertDialog.Builder(mActivity, R.style.CustomDialog).create()
        dialog.show()
        dialog.window!!.setGravity(Gravity.BOTTOM)
        dialog.window!!.setContentView(view)
        dialog.window!!.setWindowAnimations(R.style.AnimationFade)

        mTvTitle = view.findViewById<TextView>(R.id.tv_title)
        mListView = view.findViewById<ListView>(R.id.list)

        return this
    }

    fun show() {
        when (type) {
            MD -> {
                dialog = builder!!.create()
                dialog.show()
            }
            IOS -> {
                if (mListView != null) {
                    val adapter = ItemAdapter(mActivity, itemList, R.layout.item)
                    mListView?.adapter = adapter
                    mListView?.onItemClickListener = AdapterView.OnItemClickListener { _: AdapterView<*>, view: View, position: Int, _: Long ->
                        run {
                            itemList[position].action?.onClick(view)
                            dialog.dismiss()
                        }
                    }
                }
            }
        }
    }

    class Item(var name: String, var action: View.OnClickListener?)

    class ItemAdapter(mContext: Context?, mListData: MutableList<Item>?, mLayoutResId: Int) : BaseAdapter<Item>(mContext, mListData, mLayoutResId) {
        override fun bindViewData(viewHolder: ViewHolder?, item: Item?, position: Int) {
            viewHolder?.getItemView<TextView>(R.id.tv_title)?.text = item?.name
        }
    }

    interface InputListener {
        fun onInputReady(result: String)
    }
}
