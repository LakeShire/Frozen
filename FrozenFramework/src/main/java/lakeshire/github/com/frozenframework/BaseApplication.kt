package lakeshire.github.com.frozenframework

import android.app.Application
import android.content.Context
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.squareup.leakcanary.LeakCanary

/**
 * Application基础类

 * @author lakeshire
 */
open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        sApp = this
        CrashHandler.instance.init(this)
        LeakCanary.install(this)
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .methodCount(1)
                .showThreadInfo(false)
                .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
    }

    companion object {
        lateinit var sApp: BaseApplication
        val myApplicationContext: Context
            get() = sApp
    }
}
