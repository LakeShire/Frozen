package com.github.lakeshire.frozen;

import lakeshire.github.com.frozenframework.fragment.IPager;

/**
 * Created by louis.liu on 2017/9/13.
 */

public interface IScrollPager extends IPager {
    void notifyAppBarOffset(int offset);
}
