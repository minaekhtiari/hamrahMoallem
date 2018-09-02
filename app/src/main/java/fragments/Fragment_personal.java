package fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hillavas.filemanaging.classes.FileForUpload;
import com.hillavas.filemanaging.helpers.FileManagerHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import apps.hillavas.com.hamrahdars.FirstContentActivity;
import apps.hillavas.com.hamrahdars.R;
import classes.models.ProfileInfo;
import classes.models.ProfileNew;
import classes.models.Record;
import classes.models.ResultJson;
import classes.models.ResultUploadFileResponse;
import classes.tools.helpers.RetrofitFactory;
import factories.FragmentHelper;

/**
 * Created by A.Mohammadi on 1/10/2018.
 */

public class Fragment_personal extends Fragment implements View.OnClickListener  {

    public static final String PICTURE_PROFILE_ADDRESS="PICTURE_PROFILE_ADDRESS";
    private static final int CAMERA_REQUEST = 1888;
    public static final String GUID="GUID";
    SharedPreferences sharedPreferencesHome;
    String token=null;

    TextView tvCreditAmount;
    TextView tvName;
    TextView tvMaghta;
    TextView tvSex;
    TextView tvService;
    TextView tvUser;
    TextView tv;

    Dialog dialogeditinfo;
    LinearLayout linearMale;
    LinearLayout linearFeMale;
    EditText editName;
    EditText editFamily;

    ImageView ivMale;
    ImageView ivFeMale;
    TextView tvMale;
    TextView tvFeMale;

