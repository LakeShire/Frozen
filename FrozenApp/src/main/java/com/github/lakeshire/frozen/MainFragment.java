package com.github.lakeshire.frozen;

import android.widget.ImageView;

import lakeshire.github.com.frozenframework.fragment.BaseFragment;
import lakeshire.github.com.frozenframework.manager.image.Image;
import lakeshire.github.com.frozenframework.manager.image.ImageLoader;

/**
 * Created by liuhan on 17/9/1.
 */

public class MainFragment extends BaseFragment {
    @Override
    public int getContainerLayoutId() {
        return R.layout.fra_main;
    }

    @Override
    public void loadData() {
        super.loadData();

//        showLoadingLayout();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                showNetworkErrorLayout();
//            }
//        }).start();


        ImageView iv = findView(R.id.iv_main);
        ImageLoader.getInstance().loadImage(getContext(), new Image("https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p480747492.webp"), iv);
//        Bitmap bitmap = BitmapUtil.reduce(getContext(), R.drawable.large_pic, 128, 128);
//        iv.setImageBitmap(bitmap);
    }
}
