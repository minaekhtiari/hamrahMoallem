package fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import apps.hillavas.com.hamrahdars.R;

/**
 * Created by A.Mohammadi on 7/9/2017.
 */

public class Fragment_FAQ extends Fragment {

    private static final String FONT_SIZE = "FONT_SIZE";
    private static final String NIGHT_MODE = "NIGHT_MODE";
    private static final String TEXT_IDS = "TEXT_IDS";
    private static final String TITR_IDS = "TITR_IDS";
    private static final String BACK_ID = "BACK_ID";

    RelativeLayout relativeLayoutBack;
    RecyclerView recyclerViewQuestions;
    TextView tvToolbarTitle;
    RelativeLayout relativeLayoutSearchBox;
    boolean whiteOrNight = false;
    SharedPreferences sharedPreferencesHome;
    int backId;
    ArrayList<Integer> textIds = new ArrayList<>();
    ArrayList<Integer> titrIds = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_faq , container , false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
