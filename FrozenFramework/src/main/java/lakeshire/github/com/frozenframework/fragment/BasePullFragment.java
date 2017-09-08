package lakeshire.github.com.frozenframework.fragment;

import android.view.View;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;
import lakeshire.github.com.frozenframework.R;

/**
 * 支持下拉的Fragment
 *
 * @author lakeshire
 */
public abstract class BasePullFragment extends BaseFragment {
    protected PtrFrameLayout mPtrFrameLayout;

    @Override
    public void initUi() {
        mPtrFrameLayout = (PtrFrameLayout) mContainerView.findViewById(R.id.ptr_frame);
        initPtrFrame();
    }

    protected void initPtrFrame() {
        View header = getHeader();
        mPtrFrameLayout.setDurationToCloseHeader(1500);
        mPtrFrameLayout.setDurationToClose(300);
        mPtrFrameLayout.setResistance(1.5f);
        mPtrFrameLayout.setRatioOfHeaderHeightToRefresh(1.0f);
        mPtrFrameLayout.setKeepHeaderWhenRefresh(true);
        mPtrFrameLayout.setPullToRefresh(true);
        mPtrFrameLayout.setHeaderView(header);
        if (header instanceof PtrUIHandler) {
            mPtrFrameLayout.addPtrUIHandler((PtrUIHandler) header);
        }
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return checkCanRefresh(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                onRefresh();
            }
        });

    }

    protected View getHeader() {
        MaterialHeader header = new MaterialHeader(mContext);
        header.setPadding(0, 0, 0, 32);
        return header;
    }

    protected void refreshComplete() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPtrFrameLayout.refreshComplete();
            }
        });
    }

    abstract protected void onRefresh();

    abstract protected boolean checkCanRefresh(PtrFrameLayout frame, View content, View header);
}
