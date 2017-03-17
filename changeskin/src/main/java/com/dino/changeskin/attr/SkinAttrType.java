package com.dino.changeskin.attr;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dino.changeskin.ResourceManager;
import com.dino.changeskin.SkinManager;

import java.util.List;


/**
 * Created by zhy on 15/9/28.
 */
public enum SkinAttrType {
    BACKGROUND("background") {
        @Override
        public void apply(View view, String resName) {
            Drawable drawable = getResourceManager().getDrawableByName(resName);
            if (drawable != null) {
                view.setBackgroundDrawable(drawable);
            } else {
                try {
                    int color = getResourceManager().getColor(resName);
                    view.setBackgroundColor(color);
                } catch (Resources.NotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }, COLOR("textColor") {
        @Override
        public void apply(View view, String resName) {
            ColorStateList colorList = getResourceManager().getColorStateList(resName);
            if (colorList == null) return;
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(colorList);
            }
        }
    }, SRC("src") {
        @Override
        public void apply(View view, String resName) {
            if (view instanceof ImageView) {
                Drawable drawable = getResourceManager().getDrawableByName(resName);
                if (drawable == null) {
                    drawable = getResourceManager().getMipmapByName(resName);
                    if (drawable == null) return;
                }
                ((ImageView) view).setImageDrawable(drawable);
            }

        }

    }, SRCCOMPAT("srcCompat") {
        @Override
        public void apply(View view, String resName) {
            if (view instanceof ImageView) {
                Drawable drawable = getResourceManager().getDrawableByName(resName);
                if (drawable == null) {
                    drawable = getResourceManager().getMipmapByName(resName);
                    if (drawable == null) return;
                }
                ((ImageView) view).setImageDrawable(drawable);
            }

        }

    }, TABINDICATORCOLOR("tabIndicatorColor") {
        @Override
        public void apply(View view, String resName) {
            if (view instanceof TabLayout) {
                ColorStateList colorList = getResourceManager().getColorStateList(resName);
                if (colorList == null) return;
                ((TabLayout) view).setSelectedTabIndicatorColor(colorList.getDefaultColor());
            }
        }
    }, TABSELECTEDTEXTCOLOR("tabSelectedTextColor") {
        @Override
        public void apply(View view, String resName) {
            if (view instanceof TabLayout) {
                ColorStateList colorList = getResourceManager().getColorStateList(resName);
                if (colorList == null) return;
                int defaultColor = ((TabLayout) view).getTabTextColors().getDefaultColor();
                ((TabLayout) view).setTabTextColors(defaultColor, colorList.getDefaultColor());
            }

        }
    }, DIVIDER("divider") {
        @Override
        public void apply(View view, String resName) {
            if (view instanceof ListView) {
                Drawable divider = getResourceManager().getDrawableByName(resName);
                if (divider == null) return;
                ((ListView) view).setDivider(divider);
            }
        }
    }, STATUSBARCOLOR("statusBarColor") {
        @Override
        public void apply(View view, String resName) {
            ColorStateList colorList = getResourceManager().getColorStateList(resName);
            if (colorList == null) return;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                for (Activity activity : getActivity()) {
                    Window window = activity.getWindow();
//                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                            | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(colorList.getDefaultColor());   //这里动态修改颜色
                }
            }
        }
    };

    String attrType;

    SkinAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getAttrType() {
        return attrType;
    }


    public abstract void apply(View view, String resName);

    public ResourceManager getResourceManager() {
        return SkinManager.getInstance().getResourceManager();
    }

    public List<Activity> getActivity() {
        return SkinManager.getInstance().getActivity();
    }

}
