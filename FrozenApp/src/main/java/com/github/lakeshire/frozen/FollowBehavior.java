package com.github.lakeshire.frozen;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by louis.liu on 2017/9/13.
 */

public class FollowBehavior extends CoordinatorLayout.Behavior {

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        if (dependency instanceof RecyclerView) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        Log.d("liuhan", "onDependentViewChanged");
        dependency.setY(child.getY() + child.getMeasuredHeight());
        return true;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        Log.d("liuhan", "onStartNestedScroll");
        return true;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        Log.d("liuhan", "onNestedPreScroll: dy=" + dy + ", consumedY=" + consumed[1]);
        int newY = (int) (child.getY() - dy);
        if (newY < -child.getMeasuredHeight()) {
            newY = -child.getMeasuredHeight();
        }

        if (newY > 0) {
            newY = 0;
        }

        if (dy < 0) {
//            if (target instanceof RecyclerView) {
//                LinearLayoutManager lm = (LinearLayoutManager) ((RecyclerView) target).getLayoutManager();
//                if (lm.findFirstCompletelyVisibleItemPosition() == 0) {
                    child.setY(newY);
                    target.setY(newY + child.getMeasuredHeight());
//                }
//            }
        } else {
            child.setY(newY);
            target.setY(newY + child.getMeasuredHeight());
        }
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
    }
}
