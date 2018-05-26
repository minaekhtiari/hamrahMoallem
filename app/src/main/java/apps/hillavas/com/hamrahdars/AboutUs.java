package apps.hillavas.com.hamrahdars;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AboutUs extends AppCompatActivity {

    WebView webView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
//        webView = (WebView) findViewById(R.id.aboutUs_webView);
//        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
////        webView.loadUrl("http://www.khajenasir.com/درباره-ما.html");
//
//
//        ((TextView)findViewById(R.id.toolbar_all_frameTitle_text)).setText("درباره همراه معلم");
//        ((RelativeLayout)findViewById(R.id.toolbar_all_imageBack_relative)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//
//
//
//
//        WebSettings settings = webView.getSettings();
//
//        settings.setJavaScriptEnabled(true);
//        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
//
//        webView.getSettings().setBuiltInZoomControls(true);
//        webView.getSettings().setUseWideViewPort(true);
//        webView.getSettings().setLoadWithOverviewMode(true);
//
//
//        progressDialog = new ProgressDialog(AboutUs.this);
//        progressDialog.setMessage(getString(R.string.waiting));
//        progressDialog.setProgressStyle(R.style.SpinnerStyle);
//        progressDialog.setCancelable(false);
//        progressDialog.show();
//
//
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                if (progressDialog.isShowing()) {
//                    progressDialog.dismiss();
//                }
//            }
//
//            @Override
//            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
////                Toast.makeText(ContestActivity.this, "Error:" + description, Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        webView.loadUrl("http://www.khajenasir.com/درباره-ما.html");
//    }
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
