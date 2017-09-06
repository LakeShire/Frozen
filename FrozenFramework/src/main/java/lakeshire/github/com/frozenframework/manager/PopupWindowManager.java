package lakeshire.github.com.frozenframework.manager;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

import lakeshire.github.com.frozenframework.R;
import lakeshire.github.com.frozenframework.adapter.MenuItemAdapter;
import lakeshire.github.com.frozenframework.model.MenuItem;

public class PopupWindowManager {

    private static PopupWindow sWindow;
    private static Object sOperateObject;

    public static void initPopupWindow(final Context context, List<MenuItem> items, AdapterView
            .OnItemClickListener onItemClickListener) {
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout
                .view_popupwindow_list, null, false);

        TextView tvClose = (TextView) view.findViewById(R.id.popup_close);
        tvClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sWindow.dismiss();
            }
        });
        ListView lvCategory = (ListView) view.findViewById(R.id.popup_list);

        MenuItemAdapter adapter = new MenuItemAdapter(context, items, R.layout.item_menu);
        lvCategory.setAdapter(adapter);
        lvCategory.setDivider(null);
        lvCategory.setOnItemClickListener(onItemClickListener);

        sWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                .LayoutParams.WRAP_CONTENT, true);
        sWindow.setAnimationStyle(R.style.AnimationFade);
        sWindow.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        sWindow.setBackgroundDrawable(dw);
        sWindow.setOutsideTouchable(true);
        sWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                setBackgroundAlpha(context, 1f);
            }
        });
    }

    protected static void setBackgroundAlpha(Context context, float f) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = f;
        ((Activity) context).getWindow().setAttributes(lp);
    }

    public static void pop(Context context, View view) {
        setBackgroundAlpha(context, 0.3f);
        sWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    public static void dismiss() {
        sWindow.dismiss();
    }

    public static void setOperateObject(Object object) {
        sOperateObject = object;
    }

    public static Object getOperateObject() {
        return sOperateObject;
    }
}
