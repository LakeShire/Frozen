package lakeshire.github.com.frozenframework.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

/**
 * 系统对话框工具类
 *
 * @author lakeshire
 */
public class DialogUtil {

    public static AlertDialog.Builder dialogBuilder(Context context, String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (msg != null) {
            builder.setMessage(msg);
        }
        if (title != null) {
            builder.setTitle(title);
        }
        return builder;
    }

    public static AlertDialog.Builder dialogBuilder(Context context, int title, View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (view != null) {
            builder.setView(view);
        }
        if (title > 0) {
            builder.setTitle(title);
        }
        return builder;
    }

    public static AlertDialog.Builder dialogBuilder(Context context, int titleResId, int msgResId) {
        String title = titleResId > 0 ? context.getResources().getString(titleResId) : null;
        String msg = msgResId > 0 ? context.getResources().getString(msgResId) : null;
        return dialogBuilder(context, title, msg);
    }

    /**
     * 无按钮对话框
     */
    public static Dialog showTips(Context context, String title, String des) {
        return showTips(context, title, des, null, null, null, null);
    }

    /**
     * 无按钮对话框
     */
    public static Dialog showTips(Context context, int title, int des) {
        return showTips(context, context.getString(title), context.getString(des));
    }

    /**
     * 有确定按钮对话框
     */
    public static Dialog showTips(Context context, int title, int des, int btn, DialogInterface.OnClickListener listener) {
        return showTips(context, context.getString(title), context.getString(des), context.getString(btn), listener);
    }

    /**
     * 有确定按钮对话框
     */
    public static Dialog showTips(Context context, String title, String des, String okBtn, DialogInterface.OnClickListener listener) {
        return showTips(context, title, des, okBtn, listener, null, null);
    }

    public static Dialog showTips(Context context, String title, String des, String okBtn, DialogInterface.OnClickListener okListener, String cancelBtn, DialogInterface.OnClickListener cancelListener) {
        AlertDialog.Builder builder = dialogBuilder(context, title, des);
        builder.setCancelable(true);
        builder.setPositiveButton(okBtn, okListener);
//        if (!TextUtils.isEmpty(cancelBtn)) {
            builder.setNegativeButton(cancelBtn, cancelListener);
//        }
        Dialog dialog = builder.show();
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }
}