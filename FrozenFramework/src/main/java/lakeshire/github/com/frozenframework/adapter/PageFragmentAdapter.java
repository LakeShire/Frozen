package lakeshire.github.com.frozenframework.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;
import lakeshire.github.com.frozenframework.fragment.PagerFragment;

/**
 * 子Fragment适配器
 *
 * @author lakeshire
 */
public class PageFragmentAdapter extends FragmentPagerAdapter {
    private List<PagerFragment> mFragments;

    public PageFragmentAdapter(List<PagerFragment> fragments, FragmentManager fm) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTabTitle();
    }

    public List<PagerFragment> getFragments() {
        return mFragments;
    }
}