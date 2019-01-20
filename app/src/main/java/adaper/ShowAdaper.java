package adaper;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.month01.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import bean.ShowBean;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ShowBean.DataBean> mData;
    private Context mContext;

    public ShowAdaper(Context mContext) {
        this.mContext = mContext;
        mData = new ArrayList<>();
    }
    public void setmData(List<ShowBean.DataBean> datas){
        mData.clear();
        if(datas!=null){
            mData.addAll(datas);
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.show_item, viewGroup, false);
        ViewHolderShow holderShow = new ViewHolderShow(view);
        return holderShow;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolderShow holderShow = (ViewHolderShow) viewHolder;
        holderShow.showTitle.setText(mData.get(i).getTitle());
        holderShow.showPrice.setText("价格:￥"+mData.get(i).getPrice());
        String image = mData.get(i).getImages().split("\\|")[0].replace("https","http");
        holderShow.showSimple.setImageURI(Uri.parse(image));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolderShow extends RecyclerView.ViewHolder {
        @BindView(R.id.show_simple)
        SimpleDraweeView showSimple;
        @BindView(R.id.show_title)
        TextView showTitle;
        @BindView(R.id.show_price)
        TextView showPrice;
        public ViewHolderShow(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public interface ShowCall{

    }
}
