package lakeshire.github.com.frozenframework;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.squareup.leakcanary.LeakCanary;

/**
 * Application基础类
 *
 * @author lakeshire
 */
public class BaseApplication extends Application {

    public static BaseApplication sApp;

    @Override
    public void onCreate() {
        super.onCreate();

        sApp = this;
        CrashHandler.getInstance().init(this);
        LeakCanary.install(this);
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .methodCount(1)
                .showThreadInfo(false)
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    public static Context getMyApplicationContext() {
        return sApp;
    }
}
