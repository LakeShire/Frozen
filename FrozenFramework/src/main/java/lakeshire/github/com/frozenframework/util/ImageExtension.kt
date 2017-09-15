package lakeshire.github.com.frozenframework.util

import android.widget.ImageView
import lakeshire.github.com.frozenframework.manager.image.Image
import lakeshire.github.com.frozenframework.manager.image.ImageLoader
import java.io.File

fun ImageView.loadImageCenter(url: String) {
    ImageLoader.getInstance().loadImage(context, Image(url), this)
}

fun ImageView.loadImageCenter(res: Int) {
    ImageLoader.getInstance().loadImage(context, Image(res), this)
}

fun ImageView.loadImageCenter(file: File) {
    ImageLoader.getInstance().loadImage(context, Image(file), this)
}

fun ImageView.loadImage(url: String) {
    ImageLoader.getInstance().loadImageOrigin(context, Image(url), this)
}

fun ImageView.loadImage(res: Int) {
    ImageLoader.getInstance().loadImageOrigin(context, Image(res), this)
}

fun ImageView.loadImage(file: File) {
    ImageLoader.getInstance().loadImageOrigin(context, Image(file), this)
}

fun ImageView.loadImage(url: String, placeholderId: Int, errorId: Int, width: Int, height: Int, radius: Int, circle: Boolean, centerCrop: Boolean) {
    ImageLoader.getInstance().loadImage(context, Image(url), placeholderId, errorId, width, height, radius, circle, centerCrop, this)
}

fun ImageView.loadImage(res: Int, placeholderId: Int, errorId: Int, width: Int, height: Int, radius: Int, circle: Boolean, centerCrop: Boolean) {
    ImageLoader.getInstance().loadImage(context, Image(res), placeholderId, errorId, width, height, radius, circle, centerCrop, this)
}

fun ImageView.loadImage(file: File, placeholderId: Int, errorId: Int, width: Int, height: Int, radius: Int, circle: Boolean, centerCrop: Boolean) {
    ImageLoader.getInstance().loadImage(context, Image(file), placeholderId, errorId, width, height, radius, circle, centerCrop, this)
}