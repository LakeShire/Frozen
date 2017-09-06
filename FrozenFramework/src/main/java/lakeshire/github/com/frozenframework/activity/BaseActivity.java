package lakeshire.github.com.frozenframework.activity;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import lakeshire.github.com.frozenframework.R;
import lakeshire.github.com.frozenframework.fragment.BaseFragment;
import lakeshire.github.com.frozenframework.util.StatusBarUtil;

/**
 * Activity基类
 *
 * @author lakeshire
 */
public class BaseActivity extends LifecycleActivity {

    private TextView mTvTitle;
    private ImageView mIvBack;
    private ImageView mIvAction;
    private View mLayoutTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (StatusBarUtil.handleStatus(this)) {
            StatusBarUtil.transparencyBar(this);
            StatusBarUtil.StatusBarLightMode(this);
        }

        setContentView(R.layout.act_main);
        initTitleBar();
    }

    private void initTitleBar() {
        mLayoutTitle = findView(R.id.layout_title);
        mTvTitle = findView(R.id.tv_title);
        mIvBack = findView(R.id.iv_back);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mIvAction = findView(R.id.iv_action);
    }

    public void hideOrShowTitleBar(boolean show) {
        mLayoutTitle.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    public void setAction(int res, View.OnClickListener listener) {
        mIvAction.setImageResource(res);
        mIvAction.setOnClickListener(listener);
    }

    public <T extends View> T findView(int resId) {
        return (T) findViewById(resId);
    }

    public void showToast(int res) {
        showToast(getString(res));
    }

    public void showToast(final String msg) {
        if (!TextUtils.isEmpty(msg)) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void startFragment(Class<?> clazz, Bundle bundle) {
        try {
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = (Fragment) clazz.newInstance();
            fragment.setArguments(bundle);
            if (fragment != null) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment);
                ft.addToBackStack(clazz.getSimpleName()).commit();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public boolean endFragment() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStackImmediate();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        BaseFragment f = (BaseFragment) fm.findFragmentById(R.id.container);
        if (!f.onBackPressed()) {
            if (!doBeforeBackPressed()) {
                super.onBackPressed();
            }
        }
    }

    /**
     * 结束Activity之前的处理
     * @return true 不结束Activity false 结束Activity
     */
    protected boolean doBeforeBackPressed() {
        return false;
    }

    /**
     * 结束Activity
     */
    public void backNow() {
        super.onBackPressed();
    }
}
