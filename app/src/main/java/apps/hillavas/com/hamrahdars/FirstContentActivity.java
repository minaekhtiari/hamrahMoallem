package apps.hillavas.com.hamrahdars;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hillavas.filemanaging.classes.FileForUpload;
import com.hillavas.filemanaging.helpers.FileManagerHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import adapters.BottomNavigationAdapter;
import classes.models.AndroidUtils;
import classes.models.Device;
import classes.models.ResultJson;
import classes.models.ResultUploadFileResponse;
import classes.models.SubscribeModel;
import classes.tools.helpers.RetrofitFactory;
import factories.FragmentHelper;
import fragments.Fragment_AboutUs;
import fragments.Fragment_Menu;
import fragments.Fragment_Messages;
import fragments.Fragment_consultant;
import fragments.Fragment_pager;
import fragments.Fragment_personal;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

import static apps.hillavas.com.hamrahdars.R.drawable.view;
import static apps.hillavas.com.hamrahdars.R.id.bottom_navigation;
import static apps.hillavas.com.hamrahdars.R.id.view_pager;

public class FirstContentActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    private static final String LEVEL_ID = "LEVEL_ID";
    private static final String CATEGORYID_ID = "CATEGORYID_ID";
    private static final String HAS_CHILD = "HAS_CHILD";
    private static final String SELECTED_CATEGORYID_ID = "SELECTED_CATEGORYID_ID";
    public static final String SENT_MESSAGE = "SENT_MESSAGE";
    public static final String GUID = "GUID";
    public static final String UNREAD_ANSWERS = "UNREAD_ANSWERS";
    public static final String PICTURE_PROFILE_ADDRESS = "PICTURE_PROFILE_ADDRESS";


    SharedPreferences sharedPreferencesHome;
    String token = null;
    String title = "";
    int id = 0;
    int newAnswerCount = 0;
    Vibrator vibrator;

    RecyclerView recyclerViewCategories;
    RecyclerView recyclerViewContents;
    int levelId = 0;
    int categoryId = 0;
    boolean hasChild = true;
    LinearLayoutManager linearLayoutManagerCategories;
    LinearLayoutManager linearLayoutManagerContents;
    int catIdFromBundle = 0;
    static ViewPager viewPager;
    static AHBottomNavigation bottomNavigation;
    private Bitmap bmpImage = null;
    private String fileUrl = null;
    private String fileAddress = null;
    ImageView imagePersonalPictureFromFragment;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_content);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        sharedPreferencesHome = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        token = sharedPreferencesHome.getString(GUID, "");
        title = getIntent().getStringExtra("MENU_ITEM");
        id = getIntent().getIntExtra("MENU_ID", 0);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.setStatusBarColor(getResources().getColor(R.color.statusBarColor));


        viewPager = (ViewPager) findViewById(view_pager);
//        Bundle bundleGive = getIntent().getExtras();
//        if(bundleGive != null && bundleGive.containsKey("CATEGORY_ID_SELECTED"))
//            catIdFromBundle = bundleGive.getInt("CATEGORY_ID_SELECTED");
//
//        Bundle bundle = new Bundle();
//        bundle.putInt("CATEGORY_ID_SELECTED" , catIdFromBundle);
//        Fragment_pager fragment_pager = new Fragment_pager();
//        fragment_pager.setArguments(bundle);


        BottomNavigationAdapter adapter = new BottomNavigationAdapter(getSupportFragmentManager());
//        viewPager.setPagingEnabled(false);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setCurrentItem(1);
        //viewPager.setCurrentItem(3);

//        for (int i = 0; i < adapter.getCount(); i++) {
//            bottomNavigation.addItem(new AHBottomNavigationItem(adapter.getPageTitle(i).toString(), adapter.getPageIcon(i)));
//        }
//        //bottomNavigation.setCurrentItem(3);
//
//        //bottomNavigation.setBehaviorTranslationEnabled(true);
//        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#D4D5D7"));
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
//        bottomNavigation.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
//        // bottomNavigation.setNotification("1", 2);
//
//

        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

// Create items
//        AHBottomNavigationItem item1 = new AHBottomNavigationItem("مشاوره", R.drawable.bottombar_icon_consultant);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("پیام ها", R.drawable.bottombar_icon_message);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("درسی", R.drawable.bottombar_icon_content);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("مشاوره ای", R.drawable.bottombar_icon_movie);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem("پروفایل", R.drawable.bottombar_icon_personal);

