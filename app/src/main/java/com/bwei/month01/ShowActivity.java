package com.bwei.month01;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;



import java.util.HashMap;
import java.util.Map;

import adaper.ShowAdaper;
import bean.ShowBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import presenter.PresenterImpl;
import utils.Apis;
import view.Iview;

public class ShowActivity extends AppCompatActivity implements Iview {
    private static final String TAG = "ShowActivity++++++";
    @BindView(R.id.show_recycle)
    RecyclerView showRecycle;
    private PresenterImpl presenter;
    private ShowAdaper adaper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        presenter = new PresenterImpl(this);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        int pscid = intent.getIntExtra("pscid", 40);
        Map<String, String> map = new HashMap<>();
        map.put("pscid", String.valueOf(pscid));
        presenter.reqyestPost(Apis.UEL_SHOW, map, ShowBean.class);
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        showRecycle.setLayoutManager(layoutManager);
        adaper = new ShowAdaper(this);
        showRecycle.setAdapter(adaper);
    }

    @Override
    public void requestData(Object o) {

        if(o instanceof ShowBean){
            Log.d(TAG, "requestData: +++++++"+ShowBean.class);
            ShowBean bean = (ShowBean) o;
            if(bean == null || !bean.isSuccess()){
                Toast.makeText(ShowActivity.this,bean.getMsg(),Toast.LENGTH_SHORT).show();
            }else{
                adaper.setmData(bean.getData());
            }
        }
    }

    @Override
    public void requestFail(Object o) {
        if(o instanceof Exception){
            Exception e = (Exception) o;
            e.printStackTrace();
        }
        Toast.makeText(ShowActivity.this,"请求错误",Toast.LENGTH_SHORT).show();
    }
}
