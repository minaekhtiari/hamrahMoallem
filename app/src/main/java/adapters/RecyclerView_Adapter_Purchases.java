package adapters;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import apps.hillavas.com.hamrahdars.ArticleTextView;
import apps.hillavas.com.hamrahdars.R;
import classes.models.CategoryWithParentChild;
import classes.models.RecordsPurchase;
import classes.models.ResultPurchase;
import factories.FragmentHelper;
import fragments.Fragment_AboutUs;
import fragments.Fragment_ArticleTextView;
import fragments.Fragment_ArticleTextView2;

/**
 * Created by mohsen.mohammadi on 6/24/2017.
 */

public class RecyclerView_Adapter_Purchases extends RecyclerView.Adapter<RecyclerView_Adapter_Purchases.MVHolder> {

    Context context;
    List<RecordsPurchase> list = new ArrayList<>();
    LayoutInflater inflater;
    int row_index = 0;
    android.support.v4.app.FragmentManager fragmentManager;

    public RecyclerView_Adapter_Purchases(Context context, List<RecordsPurchase> list , android.support.v4.app.FragmentManager manager) {
        this.context = context;
        this.list = list;
        this.fragmentManager = manager;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_buy_report , parent , false);
        return new MVHolder(view);
    }

    @Override
    public void onBindViewHolder(final MVHolder holder, final int position) {
        RecordsPurchase rp = list.get(position);
        holder.setData(rp);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MVHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitr;
        TextView tvPrice;
        TextView tvDate;
        RelativeLayout relativeLayout;

        public MVHolder(View itemView) {
            super(itemView);
            tvTitr = (TextView) itemView.findViewById(R.id.card_buy_report_titr);
            tvPrice = (TextView) itemView.findViewById(R.id.card_buy_report_price);
            tvDate = (TextView) itemView.findViewById(R.id.card_buy_report_date);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.card_buy_relative);
            relativeLayout.setOnClickListener(this);
        }

        private void setData(RecordsPurchase rp){
            tvTitr.setText(rp.getContentSubject());
            tvPrice.setText(rp.getPrice()+" ریال");
            tvDate.setText(rp.getInsertDateSh());
        }


        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.card_buy_relative){

                RecordsPurchase rp = list.get(getAdapterPosition());

//                Intent intent = new Intent(getActivity(), ArticleTextView.class);
//                intent.putExtra("CONTENT_ID", record.getContentId());
//                intent.putExtra("CATEGORY_ID", categoryId);
//                ActivityCompat.startActivity(getActivity(), intent,null);

                Bundle bundle = new Bundle();
                bundle.putInt("CONTENT_ID", rp.getContentId());
                Fragment_ArticleTextView2 articleTextView = new Fragment_ArticleTextView2();
                articleTextView.setArguments(bundle);

                new FragmentHelper(
                        articleTextView,
                        R.id.first_frame,
                        fragmentManager
                ).add(false);
            }
        }
    }
}






