package lakeshire.github.com.frozenframework.fragment;

/**
 * 列表项中有可滑动项时 Adapter需要实现的接口 告诉外部是否正在滑动
 *
 * @author lakeshire
 */
public interface IInnerScrollable {
    boolean isInnerScrolling();
}
