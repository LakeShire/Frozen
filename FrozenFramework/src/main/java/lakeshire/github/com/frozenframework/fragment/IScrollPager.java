package lakeshire.github.com.frozenframework.fragment;

/**
 * 嵌套滑动页面接口 用于通知AppBar的偏移
 *
 * @author lakeshire
 */
public interface IScrollPager extends IPager {
    void notifyAppBarOffset(int offset);
}
