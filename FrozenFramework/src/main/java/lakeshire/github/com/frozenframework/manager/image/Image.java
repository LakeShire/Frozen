package lakeshire.github.com.frozenframework.manager.image;

import java.io.File;

/**
 * Created by louis.liu on 2017/9/4.
 */

public class Image {
    public String url;
    public int resId = 0;
    public File file;

    public Image(String url) {
        this.url = url;
    }

    public Image(int resId) {
        this.resId = resId;
    }

    public Image(File file) {
        this.file = file;
    }

    public Image(String url, int resId, File file) {
        this.url = url;
        this.resId = resId;
        this.file = file;
    }
}
