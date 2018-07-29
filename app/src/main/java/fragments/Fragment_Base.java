package fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.net.URI;

import apps.hillavas.com.hamrahdars.BuildConfig;
import apps.hillavas.com.hamrahdars.R;
import classes.models.VersionInfo;
import classes.tools.helpers.RetrofitFactory;
import factories.FragmentHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by A.Mohammadi on 1/16/2018.
 */

public class Fragment_Base extends Fragment {

    FrameLayout frameLayoutContent;
    FrameLayout frameLayoutMenu;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base , container , false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        frameLayoutContent = (FrameLayout) getActivity().findViewById(R.id.fragment_base_content);
        frameLayoutMenu = (FrameLayout) getActivity().findViewById(R.id.fragment_base_menu);

        getVersion();
        new FragmentHelper(
                new Fragment_pager(),
                R.id.fragment_base_content,
                getActivity().getSupportFragmentManager()
        ).replace(false);

        new FragmentHelper(
                new Fragment_Menu(),
                R.id.fragment_base_menu,
                getActivity().getSupportFragmentManager()
        ).replace(false);



        ((FloatingActionButton)getActivity().findViewById(R.id.fragment_messages_fab_menu))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(frameLayoutMenu.getVisibility() == View.INVISIBLE)
                            frameLayoutMenu.setVisibility(View.VISIBLE);
                        else
                            frameLayoutMenu.setVisibility(View.INVISIBLE);

                    }
                });

    }

    private void getVersion() {
   final AlertDialog.Builder dialogversoin =new AlertDialog.Builder(getActivity());
   final int currentversionCode = BuildConfig.VERSION_CODE;



        RetrofitFactory.getRetrofitClient().getDevicevrsion("1").enqueue(new Callback<VersionInfo>() {
            @Override
            public void onResponse(Call<VersionInfo> call, final Response<VersionInfo> response) {
               if(response.body().getIsSuccessful()==true && response.body().getResult().getLastVersionCode()>currentversionCode){
                   dialogversoin.show();
                   new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {

                       }
                   };


                  dialogversoin.setMessage("نسخه جدید");
                  dialogversoin.setPositiveButton("بی خیال", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                          dialogInterface.dismiss();
                        }
                    });

                    dialogversoin.setNegativeButton("به روز رسانی", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String url=response.body().getResult().getDownloadLink();
                            Intent intent= new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("https://"+url));
                            startActivity(intent);
                        }
                    });
                   AlertDialog alertDialog = dialogversoin.create();
                   alertDialog.show();
                   alertDialog.setCancelable(false);
                   if(response.body().getResult().getIsForceUpdate()==true){
                       alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);}



            }
          }

            @Override
            public void onFailure(Call<VersionInfo> call, Throwable t) {
                Toast.makeText(getContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });


    }

}
