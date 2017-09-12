package lakeshire.github.com.frozenframework

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.PrintWriter
import java.lang.Thread.UncaughtExceptionHandler
import java.text.SimpleDateFormat
import java.util.*

/**
 * 错误收集

 * @author lakeshire
 */
class CrashHandler private constructor() : UncaughtExceptionHandler {

    var mDefaultHandler: UncaughtExceptionHandler? = null
    lateinit var mContext: Context

    val simpleDateFormat = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss")

    fun getLogFilePath(ctx: Context): File? {
        val sdStatus = Environment.getExternalStorageState()
        if (sdStatus != Environment.MEDIA_MOUNTED) {
            return null
        }

        val dirStr = Environment.getExternalStorageDirectory().path + "/Android/data/" +
                ctx.packageName + "/files/error/"
        val dir = File(dirStr)
        if (!dir.exists()) {
            dir.mkdirs()
        }

        val pathStr = Environment.getExternalStorageDirectory().path + "/Android/data/" +
                ctx.packageName + "/files/error/" + simpleDateFormat.format(Date()) + "" +
                ".log"

        val path = File(pathStr)
        try {
            path.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return path
    }

    fun init(context: Context) {
        mContext = context
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(thread: Thread?, ex: Throwable?) {
        if (thread == null || ex == null || mDefaultHandler == null) {
            System.exit(0)
            return
        }

        val savePathFile = getLogFilePath(mContext)

        if (savePathFile == null) {
            mDefaultHandler!!.uncaughtException(thread, ex)
            return
        }

        val logMessage: String
        var printWriter: PrintWriter? = null

        try {
            printWriter = PrintWriter(FileWriter(savePathFile, true))
            logMessage = String.format("%s\r\n\r\nThread: " + "%d\r\n\r\nMessage:\r\n\r\n%s\r\n\r\nStack Trace:\r\n\r\n%s", Date(),
                    thread.id, ex.message, Log.getStackTraceString(ex))

            printWriter.print(logMessage)
            printWriter.print("\n\n---------------------------------------------------------------------------\n\n")
        } catch (tr2: Throwable) {

        } finally {
            if (printWriter != null) {
                printWriter.close()
            }
        }
        mDefaultHandler!!.uncaughtException(thread, ex)
    }

    companion object {
        val instance: CrashHandler
            get() {
                if (crashHandler == null) {
                    synchronized(CrashHandler::class.java) {
                        crashHandler = CrashHandler()
                    }
                }
                return crashHandler!!
            }
        private var crashHandler: CrashHandler? = null
    }
}
