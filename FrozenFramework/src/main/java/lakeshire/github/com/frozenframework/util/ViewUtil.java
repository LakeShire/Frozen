/**
 * ViewUtil.java
 * com.ximalaya.ting.android.util
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * 2014年11月20日 ricky
 */

package lakeshire.github.com.frozenframework.util;

import android.support.v4.view.ViewPager;
import android.widget.Scroller;
import java.lang.reflect.Field;

public class ViewUtil {

    public static void setViewPagerScroller(ViewPager pager, Scroller scroller) {
        try {
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            mField.set(pager, scroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
