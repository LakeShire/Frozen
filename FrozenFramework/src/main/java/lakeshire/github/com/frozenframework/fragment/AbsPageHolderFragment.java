package lakeshire.github.com.frozenframework.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;
import lakeshire.github.com.frozenframework.R;
import lakeshire.github.com.frozenframework.adapter.PageFragmentAdapter;

/**
 * 有子页面的Fragment抽象类
 *
 * @author lakeshire
 */
abstract public class AbsPageHolderFragment extends BasePullFragment {
    protected ViewPager mPager;
    protected PageFragmentAdapter mAdapter;

    @Override
    abstract public int getContainerLayoutId();

    @Override
    public void initUi() {
        super.initUi();

        mPager = findView(R.id.pager);
        List<IPager> fragments = initFragments();
        mAdapter = new PageFragmentAdapter(fragments, getChildFragmentManager());
        mPager.setAdapter(mAdapter);
    }

    abstract protected List<IPager> initFragments();

    @Override
    public void onRefresh() {
        refreshComplete();
    }

    @Override
    public boolean checkCanRefresh(PtrFrameLayout frame, View content, View header) {
        return false;
    }
}
