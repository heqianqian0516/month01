package adaper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bwei.month01.R;

import java.util.ArrayList;
import java.util.List;

import bean.RightBean;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RightTitleAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<RightBean.DataBean> mData;
    private Context mContext;

    public RightTitleAdaper(Context mContext) {
        this.mContext = mContext;
        mData = new ArrayList<>();
    }
    public void setmDatas(List<RightBean.DataBean> datas){
        mData.clear();
        if(datas!=null){
            mData.addAll(datas);
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.right_top_item, viewGroup, false);
        ViewHolderTitle holderTitle = new ViewHolderTitle(view);
        return holderTitle;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolderTitle holderTitle = (ViewHolderTitle) viewHolder;
        holderTitle.title.setText(mData.get(i).getName());
        RightAdaper rightAdaper = new RightAdaper(mContext);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,3);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        holderTitle.topRecycle.setLayoutManager(gridLayoutManager);
        holderTitle.topRecycle.setAdapter(rightAdaper);
        rightAdaper.setmList(mData.get(i).getList());
        rightAdaper.setRightCallBack(new RightAdaper.RightCallBack() {
            @Override
            public void CallBack(int pscid) {
                if(titleCallBack!=null){
                    titleCallBack.callBack(pscid);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolderTitle extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.top_recycle)
        RecyclerView topRecycle;
        public ViewHolderTitle(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    //定义接口
    private TitleCallBack titleCallBack;
    public void setTitleCallBack(TitleCallBack titleCallBack){
        this.titleCallBack = titleCallBack;
    }
    public interface TitleCallBack{
        void callBack(int pscid);
    }

}
