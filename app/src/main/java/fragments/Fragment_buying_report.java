package fragments;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import adapters.RecyclerView_Adapter_Purchases;
import adapters.RecyclerView_messagesQuestion_Adapter;
import apps.hillavas.com.hamrahdars.R;
import classes.models.RecordsPurchase;
import classes.models.ResultJsonObject;
import classes.models.ResultJsonPurchase;
import classes.tools.helpers.RetrofitFactory;

/**
 * Created by A.Mohammadi on 1/10/2018.
 */

public class Fragment_buying_report extends Fragment {

    public static final String GUID="GUID";
    SharedPreferences sharedPreferencesHome;
    String token=null;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_buying_report, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        sharedPreferencesHome = PreferenceManager.getDefaultSharedPreferences(getActivity());
        token = sharedPreferencesHome.getString(GUID, "");
        new TaskLoadPurchases().execute();

        ((RelativeLayout) getActivity().findViewById(R.id.aboutUs_imageBack_relative)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    class TaskLoadPurchases extends AsyncTask<Void,Void,List<RecordsPurchase>> {


        @Override
        protected List<RecordsPurchase> doInBackground(Void... voids) {
            ResultJsonPurchase rjo = new ResultJsonPurchase();
            List<RecordsPurchase> rp = new ArrayList<>();
            try {
                if (RetrofitFactory.getRetrofitClient().getAllPurchases(token, 1, 100).execute().body().isIsSuccessfull()) {
                    rp = RetrofitFactory.getRetrofitClient().getAllPurchases(token, 1, 100).execute().body().getResultPurchase().getRecordsPurchase();
                    int y = 0;
                }
            }catch (Exception e){}
            return rp;
        }

        @Override
        protected void onPostExecute(List<RecordsPurchase> recordsPurchases) {
            super.onPostExecute(recordsPurchases);

            if(getActivity() != null) {
                RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.buyingReport_recycler);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                layoutManager.scrollToPosition(0);
                recyclerView.setLayoutManager(layoutManager);
                RecyclerView_Adapter_Purchases recyclerView_adapter_purchases = new RecyclerView_Adapter_Purchases(getActivity(), recordsPurchases , getFragmentManager());
                if (recyclerView_adapter_purchases != null)
                    recyclerView.setAdapter(recyclerView_adapter_purchases);
            }

        }
    }

}
