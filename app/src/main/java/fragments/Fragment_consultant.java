package fragments;

import android.Manifest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import apps.hillavas.com.hamrahdars.ConsultantActivity;
import apps.hillavas.com.hamrahdars.MessagingActivity;
import apps.hillavas.com.hamrahdars.R;

/**
 * Created by A.Mohammadi on 1/10/2018.
 */

public class Fragment_consultant extends Fragment implements View.OnClickListener{

    public static final String GUID="GUID";
    public static final String UNREAD_ANSWERS="UNREAD_ANSWERS";


    FloatingActionButton fabCall;
    Vibrator vibrator;
    TextView tvNewAnswerCount;
    ImageView ivNewAnswerCountBack;
    RelativeLayout relativeLayoutImageMessage;
    RelativeLayout relativeLayoutImageMenu;
    TextView tvAnswerCountNav;
    ImageView ivAnswerCountNavBack;
    ImageView ivMenu;
    SharedPreferences sharedPreferencesHome;
    String token = null;
    int newAnswerCount = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_consultant , container , false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sharedPreferencesHome = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        ((TextView)getActivity().findViewById(R.id.toolbar_justtitr_text_titr)).setText("مشاوره تلفنی");
//        ivMenu = (ImageView) getActivity().findViewById(R.id.toolbar_home_imageMenu);
//        ivMenu.setImageResource(R.drawable.back_icon);
//        token = sharedPreferencesHome.getString(GUID, "");
//        relativeLayoutImageMessage = (RelativeLayout)getActivity().findViewById(R.id.toolbar_home_imageMessage_relative);
//        relativeLayoutImageMenu = (RelativeLayout)getActivity().findViewById(R.id.toolbar_home_imageMenu_relative);
//        relativeLayoutImageMessage.setOnClickListener(this);
//        relativeLayoutImageMenu.setOnClickListener(this);
//        tvNewAnswerCount = (TextView) getActivity().findViewById(R.id.toolbar_home_text_newMessageCount);
//        ivNewAnswerCountBack = (ImageView) getActivity().findViewById(R.id.toolbar_home_image_newMessageCountBack);
        getActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        Window window = getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.setStatusBarColor(getResources().getColor(R.color.statusBarColor));

        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
//        checkNewAnswerCount();

        fabCall = (FloatingActionButton) getActivity().findViewById(R.id.fragment_home_callWithConsuler_fabCall);
        fabCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vibrator.vibrate(65);
                int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE);

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE},
                            123);

                } else {
                    vibrator.vibrate(165);
                    String number = "99225595";
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + number));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.toolbar_home_imageMenu_relative:{
                getActivity().onBackPressed();
                break;
            }
            case R.id.toolbar_home_imageMessage_relative:{
                Intent in = new Intent(getActivity() , MessagingActivity.class);
                startActivity(in);
                break;
            }

        }
    }

//
//    private void checkNewAnswerCount() {
//        newAnswerCount = sharedPreferencesHome.getInt(UNREAD_ANSWERS , 0);
//        if(newAnswerCount > 0){
//            tvNewAnswerCount.setVisibility(View.VISIBLE);
//            ivNewAnswerCountBack.setVisibility(View.VISIBLE);
//            tvNewAnswerCount.setText(newAnswerCount + "");
//        }else {
//            tvNewAnswerCount.setVisibility(View.INVISIBLE);
//            ivNewAnswerCountBack.setVisibility(View.INVISIBLE);
//        }
//    }
}
