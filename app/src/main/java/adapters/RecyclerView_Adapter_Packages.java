package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import apps.hillavas.com.hamrahdars.R;
import classes.models.Package;
import classes.models.RecordsPurchase;

/**
 * Created by mohsen.mohammadi on 6/24/2017.
 */

public class RecyclerView_Adapter_Packages extends RecyclerView.Adapter<RecyclerView_Adapter_Packages.MVHolder> {

    Context context;
    List<Package> list = new ArrayList<>();
    LayoutInflater inflater;
    int row_index = 0;

    public RecyclerView_Adapter_Packages(Context context, List<Package> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_credit_copen , parent , false);
        return new MVHolder(view);
    }

    @Override
    public void onBindViewHolder(final MVHolder holder, final int position) {
        Package rp = list.get(position);
        holder.setData(rp , position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MVHolder extends RecyclerView.ViewHolder {

        TextView tvTitr;
        TextView tvPrice;
        TextView tvCredit;
        TextView tvDate;
        RelativeLayout relativeLayoutBack;



        public MVHolder(View itemView) {
            super(itemView);
            tvTitr = (TextView) itemView.findViewById(R.id.card_credit_copen_text_package);
            tvPrice = (TextView) itemView.findViewById(R.id.card_credit_copen_text_amount);
            tvCredit = (TextView) itemView.findViewById(R.id.card_credit_copen_text_amountreal);
            tvDate = (TextView) itemView.findViewById(R.id.card_credit_copen_text_date);
            relativeLayoutBack = (RelativeLayout) itemView.findViewById(R.id.card_credit_copen_relative_back);
        }

        private void setData(Package rp , int position){
            tvTitr.setText(rp.getName());
            tvPrice.setText(rp.getPrice()+"");
            tvCredit.setText(rp.getCredit()+"");
            tvDate.setText(rp.getInsertDateSh());

            int colorRes = 0;
            switch(position) {
                case 0: colorRes = R.color.backColor;
                    break;
                case 1: colorRes = R.color.green;
                    break;
                case 2: colorRes = R.color.gray_500;
                    break;
                case 3: colorRes = R.color.blue;
                    break;
                case 4: colorRes = R.color.gray;
                    break;
                case 5: colorRes = R.color.colorAccent;
                    break;
                case 6: colorRes = R.color.colorAccent2;
                    break;
            }

            relativeLayoutBack.setBackgroundColor(colorRes);
        }


    }
}






