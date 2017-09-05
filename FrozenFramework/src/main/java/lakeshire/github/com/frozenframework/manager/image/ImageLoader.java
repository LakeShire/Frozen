package lakeshire.github.com.frozenframework.manager.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.TextUtils;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;

/**
 * 图片加载器
 *
 * @author lakeshire
 */
public class ImageLoader {
    private static ImageLoader sInstance;

    private ImageLoader() {

    }

    public static ImageLoader getInstance() {
        if (sInstance == null) {
            synchronized (ImageLoader.class) {
                if (sInstance == null) {
                    sInstance = new ImageLoader();
                    return sInstance;
                }
            }
        }
        return sInstance;
    }

    /**
     * 加载图片 默认实现 256*256的图片中心正方形区域
     *
     * @param context 上下文
     * @param image   图片
     * @param target  目标View
     */
    public void loadImage(Context context, Image image, ImageView target) {
        loadImage(context, image, 0, 0, 256, 256, 0, false, true, target);
    }

    /**
     * 加载原始大小的图片
     */
    public void loadImageOrigin(Context context, Image image, ImageView target) {
        loadImage(context, image, 0, 0, 0, 0, 0, false, false, target);
    }

    /**
     * 加载图片
     *
     * @param context       上下文
     * @param image         图片
     * @param placeholderId 占位符
     * @param errorId       错误图片
     * @param width         宽度
     * @param height        高度
     * @param radius        圆角
     * @param circle        是否是圆形
     * @param centerCrop    是否中心区域
     * @param target        目标View
     */
    public void loadImage(Context context, Image image, int placeholderId, int errorId, int
            width, int height,
                          int radius, boolean circle, boolean centerCrop, ImageView target) {
        RequestCreator creator;

        if (!TextUtils.isEmpty(image.url)) {
            creator = Picasso.with(context).load(image.url);
        } else if (image.resId > 0) {
            creator = Picasso.with(context).load(image.resId);
        } else if (image.file != null) {
            creator = Picasso.with(context).load(image.file);
        } else {
            Logger.e("load image fail: invalid image source");
            return;
        }

        if (placeholderId != 0) {
            creator.placeholder(placeholderId);
        }

        if (errorId != 0) {
            creator.error(errorId);
        }

        if (radius != 0) {
            creator.transform(new RoundCornerTransform(radius));
        }

        if (circle) {
            creator.transform(new CircleTransform());
        }

        if (centerCrop) {
            creator.resize(width, height).centerCrop();
        } else {
            creator.fit();
        }

        creator.into(target);
    }

    private class CircleTransform implements Transformation {
        private static final String KEY = "CircleTransform";

        @Override
        public Bitmap transform(Bitmap source) {
            int minEdge = Math.min(source.getWidth(), source.getHeight());
            int dx = (source.getWidth() - minEdge) / 2;
            int dy = (source.getHeight() - minEdge) / 2;

            Shader shader = new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            Matrix matrix = new Matrix();
            matrix.setTranslate(-dx, -dy);
            shader.setLocalMatrix(matrix);

            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setShader(shader);

            Bitmap output = Bitmap.createBitmap(minEdge, minEdge, source.getConfig());
            Canvas canvas = new Canvas(output);
            canvas.drawOval(new RectF(0, 0, minEdge, minEdge), paint);

            source.recycle();
            return output;
        }

        @Override
        public String key() {
            return KEY;
        }
    }

    private class RoundCornerTransform implements Transformation {
        private static final String KEY = "RoundCornerTransform";
        private int radius;

        public RoundCornerTransform(int radius) {
            this.radius = radius;
        }

        @Override
        public Bitmap transform(Bitmap source) {
            int widthLight = source.getWidth();
            int heightLight = source.getHeight();
            Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap
                    .Config.ARGB_8888);
            Canvas canvas = new Canvas(output);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

            RectF rectF = new RectF(new Rect(0, 0, widthLight, heightLight));
            canvas.drawRoundRect(rectF, radius, radius, paint);

            Paint paintImage = new Paint();
            paintImage.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
            canvas.drawBitmap(source, 0, 0, paintImage);
            source.recycle();
            return output;
        }

        @Override
        public String key() {
            return KEY;
        }
    }
}
