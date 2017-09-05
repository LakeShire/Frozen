package lakeshire.github.com.frozenframework.util.hardwear;

import android.content.Context;
import android.os.Build;
import android.os.PowerManager;

/**
 * 唤醒锁
 * require <uses-permission android:name="android.permission.WAKE_LOCK"/>
 *
 * @author lakeshire
 */
public class WakeLock {
    PowerManager powerManager;
    PowerManager.WakeLock wakeLock;

    public WakeLock(Context context, String tag) {
        powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.FULL_WAKE_LOCK, tag);
    }

    public boolean isScreenOn() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ECLAIR_MR1) {
            return false;
        } else {
            return powerManager.isScreenOn();
        }
    }

    public void turnScreenOn() {
        if (!wakeLock.isHeld()) {
            wakeLock.acquire();
        }
    }

    public void turnScreenOff() {
        if (wakeLock.isHeld()) {
            try {
                wakeLock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void release() {
        if (wakeLock != null && wakeLock.isHeld()) {
            try {
                wakeLock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public PowerManager.WakeLock getWakeLock() {
        return wakeLock;
    }

    public void setWakeLock(PowerManager.WakeLock wakeLock) {
        this.wakeLock = wakeLock;
    }

    public PowerManager getPowerManager() {
        return powerManager;
    }

    public void setPowerManager(PowerManager powerManager) {
        this.powerManager = powerManager;
    }
}

