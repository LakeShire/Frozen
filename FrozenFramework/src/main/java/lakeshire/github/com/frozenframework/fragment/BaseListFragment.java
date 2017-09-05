package lakeshire.github.com.frozenframework.fragment;

import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;
import lakeshire.github.com.frozenframework.R;

/**
 * Created by liuhan on 17/9/5.
 */

public abstract class BaseListFragment<T> extends BasePullFragment {

    private ListView mListView;
    private List<T> mData = new ArrayList<>();

    @Override
    public void initUi() {
        super.initUi();

        mListView = findView(R.id.list);
    }

    @Override
    public void loadData() {
        super.loadData();
    }

    @Override
    protected void onRefresh(PtrFrameLayout frame) {

    }

    @Override
    protected boolean checkCanRefresh(PtrFrameLayout frame, View content, View header) {
        return false;
    }
}