// Add items
//        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item5);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item2);


        bottomNavigation.setDefaultBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        bottomNavigation.setAccentColor(Color.parseColor("#000000"));
        bottomNavigation.setInactiveColor(Color.parseColor("#FFFFFF"));

        bottomNavigation.setTranslucentNavigationEnabled(true);

        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        bottomNavigation.setCurrentItem(1);

        bottomNavigation.setItemDisableColor(getResources().getColor(R.color.white));

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                    getSupportFragmentManager().popBackStack();
                else if (getFragmentManager().getBackStackEntryCount() > 0)
                    getFragmentManager().popBackStack();

                ((FrameLayout) findViewById(R.id.first_frame)).setVisibility(View.INVISIBLE);
                viewPager.setCurrentItem(position, false);

//                View view = HomeActivity.this.getCurrentFocus();
//                if (view != null) {
//                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//                }

                return true;
            }
        });

        imagePersonalPictureFromFragment = (ImageView) findViewById(R.id.personal_image_profile);
        getDeviceinfo();
    }

    private void getDeviceinfo() {

        Device device = new Device();
        AndroidUtils androidUtils = new AndroidUtils(this);
        device.setManufacture(String.valueOf(androidUtils.getManufacturer()));
        device.setCpu(androidUtils.getCpu());
        device.setOperator(androidUtils.getSimOperator());
        device.setUUID(androidUtils.getUUID());
        device.setApiVersion(String.valueOf(androidUtils.getAndroidVersionCode()));
        device.setOsVersion(androidUtils.getAndroidVersionName());
        device.setDpi(androidUtils.getDpi());
        device.setResolotion(androidUtils.getResolution());
        device.setProductName(androidUtils.getBrandName());
        device.setModelName(androidUtils.getModelName());
        device.setRam(androidUtils.getRam());
        device.setHardware(androidUtils.getHardware());
        device.setNetworkName(androidUtils.getModelName());
        device.setNetworkType(androidUtils.getNetworkType());
        device.setOsName("Android");


        RetrofitFactory.getRetrofitClient().getDeviceifo(device).enqueue(new Callback<ResultJson>() {
            @Override
            public void onResponse(Call<ResultJson> call, Response<ResultJson> response) {
                // Toast.makeText(FirstContentActivity.this,response.toString(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<ResultJson> call, Throwable t) {
                // Toast.makeText(FirstContentActivity.this,t.toString(),Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] byteArrayImage = baos.toByteArray();
            String encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
            FileForUpload fileForUpload = new FileForUpload();
            fileForUpload.setFilename("ProfilePicture.jpg");
            fileForUpload.setUsername("Hillavas_Youga");
            fileForUpload.setPassword("H!ll@v@s_Ug@");
            fileForUpload.setStringBase64(encodedImage);
            new TaskUploadFile().execute(fileForUpload);

        }
    }

    class TaskUploadFile extends AsyncTask<FileForUpload, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(FileForUpload... params) {
            try {
                return FileManagerHelper.fileUpload(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            if (jsonObject != null) {
                Gson gson = new Gson();
                ResultUploadFileResponse resultUploadResponse = gson.fromJson(jsonObject.toString(), ResultUploadFileResponse.class);
                fileUrl = resultUploadResponse.getFileID();
                new TaskLoadImageAddress().execute(fileUrl);

            }
        }
    }

    class TaskLoadImageAddress extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return FileManagerHelper.getOneFileAddress(params[0], "image");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            fileAddress = s;
            sharedPreferencesHome.edit().putString(PICTURE_PROFILE_ADDRESS, s).commit();

            viewPager.setCurrentItem(3);
            viewPager.setCurrentItem(4);
        }
    }

    public void removeFragments() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        else if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
    }

    @Override
    public void onBackPressed() {

//        Toast.makeText(this, "" + getSupportFragmentManager().getBackStackEntryCount() + " or " +
//                getFragmentManager().getBackStackEntryCount(), Toast.LENGTH_SHORT).show();

        if (getSupportFragmentManager().getBackStackEntryCount() == 1)
            ((FrameLayout) findViewById(R.id.first_frame)).setVisibility(View.INVISIBLE);
        if (getFragmentManager().getBackStackEntryCount() == 1)
            ((FrameLayout) findViewById(R.id.first_frame)).setVisibility(View.INVISIBLE);


        if (getSupportFragmentManager().getBackStackEntryCount() > 0)

            getSupportFragmentManager().popBackStack();

        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("همراه معلم");
            // builder.setIcon(R.);
            builder.setMessage("آیا میخواهید خارج شوید؟")
                    .setCancelable(false)
                    .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    .setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
//        return;
    }

    public static void gotoMessaging() {
        bottomNavigation.setCurrentItem(3);
        viewPager.setCurrentItem(3);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}

