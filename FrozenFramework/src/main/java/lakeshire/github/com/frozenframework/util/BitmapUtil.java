package lakeshire.github.com.frozenframework.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;

/**
 * 位图工具类
 *
 * @author lakeshire
 */
public class BitmapUtil {

    private static Resources getResources(Context context) {
        return context.getResources();
    }

    /**
     * 资源 -> 位图
     */
    public static Bitmap resource2Bitmap(Context context, int resource) {
        Resources res = getResources(context);
        return BitmapFactory.decodeResource(res, resource);
    }

    /**
     * 位图 -> Drawable
     */
    public static Drawable bitmap2Drawable(Context context, Bitmap bitmap) {
        return new BitmapDrawable(getResources(context), bitmap);
    }

    /**
     * Drawable -> 位图
     */
    public static Bitmap drawable2Bitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 位图 -> bytes
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * bytes -> 位图
     */
    public static Bitmap bytes2Bitmap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

    /**
     * 缩小
     */
    public static Bitmap reduce(String path, int width, int height) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        Bitmap bm = BitmapFactory.decodeFile(path, opt);

        int picWidth = opt.outWidth;
        int picHeight = opt.outHeight;

        opt.inSampleSize = 1;
        if (width > height) {
            if (picWidth > width) {
                opt.inSampleSize = picWidth / width;
            }
        } else {
            if (picHeight > height) {
                opt.inSampleSize = picHeight / height;
            }
        }

        opt.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(path, opt);
        return bm;
    }

    /**
     * 缩小
     */
    public static Bitmap reduce(Context context, int res, int width, int height) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        Bitmap bm = BitmapFactory.decodeResource(getResources(context), res, opt);

        int picWidth = opt.outWidth;
        int picHeight = opt.outHeight;

        opt.inSampleSize = 1;
        if (width > height) {
            if (picWidth > width) {
                opt.inSampleSize = picWidth / width;
            }
        } else {
            if (picHeight > height) {
                opt.inSampleSize = picHeight / height;
            }
        }

        opt.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeResource(getResources(context), res, opt);
        return bm;
    }
}
