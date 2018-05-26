package adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import apps.hillavas.com.hamrahdars.ArticleTextView;
import apps.hillavas.com.hamrahdars.R;
import classes.Home_Pager_Page;
import classes.models.FileGiver;
import classes.models.FileResult;
import classes.models.Record;
import classes.models.ResultJson;
import classes.tools.helpers.RetrofitFactory;
import classes.tools.helpers.RetrofitFactoryForFileManager;
import fragments.Fragment_ArticleTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mohsen.mohammadi on 6/24/2017.
 */

public class RecyclerView_record_adapter extends RecyclerView.Adapter<RecyclerView_record_adapter.MVHolder> {


    private static final String CONTENT = "CONTENT";
    private static final String TOKEN = "TOKEN";

    Context context;
    List<Record> records = new ArrayList<>();
    LayoutInflater inflater;
    String token;

    public RecyclerView_record_adapter(Context context, List<Record> records , String token) {
        this.context = context;
        this.records = records;
        inflater = LayoutInflater.from(context);
        this.token = token;
    }

    @Override
    public MVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_content , parent , false);
        return new MVHolder(view);
    }

    @Override
    public void onBindViewHolder(MVHolder holder, int position) {
        Record record = records.get(position);
        holder.setData(record,position);
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    class MVHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView tvTitr;
        TextView tvDescription;

        public MVHolder(View itemView) {
            super(itemView);
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/iransans.ttf");
            imageView = (ImageView) itemView.findViewById(R.id.content_image);
            tvTitr = (TextView) itemView.findViewById(R.id.content_text_titr);
            tvDescription = (TextView) itemView.findViewById(R.id.content_text_description);
            tvDescription.setTypeface(typeface);
            tvTitr.setTypeface(typeface);

        }

        private void setData(Record record , int position){
            if(record.getThumbnailImageId() != null && record.getThumbnailImageId().length() > 0){
                RetrofitFactoryForFileManager.getRetrofitClient().getFiles(record.getThumbnailImageId() , 1).enqueue(new Callback<FileGiver>() {
                    @Override
                    public void onResponse(Call<FileGiver> call, Response<FileGiver> response) {

                        if(response.body().isIsSuccessfull()){
                            FileResult result = response.body().getFileResult();
                            if(result  != null){
                                Glide.with(context)
                                        .load(result.getFileAddress())
                                        .asBitmap()
                                        .override(240,320)
                                        .centerCrop()
                                        .into(imageView);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<FileGiver> call, Throwable t) {

                    }
                });
            }
            String description = null;
            description = Html.fromHtml(record.getBody()).toString();
            if(description.length() > 50)
                description = description.substring(0,50) + " ...";
            tvTitr.setText(record.getSubject());
            tvDescription.setText(description);

        }

        @Override
        public void onClick(View v) {

//            final int contentId = homePagePagerList.get(getAdapterPosition()).getId();
//            RetrofitFactory.getRetrofitClient().getContentViewCount(token , contentId).enqueue(new Callback<ResultJson>() {
//                @Override
//                public void onResponse(Call<ResultJson> call, Response<ResultJson> response) {
//                    //homePagePagerList.get(getAdapterPosition()).setViewCount(homePagePagerList.get(contentId).getViewCount() +1);
//                }
//
//                @Override
//                public void onFailure(Call<ResultJson> call, Throwable t) {
//
//                }
//            });
//
//            Bundle bundle = new Bundle();
//            bundle.putParcelable(CONTENT , (Parcelable) homePagePagerList.get(getAdapterPosition()));
//            bundle.putString(TOKEN , token);
//            Fragment_ArticleTextView articleTextView = new Fragment_ArticleTextView();
//            articleTextView.setArguments(bundle);
//       Intent intent = new Intent(context , ArticleTextView.class);
//       intent.putExtras(bundle);
//       context.startActivity(intent);
         }
        }

}






