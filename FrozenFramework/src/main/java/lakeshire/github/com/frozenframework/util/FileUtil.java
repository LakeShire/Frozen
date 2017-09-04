package lakeshire.github.com.frozenframework.util;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * 文件工具类
 *
 * @author lakeshire
 */
public class FileUtil {

    /**
     * 创建文件
     */
    public static void createFile() {

    }

    /**
     * 删除文件
     */
    public static void deleteFile() {

    }

    /**
     * 复制文件
     */
    public static void copyFile() {

    }

    /**
     * 获取易理解的文件大小
     */
    public static String getFriendlyFileSize(File file) {
        long blockSize = 0;
        try {
            if (file.isDirectory()) {
                blockSize = getFileSizes(file);
            } else {
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e("获取文件大小", "获取失败!");
        }

        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString;
        if (blockSize < 1024) {
            fileSizeString = df.format((double) blockSize) + "B";
        } else if (blockSize < 1048576) {
            fileSizeString = df.format((double) blockSize / 1024) + "K";
        } else if (blockSize < 1073741824) {
            fileSizeString = df.format((double) blockSize / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) blockSize / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 获取文件大小
     */
    private static long getFileSize(File file) throws IOException {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            Logger.e("file not existed!");
        }
        return size;
    }

    /**
     * 获取文件夹大小
     */
    private static long getFileSizes(File f) throws IOException {
        long size = 0;
        File fileList[] = f.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isDirectory()) {
                size = size + getFileSizes(fileList[i]);
            } else {
                size = size + getFileSize(fileList[i]);
            }
        }
        return size;
    }
}
