package lakeshire.github.com.frozenframework.fragment;

/**
 * 子页面
 */
public abstract class PagerFragment extends BasePullFragment {
    private String tabTitle;
    private int iconId;
    private String fragmentId;

    public String getFragmentId() {
        return fragmentId;
    }

    public void setFragmentId(String fragmentId) {
        this.fragmentId = fragmentId;
    }

    public String getTabTitle() {
        return tabTitle;
    }

    public void setTabTitle(String title) {
        this.tabTitle = title;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
