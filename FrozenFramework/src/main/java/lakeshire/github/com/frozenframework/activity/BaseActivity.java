package lakeshire.github.com.frozenframework.activity;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import lakeshire.github.com.frozenframework.R;

/**
 * Activity基类
 *
 * @author lakeshire
 */
public class BaseActivity extends LifecycleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
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
}
