package apps.hillavas.com.hamrahdars;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;

import fragments.Fragment_ArticleTextView;
import fragments.Fragment_RegisterJustMobile;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

public class IntroActivity extends AppIntro2 {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   setContentView(R.layout.activity_main);
//        addSlide(firstFragment);
//        addSlide(secondFragment);
//        addSlide(thirdFragment);
//        addSlide(fourthFragment);
//        Typeface font = Typeface.createFromAsset(

      //  CalligraphyTypefaceSpan typefaceSpan = new CalligraphyTypefaceSpan(TypefaceUtils.load(getAssets(), "fonts/Roboto-Bold.ttf"));

        SliderPage sliderPage1 = new SliderPage();
        sliderPage1.setTitle("اپلیکیشن همراه معلم");
        sliderPage1.setTitleColor(Color.BLACK);
        sliderPage1.setDescription("بهترین اپلیکیشن آموزشی \n" +
                "از طریق ویدئو با بهره گیری از بهترین اساتید کشوری \n" +
                "از ابتدایی تا کنکور");
//        sliderPage1.setTitleTypeface(String.valueOf(typefaceSpan));
//        sliderPage1.setDescTypeface(String.valueOf(typefaceSpan));
        sliderPage1.setDescColor(Color.parseColor("#949494"));
        sliderPage1.setImageDrawable(R.mipmap.hello);
        sliderPage1.setBgColor(Color.WHITE);
        addSlide(AppIntroFragment.newInstance(sliderPage1));


        SliderPage sliderPage2 = new SliderPage();
        sliderPage2.setTitle("آموزش مستقیم");
        sliderPage2.setTitleColor(Color.BLACK);
        sliderPage2.setDescription("با همراه معلم دیگر نیازی به معلم خصوصی نداری\n" +
                "زیرا بهترین دبیر های کشور را در این اپلیکیشن در اختیار دارید ");
        sliderPage2.setDescColor(Color.parseColor("#949494"));
        sliderPage2.setImageDrawable(R.mipmap.ourteam);
        sliderPage2.setBgColor(Color.WHITE);
        addSlide(AppIntroFragment.newInstance(sliderPage2));


        SliderPage sliderPage3 = new SliderPage();
        sliderPage3.setTitle("هزینه بسیار پایین");
        sliderPage3.setTitleColor(Color.BLACK);
        sliderPage3.setTitleColor(Color.BLACK);
        sliderPage3.setDescription("هزینه بسیار پایین نسبت به تدریس خصوصی\n" +
                "با هزینه روزی 500 تومان، 5000 تومان \n" +
                "درون برنامه ای دریافت کنید\n" +
                " (اعتبار یعنی 10 برابر!!!)");
        sliderPage3.setDescColor(Color.parseColor("#949494"));
        sliderPage3.setImageDrawable(R.mipmap.getticket);
        sliderPage3.setBgColor(Color.WHITE);
        addSlide(AppIntroFragment.newInstance(sliderPage3));



        SliderPage sliderPage4 = new SliderPage();
        sliderPage4.setTitle("ذکر تمامی نکات درسی");
        sliderPage4.setTitleColor(Color.BLACK);
        sliderPage4.setTitleColor(Color.BLACK);
        sliderPage4.setDescription("تدریس ها طبق فصل بندی کتاب درسی سطر به سطر \n" +
                "با ذکر تمامی نکات درسی می باشد ");
        sliderPage4.setDescColor(Color.parseColor("#949494"));
        sliderPage4.setImageDrawable(R.mipmap.prototyping);
        sliderPage4.setBgColor(Color.WHITE);
        addSlide(AppIntroFragment.newInstance(sliderPage4));




        SliderPage sliderPage5 = new SliderPage();
        sliderPage5.setTitle("دسترسی راحت");
        sliderPage5.setTitleColor(Color.BLACK);
        sliderPage5.setTitleColor(Color.BLACK);
        sliderPage5.setDescription("با همراه معلم در هر زمان \n" +
                "که دوست داشتید درس بخوانید و آموزش ببینید");
        sliderPage5.setDescColor(Color.parseColor("#949494"));
        sliderPage5.setImageDrawable(R.mipmap.branding);
        sliderPage5.setBgColor(Color.WHITE);
        addSlide(AppIntroFragment.newInstance(sliderPage5));



        SliderPage sliderPage6 = new SliderPage();
        sliderPage6.setTitle("تکنولوژی");
        sliderPage6.setTitleColor(Color.BLACK);
        sliderPage6.setTitleColor(Color.BLACK);
        sliderPage6.setDescription("از تکنولوژی در آموزش بهره بگیرید");
        sliderPage6.setDescColor(Color.parseColor("#949494"));
        sliderPage6.setImageDrawable(R.mipmap.onlinetreatment);
        sliderPage6.setBgColor(Color.WHITE);
        addSlide(AppIntroFragment.newInstance(sliderPage6));

        setBarColor(Color.parseColor("#3F51B5"));

        // showSkipButton(true);
        //  setProgressButtonEnabled(false);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
    finish();

    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
