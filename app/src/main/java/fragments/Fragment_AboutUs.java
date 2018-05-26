package fragments;


import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import apps.hillavas.com.hamrahdars.AboutUs;
import apps.hillavas.com.hamrahdars.R;
import classes.justifiers.JustifiedTextView;

/**
 * Created by A.Mohammadi on 1/14/2018.
 */

public class Fragment_AboutUs extends Fragment {

    ProgressDialog progressDialog;
    JustifiedTextView jText;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.activity_about_us, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((RelativeLayout) getActivity().findViewById(R.id.aboutUs_imageBack_relative)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            getActivity().onBackPressed();
            }
        });

        jText = (JustifiedTextView) getActivity().findViewById(R.id.about_Jtest_text);
        jText.setText("موسسه خواجه نصیر با بیش از 15 سال سابقه آموزشی ، همواره در عرصه آموزش ، تقویتی و کنکور گام های مهم و تعیین کننده ای برداشته است. در این راستا بهره گیری ازابزارها و روش های نوین ، با استفاده از تاثیر تکنولوژی در تدریس و آموزش می تواند راه خوبی برای آموزش مفهومی و مفید باشد.\n \n\n" +
                "موسسه خواجه نصیر  درسال 1394 به ایجاد حوزه ای مستقل برای فعالیت در زمینه تهیه فیلم های آموزشی و آموزشگاه مجازی اقدام نموده است. فیلم های مرکز آموزش مجازی خواجه نصیر با بهره گیری از بهترین اساتید و با بالاترین کیفیت به صورت HD تهیه شده است. عمده هدف این موسسه توجه بیشتر به یادگیری و تفهیم بهتر درس برای دانش آموزان می باشد.\n" +
                "\n" +
                "برخی از ویژگی های فیلم های آموزشی خواجه نصیر:\n" +
                "استفاده از روشی نوین در تهیه فیلم های آموزشی با آخرین تکنولوژی آموزشی\n" +
                "استفاده از بهترین اساتید مجرب برای تدریس\n" +
                "تسهیل در دسترسی به فیلم های آموزشی از روشهای نوین\n" +
                "کاهش هزینه برای دانش آموزان در سطح های مختلف تحصیلی\n" +
                "قابلیت تکرار و مرور فیلم ها در هر زمان و هر مکان با تلفن همراه ، تبلت ، کامپیوتر و DVD پلیرها\n" +
                "شماره تماس مشاوره و راهنمایی استفاده از سایت :  48098 - 021\n");

        jText.setLineSpacing(80);
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/iransans.ttf");
        jText.setTypeFace(tf);


    }
}
