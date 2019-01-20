package adaper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bwei.month01.R;

import java.util.ArrayList;
import java.util.List;

import bean.LeftBean;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LeftAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<LeftBean.DataBean> mData;
    private Context mContext;

    public LeftAdaper(Context mContext) {
        this.mContext = mContext;
        mData = new ArrayList<>();
    }
    public void setmData(List<LeftBean.DataBean> datas){
        mData.addAll(datas);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.left_recycle_item, viewGroup, false);
        ViewHolderLeft holderLeft = new ViewHolderLeft(view);
        return holderLeft;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ViewHolderLeft holderLeft = (ViewHolderLeft) viewHolder;
        holderLeft.leftTextName.setText(mData.get(i).getName());
        holderLeft.leftTextName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(leftCallBack!=null){
                    leftCallBack.callBack(mData.get(i).getCid());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolderLeft extends RecyclerView.ViewHolder {
        @BindView(R.id.left_text_name)
        TextView leftTextName;
        public ViewHolderLeft(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    //定义接口
    private LeftCallBack leftCallBack;
    public void setLeftCallBack(LeftCallBack leftCallBack){
        this.leftCallBack = leftCallBack;
    }
    public interface LeftCallBack{
        void callBack(int cid);
    }
}
