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
        return showTips(context, title, des, null, null);
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
    public static Dialog showTips(Context context, int title, int des, int btn, DialogInterface.OnDismissListener dismissListener) {
        return showTips(context, context.getString(title), context.getString(des), context.getString(btn), dismissListener);
    }

    /**
     * 有确定按钮对话框
     */
    public static Dialog showTips(Context context, String title, String des, String btn, DialogInterface.OnDismissListener dismissListener) {
        AlertDialog.Builder builder = dialogBuilder(context, title, des);
        builder.setCancelable(true);
        builder.setPositiveButton(btn, null);
        Dialog dialog = builder.show();
        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnDismissListener(dismissListener);
        return dialog;
    }
}