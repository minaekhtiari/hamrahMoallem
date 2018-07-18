package apps.hillavas.com.hamrahdars;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.hillavas.messaging.helpers.QuestionHelper;

import java.util.List;

import adapters.Fragment_Pager_adapter_show_case;
import fragments.Fragment_Password;
import fragments.Fragment_Register;
import fragments.Fragment_RegisterJustMobile;
import fragments.Fragment_Splash;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HOME extends AppCompatActivity {
    public static final String MOBILE_NUMBER="MOBILE_NUMBER";
    public static final String PASSWORD="PASSWORD";
    public static final String REGISTERED = "REGISTERED";
    public static final String GUID="GUID";
    public static final String UNREAD_ANSWERS="UNREAD_ANSWERS";
    private static final String FONT_SIZE = "FONT_SIZE";
    private static final String STOP_LOADING = "STOP_LOADING";
    SharedPreferences sharedPreferencesHome;
    FragmentTransaction transaction;
    String token = null;
    FrameLayout frameBase;
    FrameLayout frameFinish;
    ViewPager viewPagerHome;
    TextView tvIKnow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        viewPagerHome = (ViewPager) findViewById(R.id.home_viewPager);
        tvIKnow = (TextView) findViewById(R.id.home_iKnow);
        sharedPreferencesHome = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sharedPreferencesHome.edit().putInt(FONT_SIZE , sharedPreferencesHome.getInt(FONT_SIZE , 12)).commit();
        token = sharedPreferencesHome.getString(GUID, "");
        if(token != null){
            new TaskGetUnreadAnswerCount().execute();
        }

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.setStatusBarColor(getResources().getColor(R.color.statusBarColor));
        frameBase = (FrameLayout) findViewById(R.id.frameLayout_base);
        frameFinish = (FrameLayout) findViewById(R.id.frameLayout_finish);
        transaction = getSupportFragmentManager().beginTransaction();


        // check user is subscribe


        if(token != null && token.length() > 0)
            transaction.replace(R.id.frameLayout_base , new Fragment_Splash()).commit();
        else
            transaction.replace(R.id.frameLayout_base , new Fragment_RegisterJustMobile()).commit();

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    class TaskGetUnreadAnswerCount extends AsyncTask<Void , Void , List<Integer>>{
        @Override
        protected List<Integer> doInBackground(Void... params) {
            List<Integer> questionIdsWithNewAnswers = null;
            questionIdsWithNewAnswers = QuestionHelper.getUnreadAnswerQuestionIds(token);
            return questionIdsWithNewAnswers;
        }
        @Override
        protected void onPostExecute(List<Integer> integers) {
            super.onPostExecute(integers);
            if(integers == null)
                return;
            sharedPreferencesHome.edit().putInt(UNREAD_ANSWERS , integers.size()).commit();
        }
    }


}
