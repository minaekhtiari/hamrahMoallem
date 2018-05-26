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









//        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
//
//// Create items
//        AHBottomNavigationItem item1 = new AHBottomNavigationItem("1", android.R.drawable.ic_dialog_alert , R.color.bottomBarColor);
//        AHBottomNavigationItem item2 = new AHBottomNavigationItem("2",  android.R.drawable.ic_dialog_alert , R.color.bottomBarColor);
//        AHBottomNavigationItem item3 = new AHBottomNavigationItem("3",  android.R.drawable.ic_dialog_alert , R.color.bottomBarColor);
//
//// Add items
//        bottomNavigation.addItem(item1);
//        bottomNavigation.addItem(item2);
//        bottomNavigation.addItem(item3);
//
//// Set background color
//        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
//
//// Disable the translation inside the CoordinatorLayout
//        bottomNavigation.setBehaviorTranslationEnabled(false);
//
//// Enable the translation of the FloatingActionButton
////        bottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);
//
//// Change colors
//        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
//        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
//
//// Force to tint the drawable (useful for font with icon for example)
//        bottomNavigation.setForceTint(true);
//
//// Display color under navigation bar (API 21+)
//// Don't forget these lines in your style-v21
//// <item name="android:windowTranslucentNavigation">true</item>
//// <item name="android:fitsSystemWindows">true</item>
//        bottomNavigation.setTranslucentNavigationEnabled(true);
//
//// Manage titles
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
//
//// Use colored navigation with circle reveal effect
//        bottomNavigation.setColored(true);
//
//// Set current item programmatically
//        bottomNavigation.setCurrentItem(1);
//
//// Customize notification (title, background, typeface)
//        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));
//
//// Add or remove notification for each item
////        bottomNavigation.setNotification("1", 3);
//// OR
//        AHNotification notification = new AHNotification.Builder()
//                .setText("1")
//                .setBackgroundColor(ContextCompat.getColor(this, R.color.gray_500))
//                .setTextColor(ContextCompat.getColor(this, R.color.gray_500))
//                .build();
//        bottomNavigation.setNotification(notification, 1);
//
//// Enable / disable item & set disable color
//        bottomNavigation.enableItemAtPosition(2);
//        bottomNavigation.disableItemAtPosition(2);
//        bottomNavigation.setItemDisableColor(Color.parseColor("#3A000000"));
//
//// Set listeners
//        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
//            @Override
//            public boolean onTabSelected(int position, boolean wasSelected) {
//                // Do something cool here...
//                return true;
//            }
//        });
//        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
//            @Override public void onPositionChange(int y) {
//                // Manage the new y position
//            }
//        });



//        token = "658DF8F9-160A-4ED2-90BB-EB17D1661EC5";
//        sharedPreferencesHome.edit().putString(GUID , token).commit();

        if(token != null && token.length() > 0)
//            transaction.replace(R.id.frameLayout_base , new Fragment_Password()).commit();
            transaction.replace(R.id.frameLayout_base , new Fragment_Splash()).commit();
        else
            transaction.replace(R.id.frameLayout_base , new Fragment_RegisterJustMobile()).commit();
//        else {
//            viewPagerHome.setVisibility(View.VISIBLE);
//            viewPagerHome.setAdapter(new Fragment_Pager_adapter_show_case(getSupportFragmentManager()));
//            tvIKnow.setVisibility(View.VISIBLE);
//            tvIKnow.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    viewPagerHome.setCurrentItem(4);
//                    tvIKnow.setVisibility(View.INVISIBLE);
//                }
//            });
//            viewPagerHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                @Override
//                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                    if(position >3)
//                        tvIKnow.setVisibility(View.INVISIBLE);
//                    else
//                        tvIKnow.setVisibility(View.VISIBLE);
//                }
//
//                @Override
//                public void onPageSelected(int position) {
//
//                }
//
//                @Override
//                public void onPageScrollStateChanged(int state) {
//
//                }
//            });
////            transaction.replace(R.id.frameLayout_base, new Fragment_Register()).commit();
////            sharedPreferencesHome.edit().putLong(MOBILE_NUMBER, 0).commit();
////            sharedPreferencesHome.edit().putString(PASSWORD, "").commit();
//        }

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
