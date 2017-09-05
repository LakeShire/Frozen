package lakeshire.github.com.frozenframework.util;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;

/**
 * 剪贴板工具类
 */
public class ClipboardUtil {

    /**
     * 复制到剪贴板（低版本兼容）
     */
    public static void copyToClipboardSupport(Context context, String text) {
        android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.setText(text);
    }

    /**
     * 获取剪贴板最近的内容（低版本兼容）
     */
    public static String getLatestTextSupport(Context context) {
        android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        return clipboard.getText().toString();
    }

    /**
     * 复制到剪贴板
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void copyToClipboard(Context context, String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.setPrimaryClip(ClipData.newPlainText(null, text));
    }

    /**
     * 获取剪贴板内容数量
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static int getItemCount(Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = clipboard.getPrimaryClip();
        return data.getItemCount();
    }

    /**
     * 获取剪贴板内容
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static String getText(Context context, int index) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = clipboard.getPrimaryClip();
        if (clip != null && clip.getItemCount() > index) {
            return String.valueOf(clip.getItemAt(0).coerceToText(context));
        }
        return null;
    }

    /**
     * 获取剪贴板最近的内容
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static String getLatestText(Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = clipboard.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            return String.valueOf(clip.getItemAt(0).coerceToText(context));
        }
        return null;
    }
}
