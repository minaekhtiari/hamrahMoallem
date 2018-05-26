package fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import apps.hillavas.com.hamrahdars.FirstContentActivity;
import apps.hillavas.com.hamrahdars.R;
import classes.models.Level;
import classes.models.ResultJson;
import classes.models.SignUpMemberModel;
import classes.tools.ConnectionChecker;
import classes.tools.helpers.RetrofitFactory;
import factories.FragmentHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by mohsen.mohammadi on 6/21/2017.
 */

public class Fragment_Register extends Fragment implements
        View.OnClickListener{

    public static final String MOBILE_NUMBER="MOBILE_NUMBER";
    public static final String PASSWORD="PASSWORD";
    public static final String RESULTCODE="RESULTCODE";
    public static final String GUID="GUID";
    SharedPreferences sharedPreferencesHome;

    EditText editMobileNumber;
    EditText editPassword;

    ImageView imageFemale;
    ImageView imageMale;

    LinearLayout linearImageMale;
    LinearLayout linearImageFemale;

    TextView tvMale;
    TextView tvFemale;

    Button btnRegister;

    int sex = 0;
    int marriageState = 1;
//
//    ImageView ivPassLock;
//    ImageView ivPassEye;
    Vibrator vibrator;
    Typeface tfIranSans;

    Spinner spinnerMaghta;
    Spinner spinnerPayeh;

    int positionMaghta = 0;
    int positionPayeh = 1;

    List<String> payehLst = new ArrayList<>();
    EditText editName;
    EditText editFamily;
    List<Level> levels = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_justmobile, container , false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        getActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        tfIranSans = Typeface.createFromAsset(getContext().getAssets() , "fonts/iransans.ttf");
        sharedPreferencesHome = PreferenceManager.getDefaultSharedPreferences(getActivity());
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        editMobileNumber = (EditText) getActivity().findViewById(R.id.fragment_register_EditMobile);
//        editPassword = (EditText) getActivity().findViewById(R.id.fragment_register_EditPassword);
//        editPassword.setInputType( InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD );
//        editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
//        editPassword.setTypeface(tfIranSans);
        editName = (EditText) getActivity().findViewById(R.id.fragment_register_EditName);
        editFamily = (EditText) getActivity().findViewById(R.id.fragment_register_EditFamily);
        linearImageMale = (LinearLayout) getActivity().findViewById(R.id.fragment_register_linear_male);
        linearImageFemale = (LinearLayout) getActivity().findViewById(R.id.fragment_register_linear_female);
        spinnerMaghta = (Spinner) getActivity().findViewById(R.id.fragment_register_spinnerMaghta);
        spinnerPayeh = (Spinner) getActivity().findViewById(R.id.fragment_register_spinnerPayeh);

        linearImageMale.setOnClickListener(this);
        linearImageFemale.setOnClickListener(this);
        final List<String> maghtaLst = new ArrayList<>();
        final List<String> payehListEbtedaee = new ArrayList<>();
        final List<String> payehListmotevasetehAval = new ArrayList<>();
        final List<String> payehListmotevasetehDovom = new ArrayList<>();
        final List<String> payehListpish = new ArrayList<>();
        final List<String> payehListkonkoor = new ArrayList<>();

        final List<Integer> payehListEbtedaeeID = new ArrayList<>();
        final List<Integer> payehListmotevasetehAvalID = new ArrayList<>();
        final List<Integer> payehListmotevasetehDovomID = new ArrayList<>();
        final List<Integer> payehListpishID = new ArrayList<>();
        final List<Integer> payehListkonkoorID = new ArrayList<>();



        maghtaLst.add("ابتدایی");
        maghtaLst.add("متوسطه اول");
        maghtaLst.add("متوسطه دوم");
        maghtaLst.add("پیش دانشگاهی");
        maghtaLst.add("کنکور");

        payehListEbtedaee.add("اول");
        payehListEbtedaee.add("دوم");
        payehListEbtedaee.add("سوم");
        payehListEbtedaee.add("چهارم");
        payehListEbtedaee.add("پنجم");
        payehListEbtedaee.add("ششم");
        payehListEbtedaeeID.add(1);
        payehListEbtedaeeID.add(2);
        payehListEbtedaeeID.add(3);
        payehListEbtedaeeID.add(4);
        payehListEbtedaeeID.add(5);
        payehListEbtedaeeID.add(6);


        payehListmotevasetehAval.add("هفتم");
        payehListmotevasetehAval.add("هشتم");
        payehListmotevasetehAval.add("نهم");
        payehListmotevasetehAvalID.add(7);
        payehListmotevasetehAvalID.add(8);
        payehListmotevasetehAvalID.add(9);


        payehListmotevasetehDovom.add("دهم");
        payehListmotevasetehDovom.add("یازدهم");
        payehListmotevasetehDovom.add("دوازدهم");
        payehListmotevasetehDovomID.add(10);
        payehListmotevasetehDovomID.add(11);
        payehListmotevasetehDovomID.add(12);

        payehListpish.add("پیش دانشگاهی");
        payehListpishID.add(13);
        payehListkonkoor.add("کنکور");
        payehListkonkoorID.add(14);


        ArrayAdapter<String> karant_adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                maghtaLst){
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTextColor(getResources().getColor(R.color.gray_500));
                ((TextView) v).setTextSize(10);
                return v;
            }
        };

        spinnerMaghta.setAdapter(karant_adapter);
        spinnerMaghta.setSelection(0);
        spinnerMaghta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionMaghta = position;
                spinnerPayeh.setEnabled(true);
                if(position == 0){
                    payehLst = new ArrayList<String>();
                    payehLst.addAll(payehListEbtedaee);
                }
                if(position == 1){
                    payehLst = new ArrayList<String>();
                    payehLst.addAll(payehListmotevasetehAval);
                }
                if(position == 2){
                    payehLst = new ArrayList<String>();
                    payehLst.addAll(payehListmotevasetehDovom);
                }
                if(position == 3){
                    payehLst = new ArrayList<String>();
                    payehLst.addAll(payehListpish);
                    spinnerPayeh.setEnabled(false);
                }
                if(position == 4){
                    payehLst = new ArrayList<String>();
                    payehLst.addAll(payehListkonkoor);
                    spinnerPayeh.setEnabled(false);
                }
                ArrayAdapter<String> payehAdapter = new ArrayAdapter<String>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        payehLst){
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View v = super.getView(position, convertView, parent);
                        ((TextView) v).setTextColor(getResources().getColor(R.color.gray_500));
                        TextView tv = new TextView(getActivity());
                        ((TextView) v).setTextSize(10);
                        return v;
                    }
                };
                spinnerPayeh.setAdapter(payehAdapter);
                spinnerPayeh.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerPayeh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(positionMaghta == 0){
                    positionPayeh = position+1;
                }
                if(positionMaghta == 1){
                    positionPayeh = position+7;
                }
                if(positionMaghta == 2){
                    positionPayeh = position+10;
                }
                if(positionMaghta == 3){
                    positionPayeh = 13;
                }
                if(positionMaghta == 4){
                    positionPayeh = 14;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        imageFemale = (ImageView) getActivity().findViewById(R.id.fragment_register_image_female);
        imageMale = (ImageView) getActivity().findViewById(R.id.fragment_register_image_male);

        tvMale = (TextView) getActivity().findViewById(R.id.fragment_register_text_male);
        tvFemale = (TextView) getActivity().findViewById(R.id.fragment_register_text_female);

//        ivPassLock = (ImageView) getActivity().findViewById(R.id.fragment_register_imageLockPass);
//        ivPassEye = (ImageView) getActivity().findViewById(R.id.fragment_register_imageEyePass);
//        ivPassEye.setOnClickListener(this);

        btnRegister = (Button) getActivity().findViewById(R.id.fragment_register_Btn_register);

        btnRegister.setOnClickListener(this);

        editMobileNumber.addTextChangedListener(new AbsListView(getActivity()) {
            @Override
            public ListAdapter getAdapter() {
                return null;
            }

            @Override
            public void setSelection(int position) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);

                if(editMobileNumber.getText().length() > 0)
                    editMobileNumber.setGravity(Gravity.LEFT | Gravity.CENTER);
                else
                    editMobileNumber.setGravity(Gravity.RIGHT | Gravity.CENTER);
            }
        });


