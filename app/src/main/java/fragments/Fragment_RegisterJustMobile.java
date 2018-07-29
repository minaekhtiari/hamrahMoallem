package fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import apps.hillavas.com.hamrahdars.FirstContentActivity;
import apps.hillavas.com.hamrahdars.R;
import classes.models.ResultJson;
import classes.models.SubscribeModel;
import classes.tools.ConnectionChecker;
import classes.tools.helpers.RetrofitFactory;
import factories.FragmentHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ab.mohammadi on 5/16/2018.
 */

public class Fragment_RegisterJustMobile extends Fragment implements View.OnClickListener {

    Button btnRegister;
    EditText editMobileNumber;
    ProgressBar progressBar;
    public static final String MOBILE_NUMBER="MOBILE_NUMBER";
    public static final String PASSWORD="PASSWORD";
    public static final String RESULTCODE="RESULTCODE";
    public static final String GUID="GUID";
    SharedPreferences sharedPreferencesHome;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_justmobile, container , false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnRegister = (Button) getActivity().findViewById(R.id.fragment_register_Btn_register);
        progressBar = (ProgressBar) getActivity().findViewById(R.id.progressRegister);
        editMobileNumber = (EditText) getActivity().findViewById(R.id.fragment_register_EditMobile);

        btnRegister.setOnClickListener(this);

        sharedPreferencesHome = PreferenceManager.getDefaultSharedPreferences(getActivity());




    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.fragment_register_Btn_register:


                if (!ConnectionChecker.check(getActivity())) {
                    Toast.makeText(getActivity(), R.string.noConnection, Toast.LENGTH_SHORT).show();
                    return;
                }

                if(editMobileNumber.length() < 11){
                    Toast.makeText(getActivity(), R.string.errorMobileNumber, Toast.LENGTH_SHORT).show();
                    return;
                }


                SubscribeModel subscribeModel=new SubscribeModel();
                subscribeModel.setMobileNumber(String.valueOf(editMobileNumber.getText()));

                btnRegister.setEnabled(false);
                progressBar.setVisibility(View.VISIBLE);

                RetrofitFactory.getRetrofitClient().subscribe(subscribeModel).enqueue(new Callback<ResultJson>() {
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
                        progressBar.setVisibility(View.INVISIBLE);

                    }
                    @Override
                    public void onFailure(Call<ResultJson> call, Throwable t) {
                        Toast.makeText(getActivity(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        btnRegister.setEnabled(true);
                        progressBar.setVisibility(View.INVISIBLE);

                    }

                });

                break;

        }
    }
}
