package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.bwei.month01.R;
import com.bwei.month01.ShowActivity;

import java.util.HashMap;
import java.util.Map;

import adaper.LeftAdaper;
import adaper.RightTitleAdaper;
import bean.LeftBean;
import bean.RightBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import presenter.PresenterImpl;
import utils.Apis;
import view.Iview;

public class ClassifyFragment extends Fragment implements Iview {

    @BindView(R.id.left_recycle)
    RecyclerView leftRecycle;
    @BindView(R.id.right_recycle)
    RecyclerView rightRecycle;
    Unbinder unbinder;
    private PresenterImpl presenter;
    private LeftAdaper leftAdaper;
    private RightTitleAdaper titleAdaper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classify_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }
    /**
     * 初始化数据
     */
    private void initData() {
        Map<String,String> mapOne = new HashMap<>();
        presenter.reqyestPost(Apis.UEL_ONE,mapOne, LeftBean.class);
    }

    /**
     * 初始化view
     */
    private void initView() {
        presenter = new PresenterImpl(this);
        initLeftView();
        initRightView();
    }
    //右边视图
    private void initRightView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        rightRecycle.setLayoutManager(layoutManager);
        //创建适配器
        titleAdaper = new RightTitleAdaper(getActivity());
        rightRecycle.setAdapter(titleAdaper);
        titleAdaper.setTitleCallBack(new RightTitleAdaper.TitleCallBack() {
            @Override
            public void callBack(int pscid) {
                Intent intent = new Intent(getActivity(),ShowActivity.class);
                intent.putExtra("pscid",pscid);
                startActivity(intent);
            }
        });
    }
    //左边视图
    private void initLeftView() {
        //创建布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        leftRecycle.setLayoutManager(layoutManager);
        //创建适配器
        leftAdaper = new LeftAdaper(getActivity());
        leftRecycle.setAdapter(leftAdaper);
        leftAdaper.setLeftCallBack(new LeftAdaper.LeftCallBack() {
            @Override
            public void callBack(int cid) {
                Map<String,String> map = new HashMap<>();
                map.put("cid",String.valueOf(cid));
                presenter.reqyestPost(Apis.UEL_TWO,map, RightBean.class);
            }
        });
    }

    @Override
    public void requestData(Object o) {
        if(o instanceof LeftBean){
            LeftBean leftBean = (LeftBean) o;
            if(leftBean==null || !leftBean.isSuccess()){
                Toast.makeText(getActivity(),leftBean.getMsg(),Toast.LENGTH_SHORT).show();
            }else{
                leftAdaper.setmData(leftBean.getData());
            }
        }else if(o instanceof RightBean){
            if(o instanceof RightBean){
                RightBean rightBean = (RightBean) o;
                if(rightBean == null || !rightBean.isSuccess()){
                    Toast.makeText(getActivity(),rightBean.getMsg(),Toast.LENGTH_SHORT).show();
                }else{
                    titleAdaper.setmDatas(rightBean.getData());
                }
            }
        }
    }

    @Override
    public void requestFail(Object o) {
        if (o instanceof Exception) {
            Exception e = (Exception) o;
            e.printStackTrace();
        }
        Toast.makeText(getActivity(), "请求错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.onDetach();
    }
}
