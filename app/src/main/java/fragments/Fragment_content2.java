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

import apps.hillavas.com.hamrahdars.MessagingActivity;
import apps.hillavas.com.hamrahdars.R;

/**
 * Created by A.Mohammadi on 1/10/2018.
 */

public class Fragment_content2 extends Fragment {

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
        return inflater.inflate(R.layout.fragment_content2 , container , false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


}
