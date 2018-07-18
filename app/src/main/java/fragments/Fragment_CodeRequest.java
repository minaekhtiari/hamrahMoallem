package fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import apps.hillavas.com.hamrahdars.FirstContentActivity;
import apps.hillavas.com.hamrahdars.R;
import classes.models.RequestCodeGiverModel;
import classes.models.ResultJson;
import classes.tools.helpers.RetrofitFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mohsen.mohammadi on 6/21/2017.
 */

public class Fragment_CodeRequest extends Fragment {

    public static final String MOBILE_NUMBER="MOBILE_NUMBER";
    public static final String PASSWORD="PASSWORD";
    public static final String GUID="GUID";
    public static final String RESULTCODE="RESULTCODE";

    SharedPreferences sharedPreferencesHome;

    EditText editCode;
    Button btnCodeRequestSender;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_code_request, container , false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sharedPreferencesHome = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editCode = (EditText) getActivity().findViewById(R.id.fragment_codeRequest_editCodeRequest);
//        btnCodeRequestSender = (Button) getActivity().findViewById(R.id.fragment_register_Btn_register);
//
//        btnCodeRequestSender.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show();
//
//                if(Integer.valueOf(editCode.getText().toString()) == 1111){
//                    sharedPreferencesHome.edit().putString(GUID, "CD0D144D-2C5F-4516-9600-BCE20907C563").commit();
//                                    Intent intent = new Intent(getActivity() , FirstContentActivity.class);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                    startActivity(intent);
//                }
//            }
//        });

//        ((Button)getActivity().findViewById(R.id.fragment_register_Btn_register2)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    if(Integer.valueOf(editCode.getText().toString()) == 1111){
//                    sharedPreferencesHome.edit().putString(GUID, "CD0D144D-2C5F-4516-9600-BCE20907C563").commit();
//                    Intent intent = new Intent(getActivity() , FirstContentActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                }
//            }
//        });


        editCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editCode.getText().length() == 4) {
                    final RequestCodeGiverModel requestCodeGiverModel = new RequestCodeGiverModel();
                    //requestCodeGiverModel.setMobileNumber(sharedPreferencesHome.getLong(MOBILE_NUMBER, 0));
                    requestCodeGiverModel.setTransactionId(sharedPreferencesHome.getString(RESULTCODE, ""));

                    try {
                        requestCodeGiverModel.setPin(Long.valueOf(editCode.getText().toString()));
                        RetrofitFactory.getRetrofitClient().requestCodeGiver(requestCodeGiverModel).enqueue(new Callback<ResultJson>() {
                            @Override
                            public void onResponse(Call<ResultJson> call, Response<ResultJson> response) {
                                if (response != null) {
                                    if (!response.body().isIsSuccessfull()) {
                                        Toast.makeText(getActivity(), R.string.incorrectCode, Toast.LENGTH_SHORT).show();
                                        return;
                                    }

                                    if (response.body().getResult().length() > 0) {
                                        sharedPreferencesHome.edit().putString(GUID, response.body().getResult()).commit();
                                        Intent intent = new Intent(getActivity(), FirstContentActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<ResultJson> call, Throwable t) {

                            }
                        });
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), R.string.incorrectCode, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        btnCodeRequestSender.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    final RequestCodeGiverModel requestCodeGiverModel = new RequestCodeGiverModel();
//                    requestCodeGiverModel.setMobileNumber(sharedPreferencesHome.getLong(MOBILE_NUMBER , 0));
//                try {
//                    requestCodeGiverModel.setCode(Long.valueOf(editCode.getText().toString()));
//                    RetrofitFactory.getRetrofitClient().requestCodeGiver(requestCodeGiverModel).enqueue(new Callback<ResultJson>() {
//                        @Override
//                        public void onResponse(Call<ResultJson> call, Response<ResultJson> response) {
//                            if (response != null) {
//                                if(!response.body().isIsSuccessfull()){
//                                    Toast.makeText(getActivity(), R.string.incorrectCode, Toast.LENGTH_SHORT).show();
//                                    return;
//                                }
//
//                                if (response.body().getResult().length() > 0) {
//                                    sharedPreferencesHome.edit().putString(GUID, response.body().getResult()).commit();
//                                    Intent intent = new Intent(getActivity() , FirstContentActivity.class);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                    startActivity(intent);
//                                }
//                            }
//                        }
//                        @Override
//                        public void onFailure(Call<ResultJson> call, Throwable t) {
//
//                        }
//                    });
//                }catch (Exception e){
//                    Toast.makeText(getActivity(), R.string.incorrectCode, Toast.LENGTH_SHORT).show();
//                }
//                }
//        });


    }

}
