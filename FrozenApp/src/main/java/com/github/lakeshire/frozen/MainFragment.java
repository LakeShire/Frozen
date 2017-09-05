package com.github.lakeshire.frozen;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import in.srain.cube.views.ptr.PtrFrameLayout;
import lakeshire.github.com.frozenframework.fragment.BaseFragment;
import lakeshire.github.com.frozenframework.fragment.BasePullFragment;
import lakeshire.github.com.frozenframework.manager.image.Image;
import lakeshire.github.com.frozenframework.manager.image.ImageLoader;
import lakeshire.github.com.frozenframework.util.CustomToast;
import lakeshire.github.com.frozenframework.util.DialogUtil;
import lakeshire.github.com.frozenframework.util.HandlerUtil;
import lakeshire.github.com.frozenframework.util.HexUtil;
import lakeshire.github.com.frozenframework.util.NotificationUtil;

/**
 * Created by liuhan on 17/9/1.
 */

public class MainFragment extends BasePullFragment {
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

        findView(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CustomToast(getContext()).showSingletonToast("你好");
            }
        });
    }

    @Override
    protected void onRefresh(PtrFrameLayout frame) {

    }

    @Override
    protected boolean checkCanRefresh(PtrFrameLayout frame, View content, View header) {
        return true;
    }
}
