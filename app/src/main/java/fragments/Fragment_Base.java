package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import apps.hillavas.com.hamrahdars.R;
import factories.FragmentHelper;

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
}
