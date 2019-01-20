package adaper;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bwei.month01.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import bean.RightBean;
import butterknife.BindView;
import butterknife.ButterKnife;


public class RightAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<RightBean.DataBean.ListBean> mList;
    private Context mContext;

    public RightAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<RightBean.DataBean.ListBean> lists) {
        mList.addAll(lists);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.reght_recycle_item, viewGroup, false);
        ViewHolderRight holderRight = new ViewHolderRight(view);
        return holderRight;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ViewHolderRight holderRight = (ViewHolderRight) viewHolder;
        holderRight.rightTitle.setText(mList.get(i).getName());
        holderRight.rightSimpl.setImageURI(Uri.parse(mList.get(i).getIcon()));
        holderRight.con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rightCallBack!=null){
                    rightCallBack.CallBack(mList.get(i).getPscid());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolderRight extends RecyclerView.ViewHolder {
        @BindView(R.id.right_simpl)
        SimpleDraweeView rightSimpl;
        @BindView(R.id.right_title)
        TextView rightTitle;
        @BindView(R.id.con)
        ConstraintLayout con;
        public ViewHolderRight(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    //定义接口
    private RightCallBack rightCallBack;

    public void setRightCallBack(RightCallBack rightCallBack) {
        this.rightCallBack = rightCallBack;
    }

    public interface RightCallBack {
        void CallBack(int pscid);
    }
}
