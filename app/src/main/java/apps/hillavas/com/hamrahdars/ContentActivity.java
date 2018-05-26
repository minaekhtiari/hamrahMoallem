package apps.hillavas.com.hamrahdars;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapters.RecyclerView_record_adapter;
import classes.Home_Pager_Page;
import classes.models.Record;
import classes.tools.helpers.RetrofitFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ContentActivity extends AppCompatActivity {

    private static final String CATEGORYID_ID = "CATEGORYID_ID";
    public static final String GUID="GUID";

    int categoryId = 0;
    SharedPreferences sharedPreferencesHome;
    String token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.setStatusBarColor(getResources().getColor(R.color.statusBarColor));

        sharedPreferencesHome = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        token = sharedPreferencesHome.getString(GUID, "");

        if(getIntent() != null){
            if(getIntent().getExtras().containsKey(CATEGORYID_ID)){
                categoryId  = getIntent().getIntExtra(CATEGORYID_ID , 0);
            }
        }

        if(categoryId > 0){
            new TaskLoadContents().execute(categoryId);
        }
    }

    class TaskLoadContents extends AsyncTask<Integer,Void,List<Record>> {

        List<Record> records = new ArrayList<>();
        List<Home_Pager_Page> pages = new ArrayList<>();
        @Override
        protected List<Record> doInBackground(Integer... params) {

            try {
                if (RetrofitFactory.getRetrofitClient().getContents(token,params[0],1,100).execute().body().isIsSuccessfull())
                    records = RetrofitFactory.getRetrofitClient().getContents(token,categoryId,1,100).execute().body().getResult().getRecords();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return records;
        }

        @Override
        protected void onPostExecute(List<Record> records) {
            super.onPostExecute(records);
            if(records != null && records.size() > 0){
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.content_recycler_content);
                LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                RecyclerView_record_adapter records_adapter = new RecyclerView_record_adapter(getApplicationContext() , records , token);
                recyclerView.setAdapter(records_adapter);
            }
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