    String name = null;
    String family = null;
    boolean sex = true;
    ImageView personalImage;
    private Bitmap bmpImage = null;
    private String fileUrl = null ;
    private String fileAddress = null ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.personal , container , false);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sharedPreferencesHome = PreferenceManager.getDefaultSharedPreferences(getActivity());
        getActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        token = sharedPreferencesHome.getString(GUID, "");
        personalImage = (ImageView) getActivity().findViewById(R.id.personal_image_profile);
        tvCreditAmount = (TextView) getActivity().findViewById(R.id.personal_text_credit);
        tvName = (TextView) getActivity().findViewById(R.id.personal_text_name);
        tvMaghta = (TextView) getActivity().findViewById(R.id.personal_text_maghta);
        tvSex = (TextView) getActivity().findViewById(R.id.personal_text_sex);
        tvService = (TextView) getActivity().findViewById(R.id.personal_text_serviceActive);
        tvUser = (TextView) getActivity().findViewById(R.id.personal_text_userActive);
        tv = (TextView) getActivity().findViewById(R.id.personal_text);


        ((RelativeLayout)getActivity().findViewById(R.id.personal_relative_about)).setOnClickListener(this);
        ((RelativeLayout)getActivity().findViewById(R.id.personal_relative_message)).setOnClickListener(this);
        ((RelativeLayout)getActivity().findViewById(R.id.personal_relative_report)).setOnClickListener(this);
        ((RelativeLayout)getActivity().findViewById(R.id.personal_relative_exitAccount)).setOnClickListener(this);
        ((LinearLayout)getActivity().findViewById(R.id.personal_linear_addCredit)).setOnClickListener(this);

        tv.setText("مشترک گرامی\n" +
                "هزینه روزانه سرویس همراه\u200Cمعلم 500 تومان است که معادل 5000 تومان اعتبار درون\u200Cبرنامه\u200Cای به اعضای سرویس تعلق می\u200Cگیرد.\n" +
                "چنانچه عضو سرویس نیستید و قصد استفاده از دروس ویدئویی همراه\u200Cمعلم را دارید، با ارسال 2 به 307574 می\u200Cتوانید عضو سرویس شوید.");

        if(sharedPreferencesHome.getString(PICTURE_PROFILE_ADDRESS , "").length() > 0) {
            Glide.with(getActivity())
                    .load(sharedPreferencesHome.getString(PICTURE_PROFILE_ADDRESS, ""))
                    .asBitmap()
                    .override(424, 240)
                    .centerCrop()
                    .into(personalImage);
        }

        personalImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                getActivity().startActivityForResult(cameraIntent , CAMERA_REQUEST);
            }
        });


        ((TextView)getActivity().findViewById(R.id.personal_image_edit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogeditinfo = new Dialog(getActivity());
                dialogeditinfo.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialogeditinfo.setContentView(R.layout.dialog_edit_personalinfo);
                linearMale = (LinearLayout) dialogeditinfo.findViewById(R.id.dialog_edit_personalinfo_linear_male);
                linearFeMale = (LinearLayout) dialogeditinfo.findViewById(R.id.dialog_edit_personalinfo_linear_female);
                editName = (EditText) dialogeditinfo.findViewById(R.id.dialog_edit_personalinfo_name);
                editFamily = (EditText) dialogeditinfo.findViewById(R.id.dialog_edit_personalinfo_family);

                ivMale = (ImageView) dialogeditinfo.findViewById(R.id.dialog_edit_personalinfo_image_male);
                ivFeMale = (ImageView) dialogeditinfo.findViewById(R.id.dialog_edit_personalinfo_image_female);
                tvMale = (TextView) dialogeditinfo.findViewById(R.id.dialog_edit_personalinfo_text_male);
                tvFeMale = (TextView) dialogeditinfo.findViewById(R.id.dialog_edit_personalinfo_text_female);

                editName.setText(name);
                editFamily.setText(family);
                maleFemaleChanger();

                linearFeMale.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sex=false;
                        maleFemaleChanger();
                    }
                });
                linearMale.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sex=true;
                        maleFemaleChanger();
                    }
                });
                    ((ImageView) dialogeditinfo.findViewById(R.id.dialog_edit_image_accept)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ProfileNew pn = new ProfileNew();
                            pn.setToken(token);
                            pn.setFirstName(editName.getText().toString());
                            pn.setLastName(editFamily.getText().toString());
                            pn.setSex(sex);
                            new TaskUpdateProfile().execute(pn);
                            dialogeditinfo.hide();

                        }
                    });

                    ((ImageView) dialogeditinfo.findViewById(R.id.dialog_edit_image_cancel)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogeditinfo.hide();
                        }
                    });

                dialogeditinfo.show();
            }
        });

    }
    private void resetMaleFemal(){
        ivMale.setImageResource(R.drawable.male_gray);
        ivFeMale.setImageResource(R.drawable.female_gray);
        tvMale.setTextColor(getResources().getColor(R.color.gray));
        tvFeMale.setTextColor(getResources().getColor(R.color.gray));
    }

    private void maleFemaleChanger(){
        //resetMaleFemal();
        if(sex) {
            ivMale.setImageResource(R.drawable.male_red);
            ivFeMale.setImageResource(R.drawable.female_gray);
            tvMale.setTextColor(getResources().getColor(R.color.red));
            tvFeMale.setTextColor(getResources().getColor(R.color.gray));
            //sex = true;
        }else{
            ivMale.setImageResource(R.drawable.male_gray);
            ivFeMale.setImageResource(R.drawable.female_red);
            tvMale.setTextColor(getResources().getColor(R.color.gray));
            tvFeMale.setTextColor(getResources().getColor(R.color.red));
           // sex = false;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            removeFragments();

            new TaskLoadProfileInfo().execute();

            if(sharedPreferencesHome.getString(PICTURE_PROFILE_ADDRESS , "").length() > 0) {
                Glide.with(getActivity())
                        .load(sharedPreferencesHome.getString(PICTURE_PROFILE_ADDRESS, ""))
                        .asBitmap()
                        .override(424, 240)
                        .centerCrop()
                        .into(personalImage);
            }
        }
        else {
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.personal_relative_about: {
                ((FrameLayout)getActivity().findViewById(R.id.first_frame)).setVisibility(View.VISIBLE);
                removeFragments();
                new FragmentHelper(
                        new Fragment_AboutUs(),
                        R.id.first_frame,
                        getActivity().getSupportFragmentManager()
                ).add(true);
                break;
            }
            case R.id.personal_relative_message: {
                ((FrameLayout)getActivity().findViewById(R.id.first_frame)).setVisibility(View.VISIBLE);
                removeFragments();
//                new FragmentHelper(
//                        new Fragment_Messages(),
//                        R.id.first_frame,
//                        getActivity().getSupportFragmentManager()
//                ).replace(true);

                ((FirstContentActivity)getActivity()).gotoMessaging();

                break;
            }
            case R.id.personal_relative_report:
                ((FrameLayout)getActivity().findViewById(R.id.first_frame)).setVisibility(View.VISIBLE);
                removeFragments();
                new FragmentHelper(
                        new Fragment_buying_report(),
                        R.id.first_frame,
                        getActivity().getSupportFragmentManager()
                ).replace(true);
                break;

//            case R.id.personal_linear_addCredit: {
//                ((FrameLayout)getActivity().findViewById(R.id.first_frame)).setVisibility(View.VISIBLE);
//                removeFragments();
//                new FragmentHelper(
//                        new Fragment_add_credit(),
//                        R.id.first_frame,
//                        getActivity().getSupportFragmentManager()
//                ).replace(true);
//                break;
//            }
            //خروج
            case R.id.personal_relative_exitAccount: {
//                getActivity().finish();
//                break;
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("همراه معلم");
               // builder.setIcon(R.);
                builder.setMessage("آیا میخواهید خارج شوید؟")
                        .setCancelable(false)
                        .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                              getActivity().finish();
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
        }
    }

    public void removeFragments(){
        if(getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0)
            getActivity().getSupportFragmentManager().popBackStack();
        else if(getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
    }

    class TaskLoadProfileInfo extends AsyncTask<Void,Void,ProfileInfo> {

        Dialog dialog = new Dialog(getActivity());
        List<Record> records = new ArrayList<>();
        @Override
        protected ProfileInfo doInBackground(Void... params) {
            ProfileInfo profileInfo=  new ProfileInfo();
            try {
                if (RetrofitFactory.getRetrofitClient().getProfileInfo(token).execute().body().isIsSuccessfull())
                    profileInfo = RetrofitFactory.getRetrofitClient().getProfileInfo(token).execute().body().getResult();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return profileInfo;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setContentView(R.layout.dialog_buesy);
            dialog.setCancelable(false);
            dialog.show();

        }

        @Override
        protected void onPostExecute(ProfileInfo profileInfo) {
            super.onPostExecute(profileInfo);

            dialog.hide();
            tvCreditAmount.setText(profileInfo.getCredit()+"");
            tvName.setText(profileInfo.getFirstName() + " " + profileInfo.getLastName());
            tvMaghta.setText(profileInfo.getLevelTitle());

            if(profileInfo.isSex())
                tvSex.setText("مرد");
            else
                tvSex.setText("زن");

            if(profileInfo.isIsServiceOn())
                tvService.setText("فعال");
            else
                tvService.setText("غیر فعال");

            if(profileInfo.isIsBlock())
                tvUser.setText("غیر فعال");
            else
                tvUser.setText("فعال");


            name = profileInfo.getFirstName();
            family = profileInfo.getLastName();
            sex = profileInfo.isSex();
        }
        }


    class TaskUpdateProfile extends AsyncTask<ProfileNew,Void,Boolean> {

        @Override
        protected Boolean doInBackground(ProfileNew... profileNews) {
            try {
                ResultJson rj = RetrofitFactory.getRetrofitClient().updateProfile(profileNews[0]).execute().body();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean) {
                Toast.makeText(getActivity(), "ثبت شد", Toast.LENGTH_SHORT).show();
                ((ViewPager)getActivity().findViewById(R.id.view_pager)).setCurrentItem(0);
                ((ViewPager)getActivity().findViewById(R.id.view_pager)).setCurrentItem(4);
            }
            else
                Toast.makeText(getActivity(), "در ثبت شد خطا رخ داد", Toast.LENGTH_SHORT).show();
        }
    }

    class TaskUploadFile extends AsyncTask<FileForUpload , Void , JSONObject> {

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
            if(jsonObject != null){
                Gson gson = new Gson();
                ResultUploadFileResponse resultUploadResponse = gson.fromJson(jsonObject.toString() ,ResultUploadFileResponse.class);
                fileUrl = resultUploadResponse.getFileID();
                //new TaskLoadImageAddress().execute(fileUrl);
            }
        }
    }





    class TaskLoadImageAddress extends AsyncTask<String , Void , String>{

        @Override
        protected String doInBackground(String... params) {
            return FileManagerHelper.getOneFileAddress(params[0] , "image");
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            fileAddress = s;
            sharedPreferencesHome.edit().putString(PICTURE_PROFILE_ADDRESS , s).commit();
            Glide.with(getActivity())
                    .load(fileAddress)
                    .asBitmap()
                    .override(424,240)
                    .centerCrop()
                    .into(personalImage);

        }
    }


}
