package com.dino.skinchangenow;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.dino.changeskin.SkinManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lenovo on 2017/1/5.
 */

public class TabActivity extends AppCompatActivity {
    TabLayout tab_title_f;
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
        tab_title_f = (TabLayout)findViewById(R.id.tab_title_layout_f);
        tab_viewPager_f = (ViewPager)findViewById(R.id.tab_viewpager_f);
        tab_fragments = new ArrayList<>();
//        tab_fragments.add(new NewsFragment());
        tab_fragments.add(new Fragment());
        tab_fragments.add(new Fragment());
        tab_fragments.add(new Fragment());

        adapter = new BaseViewpagerAdapter(getSupportFragmentManager(), titles, tab_fragments);
        tab_viewPager_f.setAdapter(adapter);
        tab_viewPager_f.setOffscreenPageLimit(3);

        tab_title_f.setupWithViewPager(tab_viewPager_f);
        //tab_viewPager_f.setCurrentItem(0);
    }
}
