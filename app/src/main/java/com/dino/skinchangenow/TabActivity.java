package com.dino.skinchangenow;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.dino.changeskin.SkinManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lenovo on 2017/1/5.
 */

public class TabActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager tab_viewPager_f;

    private List<Fragment> tab_fragments;
    private String titles[] = {"hello","world","!"};

    private BaseViewpagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        SkinManager.getInstance().register(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().unregister(this);
    }

    public void init() {
        tabLayout = (TabLayout)findViewById(R.id.tab_title_layout_f);
        tab_viewPager_f = (ViewPager)findViewById(R.id.tab_viewpager_f);
        tab_fragments = new ArrayList<>();
//        tab_fragments.add(new NewsFragment());
        tab_fragments.add(new Fragment());
        tab_fragments.add(new Fragment());
        tab_fragments.add(new Fragment());

        adapter = new BaseViewpagerAdapter(getSupportFragmentManager(), titles, tab_fragments);
        tab_viewPager_f.setAdapter(adapter);
        tab_viewPager_f.setOffscreenPageLimit(3);

        tabLayout.setupWithViewPager(tab_viewPager_f);
        //tab_viewPager_f.setCurrentItem(0);

        //设置自定义标题
//        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            //获得到对应位置的Tab
            TabLayout.Tab tab = tabLayout.getTabAt(0);
            //设置自定义的标题
            tab.setCustomView(getTabView(0));
//        }
    }


    private View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.main_item_tab, null);
        ImageView iv_tab = (ImageView) view.findViewById(R.id.image);
        TextView tv_tab = (TextView) view.findViewById(R.id.title);
        iv_tab.setImageResource( R.drawable.main_tab_1);
        iv_tab.setTag("skin:main_tab_1:src");
        tv_tab.setText(titles[position]);

//        //建议通过xml inflater
//        TextView tv = new TextView(this);
//        tv.setTag("skin:item_text_color:textColor");
//        tv.setTextColor(getResources().getColorStateList(R.color.item_text_color));
//        tv.setText("dymaic add!");

//        ((ViewGroup) findViewById(R.id.id_container)).addView(tv);
        SkinManager.getInstance().injectSkin(view);
        return view;
    }
}
