package lakeshire.github.com.frozenframework.util;

import android.os.Handler;
import android.os.Looper;

/**
 * Handler工具类
 */
public class HandlerUtil {
    public static final Handler HANDLER = new Handler(Looper.getMainLooper());

    public static void ui(Runnable runnable){
        HANDLER.post(runnable);
    }

    public static void uiDelay(Runnable runnable, long delayMillis){
        HANDLER.postDelayed(runnable,delayMillis);
    }

    public static void remove(Runnable runnable){
        HANDLER.removeCallbacks(runnable);
    }
}

