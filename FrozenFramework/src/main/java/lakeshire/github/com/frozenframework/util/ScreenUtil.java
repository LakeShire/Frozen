package lakeshire.github.com.frozenframework.util;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

/**
 * 屏幕工具类
 *
 * @author lakeshire
 */
public class ScreenUtil {

//    public static int getStatusBarHeight(Activity activity) {
//        Rect frame = new Rect();
//        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
//        return frame.top;
//    }

//    public static int getTitleBarHeight(Activity activity) {
//        int contentTop = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
//        return contentTop - getStatusBarHeight(activity);
//    }

    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        return display.getHeight();
    }

    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        return display.getWidth();
    }

//    public static void setNoTitle(Activity activity) {
//        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
//    }

//    public static void setFullScreen(Activity activity) {
//        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
//                .LayoutParams.FLAG_FULLSCREEN);
//    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dp2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
