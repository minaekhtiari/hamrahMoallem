package apps.hillavas.com.hamrahdars;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hillavas.messaging.helpers.QuestionHelper;

import java.util.ArrayList;
import java.util.List;

import adapters.Recycler_Adapter_CommonQuestions;
import factories.FragmentHelper;
import fragments.Fragment_BottomBar;
import fragments.Fragment_ToolbarAll;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CommonQuestion extends AppCompatActivity {


    private static final String FONT_SIZE = "FONT_SIZE";
    private static final String NIGHT_MODE = "NIGHT_MODE";
    private static final String TEXT_IDS = "TEXT_IDS";
    private static final String TITR_IDS = "TITR_IDS";
    private static final String BACK_ID = "BACK_ID";
    public static final String GUID="GUID";

    String token = null;
    RelativeLayout relativeLayoutBack;
    RecyclerView recyclerViewQuestions;
    TextView tvToolbarTitle;
    RelativeLayout relativeLayoutSearchBox;
    boolean whiteOrNight = false;
    SharedPreferences sharedPreferencesHome;
    int backId;
    ArrayList<Integer> textIds = new ArrayList<>();
    ArrayList<Integer> titrIds = new ArrayList<>();
    List<com.hillavas.messaging.classes.Question> allQuestions = new ArrayList<>();
    List<com.hillavas.messaging.classes.Question> allQuestionsSearch = null;
    EditText editSerach;
    ImageView ivSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_question);
        recyclerViewQuestions = (RecyclerView)findViewById(R.id.fraqment_faq_recyclerQuestions);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        sharedPreferencesHome = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        token = sharedPreferencesHome.getString(GUID, "");
        whiteOrNight = sharedPreferencesHome.getBoolean(NIGHT_MODE , false);
        relativeLayoutBack = (RelativeLayout)findViewById(R.id.fraqment_faq_relative_backOfAll);
        editSerach = (EditText) findViewById(R.id.fragment_login_EditMobile);
        ivSearch = (ImageView) findViewById(R.id.commonQuestion_image_search);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.setStatusBarColor(getResources().getColor(R.color.statusBarColor));

        editSerach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(editSerach.getText().toString().length()>0){
                    recyclerViewQuestions.setAdapter(null);
                    String word = editSerach.getText().toString();
                    allQuestionsSearch = new ArrayList<com.hillavas.messaging.classes.Question>();
                    for(com.hillavas.messaging.classes.Question q : allQuestions){
                        if(q.getBody().contains(word)){
                            allQuestionsSearch.add(q);
                        }
                    }
                    if(allQuestionsSearch.size()>0){
                        Recycler_Adapter_CommonQuestions adapterCommonQuestions = new Recycler_Adapter_CommonQuestions(getApplicationContext() , allQuestionsSearch);
                        recyclerViewQuestions.setAdapter(adapterCommonQuestions);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        layoutManager.scrollToPosition(0);
                        recyclerViewQuestions.setLayoutManager(layoutManager);
                        bottomBarLoader(allQuestionsSearch);
                    }
                }else {
                    Recycler_Adapter_CommonQuestions adapterCommonQuestions = new Recycler_Adapter_CommonQuestions(getApplicationContext() , allQuestions);
                    recyclerViewQuestions.setAdapter(adapterCommonQuestions);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    layoutManager.scrollToPosition(0);
                    recyclerViewQuestions.setLayoutManager(layoutManager);
                    bottomBarLoader(allQuestions);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        if(whiteOrNight)
            relativeLayoutBack.setBackgroundColor(getResources().getColor(R.color.gray_700));
        else
            relativeLayoutBack.setBackgroundColor(getResources().getColor(R.color.white));


        Fragment_ToolbarAll fragment_toolbarAll = new Fragment_ToolbarAll();
        Bundle bundleToolBarTitle = new Bundle();
        bundleToolBarTitle.putString("TITLE" , getString(R.string.commonQuestion));
        fragment_toolbarAll.setArguments(bundleToolBarTitle);

        new FragmentHelper(
                fragment_toolbarAll,
                R.id.fraqment_faq_toolbarFrame,
                getSupportFragmentManager()
        ).replace(false);


        relativeLayoutSearchBox = (RelativeLayout)findViewById(R.id.fraqment_faq_searchBox);
        ((FrameLayout)findViewById(R.id.frameLayout_bottom_bar)).setVisibility(View.VISIBLE);
        new TaskLoadAllUsualQuestions().execute();
    }

    private void bottomBarLoader(List<com.hillavas.messaging.classes.Question> questions){
        ArrayList<String> lstQuestion = new ArrayList<>();
        ArrayList<String> lstAnswer = new ArrayList<>();
        for(int i = 0 ; i < questions.size() ; i++){
            if(questions.get(i).getAnswers() != null) {
                lstQuestion.add(i, questions.get(i).getBody());
                lstAnswer.add(i, questions.get(i).getAnswers().get(0).getAnswer());
            }
        }

        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList(TEXT_IDS , textIds);
        bundle.putIntegerArrayList(TITR_IDS , titrIds);
        bundle.putInt(BACK_ID , relativeLayoutBack.getId());
        bundle.putBoolean("FROM_RECYCLER_QUESTIONS" , true);
        bundle.putStringArrayList("QUESTION_LIST" , lstQuestion);
        bundle.putStringArrayList("ANSWER_LIST" , lstAnswer);
        Fragment_BottomBar fragment_bottomBar = new Fragment_BottomBar();
        fragment_bottomBar.setArguments(bundle);
        new FragmentHelper(
                fragment_bottomBar,
                R.id.frameLayout_bottom_bar,
                getSupportFragmentManager()
        ).replace(false);
    }


    class TaskLoadAllUsualQuestions extends AsyncTask<Void ,Void , List<com.hillavas.messaging.classes.Question>>{

        @Override
        protected List<com.hillavas.messaging.classes.Question> doInBackground(Void... params) {
            List<com.hillavas.messaging.classes.Question> questions = null;
            questions = QuestionHelper.getAllUsualQuestions(token);
            return questions;
        }
        @Override
        protected void onPostExecute(List<com.hillavas.messaging.classes.Question> questions) {
            super.onPostExecute(questions);
            allQuestions = questions;
            Recycler_Adapter_CommonQuestions adapterCommonQuestions = new Recycler_Adapter_CommonQuestions(getApplicationContext() , questions);
            recyclerViewQuestions.setAdapter(adapterCommonQuestions);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerViewQuestions.setLayoutManager(layoutManager);
            bottomBarLoader(allQuestions);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}




