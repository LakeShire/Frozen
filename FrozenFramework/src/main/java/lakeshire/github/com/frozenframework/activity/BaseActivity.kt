package lakeshire.github.com.frozenframework.activity

import android.arch.lifecycle.LifecycleActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import kotlinx.android.synthetic.main.act_main.*
import kotlinx.android.synthetic.main.layout_title.*
import lakeshire.github.com.frozenframework.R
import lakeshire.github.com.frozenframework.fragment.BaseFragment
import lakeshire.github.com.frozenframework.util.StatusBarUtil
import org.jetbrains.anko.imageResource

/**
 * Activity基类

 * @author lakeshire
 */
open class BaseActivity : LifecycleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (StatusBarUtil.handleStatus(this)) {
            StatusBarUtil.transparencyBar(this)
            StatusBarUtil.StatusBarLightMode(this)
        }

        setContentView(R.layout.act_main)
        initTitleBar()
    }

    private fun initTitleBar() {
        iv_back.setOnClickListener { onBackPressed() }
    }

    fun hideOrShowTitleBar(show: Boolean) {
        layout_title.visibility = if (show) View.VISIBLE else View.GONE
    }

    fun setTitle(title: String) {
        tv_title.text = title
    }

    fun setAction(res: Int, listener: View.OnClickListener?) {
        iv_action.imageResource = res
        iv_action.setOnClickListener(listener)
    }

    fun startFragment(clazz: Class<*>, bundle: Bundle?) {
        try {
            val fm = supportFragmentManager
            val fragment = clazz.newInstance() as Fragment
            fragment.arguments = bundle
            val ft = fm.beginTransaction()
            ft.replace(R.id.container, fragment)
            ft.addToBackStack(clazz.simpleName).commit()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

    }

    fun endFragment(): Boolean {
        val fm = supportFragmentManager
        if (fm.backStackEntryCount > 0) {
            fm.popBackStackImmediate()
            return true
        } else {
            return false
        }
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        val f = fm.findFragmentById(R.id.container) as BaseFragment
        if (!f.onBackPressed()) {
            if (!doBeforeBackPressed()) {
                super.onBackPressed()
            }
        }
    }

    /**
     * 结束Activity之前的处理
     * @return true 不结束Activity false 结束Activity
     */
    protected open fun doBeforeBackPressed(): Boolean {
        return false
    }

    /**
     * 结束Activity
     */
    fun backNow() {
        super.onBackPressed()
    }
}
