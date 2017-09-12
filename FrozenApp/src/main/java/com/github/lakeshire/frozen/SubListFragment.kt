package com.github.lakeshire.frozen

import android.view.View
import android.widget.AdapterView
import com.github.lakeshire.frozen.manager.SubjectAdapter
import lakeshire.github.com.frozenframework.adapter.BaseAdapter
import lakeshire.github.com.frozenframework.fragment.BaseListFragment
import lakeshire.github.com.frozenframework.fragment.IPager
import lakeshire.github.com.frozenframework.manager.PopupWindowManager
import lakeshire.github.com.frozenframework.model.MenuItem
import lakeshire.github.com.frozenframework.util.CustomToast
import lakeshire.github.com.frozenframework.util.RandomUtil
import java.util.*

class SubListFragment : BaseListFragment<String>(), IPager {
    override var tabTitle: String = ""

    override fun initData() {
        //        for (int i = 0; i < 10; i++) {
        //            mDataList.add(tabTitle + RandomUtil.getRandom(1000));
        //        }
        showLoadingLayout()
    }

    override val adapter: BaseAdapter<String>
        get() = SubjectAdapter(context, mDataList, R.layout.item)

    override fun loadMoreData() {
        for (i in 0..9) {
            mDataList.add(tabTitle!! + RandomUtil.getRandom(1000))
        }
        notifyAdapter(BaseListFragment.STATUES_OK, false)
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        startFragment(SubFragment::class.java, null)
        //        PopupWindowManager.pop(getActivity(), view);
    }

    override fun initUi() {
        super.initUi()

        val items = ArrayList<MenuItem>()
        items.add(MenuItem("试听", R.drawable.ic_category, null))
        items.add(MenuItem("下载", R.drawable.ic_home, null))
        PopupWindowManager.initPopupWindow(activity, items) { parent, view, position, id ->
            when (position) {
                0 -> CustomToast(context).showToast("试听")
                1 -> CustomToast(context).showToast("下载")
            }
        }
    }


    override fun onRefresh() {
        mDataList.clear()
        for (i in 0..9) {
            mDataList.add(tabTitle!! + RandomUtil.getRandom(1000))
        }
        notifyAdapter(BaseListFragment.STATUES_OK, true)
    }
}
