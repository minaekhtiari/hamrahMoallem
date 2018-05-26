package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import apps.hillavas.com.hamrahdars.R;

/**
 * Created by ab.mohammadi on 5/16/2018.
 */

public class Fragment_RegisterJustMobile extends Fragment {

    Button btnRegister;
    EditText editMobileNumber;
    ProgressBar progressBar;

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

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              progressBar.setVisibility(View.VISIBLE);
            }
        });

    }
}
