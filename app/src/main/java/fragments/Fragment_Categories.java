package fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import apps.hillavas.com.hamrahdars.R;

/**
 * Created by A.Mohammadi on 7/9/2017.
 */

public class Fragment_Categories extends Fragment {


    private static final String LEVEL_ID = "LEVEL_ID";
    private static final String CATEGORYID_ID = "CATEGORYID_ID";
    private static final String HAS_CHILD = "HAS_CHILD";
    public static final String GUID="GUID";
    SharedPreferences sharedPreferencesHome;
    RecyclerView recyclerViewCategories;
    RecyclerView recyclerViewContents;
    String token = null;

    int levelId = 0;
    int categoryId = 0;
    boolean hasChild = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories , container , false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sharedPreferencesHome = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        token = sharedPreferencesHome.getString(GUID, "");
        Bundle bundle = getArguments();
        if(bundle != null){
            if(bundle.containsKey(LEVEL_ID))
                levelId = bundle.getInt(LEVEL_ID);
            if(bundle.containsKey(CATEGORYID_ID))
                categoryId = bundle.getInt(CATEGORYID_ID);
            if(bundle.containsKey(HAS_CHILD))
                hasChild = bundle.getBoolean(LEVEL_ID);
        }
//        if(!hasChild){
//            Toast.makeText(getActivity(), "goto content", Toast.LENGTH_SHORT).show();
//        }
//        if(levelId > 0 && categoryId > 0 && hasChild)
//            new TaskLoadLevelAndCategories().execute(levelId , categoryId);
//        else
//        if(levelId > 0 && categoryId == 0 && hasChild)
//            new TaskLoadLevelCategories().execute(levelId);
//        if(levelId == 0 && categoryId == 0)
//            new TaskLoadBaseCategories().execute();


        recyclerViewCategories = (RecyclerView) getActivity().findViewById(R.id.activity_menu_recycler_categoris);
        recyclerViewContents = (RecyclerView) getActivity().findViewById(R.id.activity_menu_recycler_contents);

        LinearLayoutManager linearLayoutManagerCategories = new LinearLayoutManager(getActivity().getApplicationContext());
        linearLayoutManagerCategories.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCategories.setLayoutManager(linearLayoutManagerCategories);

        LinearLayoutManager linearLayoutManagerContents = new LinearLayoutManager(getActivity().getApplicationContext());
        linearLayoutManagerContents.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewContents.setLayoutManager(linearLayoutManagerContents);


    }


}
