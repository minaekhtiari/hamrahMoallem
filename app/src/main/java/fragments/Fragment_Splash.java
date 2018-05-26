package fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import apps.hillavas.com.hamrahdars.FirstContentActivity;
import apps.hillavas.com.hamrahdars.R;

/**
 * Created by A.Mohammadi on 7/9/2017.
 */

public class Fragment_Splash extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash , container , false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        new FragmentHelper(
//                new Fragment_Password(),
//                R.id.frameLayout_base,
//                getActivity().getSupportFragmentManager()
//        ).replace(false);



        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent(getActivity() , FirstContentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }, 2000);

    }

}
