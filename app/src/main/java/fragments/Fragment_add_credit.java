package fragments;


import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapters.RecyclerView_Adapter_Packages;
import adapters.RecyclerView_Adapter_Purchases;
import apps.hillavas.com.hamrahdars.R;
import classes.models.Package;
import classes.models.RecordsPurchase;
import classes.models.ResultJsonPurchase;
import classes.tools.helpers.RetrofitFactory;

/**
 * Created by mohsen.mohammadi on 6/21/2017.
 */

public class Fragment_add_credit extends Fragment {

    public static final String GUID="GUID";
    SharedPreferences sharedPreferencesHome;
    String token=null;

    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_credit, container , false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sharedPreferencesHome = PreferenceManager.getDefaultSharedPreferences(getActivity());
        token = sharedPreferencesHome.getString(GUID, "");
        new TaskLoadPackages().execute();

        ((RelativeLayout) getActivity().findViewById(R.id.aboutUs_imageBack_relative)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    class TaskLoadPackages extends AsyncTask<Void,Void,List<Package>> {


        @Override
        protected List<Package> doInBackground(Void... voids) {

            List<Package> packages = new ArrayList<>();

            try {
                if (RetrofitFactory.getRetrofitClient().getPackages(token).execute().body().isIsSuccessfull()) {
                    packages = RetrofitFactory.getRetrofitClient().getPackages(token).execute().body().getResult();

                    int y = 0;
                }
            }catch (Exception e){}
            return packages;
        }

        @Override
        protected void onPostExecute(List<Package> packages) {
            super.onPostExecute(packages);



            if(getActivity() != null) {
                RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.addCredit_recycler);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                layoutManager.scrollToPosition(0);
                recyclerView.setLayoutManager(layoutManager);
                RecyclerView_Adapter_Packages recyclerView_adapter_packages = new RecyclerView_Adapter_Packages(getActivity(), packages);
                if (recyclerView_adapter_packages != null)
                    recyclerView.setAdapter(recyclerView_adapter_packages);
            }

        }
    }
}
