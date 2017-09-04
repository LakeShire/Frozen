package lakeshire.github.com.frozenframework.util;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import com.orhanobut.logger.Logger;
import java.io.File;

/**
 * SD卡工具类
 *
 * @author lakeshire
 */
public class SDCardUtil {

    /**
     * SD卡是否可用
     */
    public static boolean isSdCardAvailable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取Data目录路径
     */
    public static String getDataPath() {
        return Environment.getDataDirectory().getPath();
    }

    /**
     * 获取SD卡路径
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    /**
     * 获得SD卡可用空间
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static long getAvailableSize(String path) {
        try {
            File base = new File(path);
            StatFs stat = new StatFs(base.getPath());
            return stat.getBlockSizeLong() * stat.getAvailableBlocksLong();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取SD卡信息
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static SDCardInfo getSDCardInfo() {
        SDCardInfo sd = new SDCardInfo();
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            sd.isExist = true;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                File sdcardDir = Environment.getExternalStorageDirectory();
                StatFs sf = new StatFs(sdcardDir.getPath());

                sd.totalBlocks = sf.getBlockCountLong();
                sd.blockByteSize = sf.getBlockSizeLong();

                sd.availableBlocks = sf.getAvailableBlocksLong();
                sd.availableBytes = sf.getAvailableBytes();

                sd.freeBlocks = sf.getFreeBlocksLong();
                sd.freeBytes = sf.getFreeBytes();

                sd.totalBytes = sf.getTotalBytes();
            }
        }
        Logger.d(sd.toString());
        return sd;
    }

    public static class SDCardInfo {
        public boolean isExist;
        public long totalBlocks;
        public long freeBlocks;
        public long availableBlocks;

        public long blockByteSize;

        public long totalBytes;
        public long freeBytes;
        public long availableBytes;

        @Override
        public String toString() {
            return "SDCardInfo {" +
                    "isExist =" + isExist +
                    ", totalBlocks =" + totalBlocks +
                    ", freeBlocks =" + freeBlocks +
                    ", availableBlocks =" + availableBlocks +
                    ", blockByteSize =" + blockByteSize +
                    ", totalBytes =" + totalBytes +
                    ", freeBytes =" + freeBytes +
                    ", availableBytes =" + availableBytes +
                    '}';
        }
    }
}
