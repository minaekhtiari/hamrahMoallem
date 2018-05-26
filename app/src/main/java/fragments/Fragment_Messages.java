package fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hillavas.messaging.helpers.QuestionHelper;

import java.util.List;

import adapters.RecyclerView_messagesQuestion_Adapter;
import apps.hillavas.com.hamrahdars.R;
import factories.FragmentHelper;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by A.Mohammadi on 7/9/2017.
 */

public class Fragment_Messages extends Fragment {


    public static final String SENT_MESSAGE = "SENT_MESSAGE";
    public static final String UNREAD_ANSWERS = "UNREAD_ANSWERS";
    public static final String GUID = "GUID";
    FloatingActionButton fabAddnewMessage;
    SharedPreferences sharedPreferencesHome;
    String token = null;
    List<Integer> unreadQuestionCount = null;
    TaskLoadAllQuestions taskLoadAllQuestions;
    FrameLayout frameMessageBase;
    TextView tvMessageNotFound;
    ImageView ivNoMessage;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        sharedPreferencesHome = PreferenceManager.getDefaultSharedPreferences(getActivity());
        getActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();

        sharedPreferencesHome = PreferenceManager.getDefaultSharedPreferences(getActivity());
        token = sharedPreferencesHome.getString(GUID, null);
        fabAddnewMessage = (FloatingActionButton) getActivity().findViewById(R.id.fragment_messages_fab_addNewMessage);
        frameMessageBase = (FrameLayout) getActivity().findViewById(R.id.fragment_messaging_frameBase);
        tvMessageNotFound = (TextView) getActivity().findViewById(R.id.fragment_messages_noMessageFound);
        ivNoMessage = (ImageView) getActivity().findViewById(R.id.fragment_messages_noMessageFound_Image);

        fabAddnewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferencesHome.edit().putBoolean(SENT_MESSAGE, false).commit();
                removeFragments();
                ((FrameLayout) getActivity().findViewById(R.id.first_frame)).setVisibility(View.VISIBLE);
                new FragmentHelper(
                        new Fragment_Messaging_new(),
                        R.id.first_frame,
                        getActivity().getSupportFragmentManager()
                ).add(true);


            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            removeFragments();
            new TaskLoadAllQuestions().execute();
        }
        else {
        }
    }

    class TaskLoadAllQuestions extends AsyncTask<Void, Void, List<com.hillavas.messaging.classes.Question>> {
        @Override
        protected List<com.hillavas.messaging.classes.Question> doInBackground(Void... params) {
            List<com.hillavas.messaging.classes.Question> questions = null;
            questions = QuestionHelper.getAllQuestions(token);
            return questions;
        }

        @Override
        protected void onPostExecute(List<com.hillavas.messaging.classes.Question> questions) {
            super.onPostExecute(questions);
            RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.fragment_messages_recyclerView);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            layoutManager.scrollToPosition(0);
            recyclerView.setLayoutManager(layoutManager);
            RecyclerView_messagesQuestion_Adapter recyclerView_messagesQuestion_adapter = new RecyclerView_messagesQuestion_Adapter(getActivity(), questions, token);
            if (recyclerView_messagesQuestion_adapter != null)
                recyclerView.setAdapter(recyclerView_messagesQuestion_adapter);

            if (questions.size() <= 0) {
                tvMessageNotFound.setVisibility(View.VISIBLE);
                ivNoMessage.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
            } else {
                tvMessageNotFound.setVisibility(View.INVISIBLE);
                ivNoMessage.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        }
    }


    public void removeFragments() {
        if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0)
            getActivity().getSupportFragmentManager().popBackStack();
        else if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}