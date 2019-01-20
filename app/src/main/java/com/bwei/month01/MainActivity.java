package com.bwei.month01;

import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;



import adaper.FragmentAdaper;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tab)
    TabLayout tab;
    private FragmentAdaper adaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //创建适配器
        adaper = new FragmentAdaper(getSupportFragmentManager());
        viewpager.setAdapter(adaper);
        tab.setupWithViewPager(viewpager);
    }
}
