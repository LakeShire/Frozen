package lakeshire.github.com.frozenframework.fragment;

/**
 * 子页面
 */
public abstract class PagerFragment extends BasePullFragment implements IPager {
    private String tabTitle;

    public String getTabTitle() {
        return tabTitle;
    }

    public void setTabTitle(String title) {
        this.tabTitle = title;
    }
}