//        editPassword.addTextChangedListener(new AbsListView(getActivity()) {
//            @Override
//            public ListAdapter getAdapter() {
//                return null;
//            }
//
//            @Override
//            public void setSelection(int position) {
//
//            }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                super.onTextChanged(s, start, before, count);
//                if(editPassword.getText().length() > 0) {
//                    editPassword.setGravity(Gravity.LEFT | Gravity.CENTER);
//                    ivPassLock.setVisibility(INVISIBLE);
//                    ivPassEye.setVisibility(VISIBLE);
//                }else {
//                    editPassword.setGravity(Gravity.RIGHT | Gravity.CENTER);
//                    ivPassLock.setVisibility(VISIBLE);
//                    ivPassEye.setVisibility(INVISIBLE);
//                }
//            }
//        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.fragment_register_linear_female:{
                if(sex == 1)
                    maleFemaleChanger();
                vibrator.vibrate(65);
                break;
            }
            case R.id.fragment_register_linear_male:{
                if(sex == 0)
                    maleFemaleChanger();
                vibrator.vibrate(65);
                break;
            }

            case R.id.fragment_register_Btn_register :{
                vibrator.vibrate(65);

                if (!ConnectionChecker.check(getActivity())) {
                    Toast.makeText(getActivity(), R.string.noConnection, Toast.LENGTH_SHORT).show();
                    return;
                }

                if(editMobileNumber.length() < 11){
                    Toast.makeText(getActivity(), R.string.errorMobileNumber, Toast.LENGTH_SHORT).show();
                    return;
                }

//                if(editPassword.getText().length() <4 || editMobileNumber.getText().length()<11){
//                    String messageError = null;
//                    if(editPassword.getText().length() <4){
//                        messageError = getString(R.string.errorPassword);
//                    }
//                    if(editMobileNumber.getText().length() < 11){
//                        messageError = getString(R.string.errorMobileNumber);
//                    }
//                    if(editPassword.getText().length() <4 && editMobileNumber.getText().length()<11){
//                        messageError = getString(R.string.errorMobileNumberAndPassword);
//                    }
//                    Toast.makeText(getActivity(), messageError, Toast.LENGTH_SHORT).show();
//                    return;
//                }
                if(editName.getText().length() < 1 || editFamily.getText().length() < 1){
                    Toast.makeText(getActivity(), R.string.errorNameOrFamily, Toast.LENGTH_SHORT).show();
                    return;
                }

                SignUpMemberModel signUpMemberModel = new SignUpMemberModel();
                signUpMemberModel.setMobileNumber(Long.valueOf(editMobileNumber.getText().toString()));
//                signUpMemberModel.setPassword(editPassword.getText().toString());
                if(sex == 1)
                    signUpMemberModel.setSex(true);
                else
                    signUpMemberModel.setSex(false);
                signUpMemberModel.setLevelId(positionPayeh);
                signUpMemberModel.setFirstName(editName.getText().toString());
                signUpMemberModel.setLastName(editFamily.getText().toString());

                btnRegister.setEnabled(false);
                RetrofitFactory.getRetrofitClient().register(signUpMemberModel).enqueue(new Callback<ResultJson>() {
                    @Override
                    public void onResponse(Call<ResultJson> call, Response<ResultJson> response) {
                        if(response != null && response.body() != null) {
                            if(response.body().isIsSuccessfull() && Long.valueOf(response.body().getResult()) == -1 ){
                                sharedPreferencesHome.edit().putLong(MOBILE_NUMBER, Long.valueOf(editMobileNumber.getText().toString())).commit();
                                sharedPreferencesHome.edit().putString(GUID, response.body().getMessage()).commit();
                                Intent intent = new Intent(getActivity(), FirstContentActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                return;
                            }
                            if (response.body().isIsSuccessfull()) {
                                sharedPreferencesHome.edit().putLong(MOBILE_NUMBER, Long.valueOf(editMobileNumber.getText().toString())).commit();
                                sharedPreferencesHome.edit().putString(RESULTCODE, response.body().getResult().toString()).commit();
                                ((ViewPager)getActivity().findViewById(R.id.home_viewPager)).setVisibility(View.INVISIBLE);

                                new FragmentHelper(new Fragment_CodeRequest(),
                                        R.id.frameLayout_base,
                                        getActivity().getSupportFragmentManager()
                                ).replace(false);

                            }else {
                                Toast toast = Toast.makeText(getActivity(), "لطفا دقایقی دیگر اقدام به ثبت نام نمائید", Toast.LENGTH_SHORT);
                                ViewGroup group = (ViewGroup) toast.getView();
                                TextView messageTextView = (TextView) group.getChildAt(0);
                                messageTextView.setTextSize(8);
                                toast.show();
//                                Toast.makeText(getActivity(), "لطفا دقایقی دیگر اقدام به ثبت نام نمائید", Toast.LENGTH_SHORT).show();
                            }
                        }

                        btnRegister.setEnabled(true);
                    }
                    @Override
                    public void onFailure(Call<ResultJson> call, Throwable t) {
                        Toast.makeText(getActivity(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        btnRegister.setEnabled(true);
                    }
                });

                break;
            }
//            case R.id.fragment_register_imageEyePass:{
//                if(editPassword.getInputType() == 18) {
//                    editPassword.setInputType(2);
//                    vibrator.vibrate(65);
//                    editPassword.setTypeface(tfIranSans);
//                }else if(editPassword.getInputType() == 2) {
//                    editPassword.setInputType(18);
//                    vibrator.vibrate(65);
//                    editPassword.setTypeface(tfIranSans);
//                }break;
//            }

        }

    }


    private void maleFemaleChanger(){
        if(sex == 0) {
            imageMale.setImageResource(R.drawable.male_red);
            imageFemale.setImageResource(R.drawable.female_gray);
            tvMale.setTextColor(getResources().getColor(R.color.red));
            tvFemale.setTextColor(getResources().getColor(R.color.gray));
            sex = 1;
        }else{
            imageMale.setImageResource(R.drawable.male_gray);
            imageFemale.setImageResource(R.drawable.female_red);
            tvMale.setTextColor(getResources().getColor(R.color.gray));
            tvFemale.setTextColor(getResources().getColor(R.color.red));
            sex = 0;
        }
    }



}




