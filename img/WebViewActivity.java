package com.afchealth.smartphysician.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.afchealth.smartphysician.R;
/**
 * Created by Android Developer on 1/25/2017.
 */

public class WebViewActivity extends AppCompatActivity {
    private static final String TAG = "WebViewActivity";
    private WebView activity_webactivity_webview;

    private Activity activity;
    private Context context;
    private ProgressDialog pDialog;
    private String URL = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            URL = b.getString("content");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity = this;
        context = getApplicationContext();

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        activity_webactivity_webview = (WebView) findViewById(R.id.activity_webactivity_webview);
        activity_webactivity_webview.getSettings().setJavaScriptEnabled(true);
        activity_webactivity_webview.loadUrl(URL);

        activity_webactivity_webview.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pDialog.setMessage("Loading...Please Wait...");
                showDialog();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                hideDialog();
            }
        });
    }

    public void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    public void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
