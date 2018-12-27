package com.nurdcoder.customerappdemo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nurdcoder.customerappdemo.R;

/**
 * Created by ZOARDER AL MUKTADIR on 11/21/2016.
 */

public class SplashScreenActivity extends BaseActivity {

    private ImageView mAppLogoImageView;
    private ProgressBar mDataLoadingProgressBar;
    private TextView mDataLoadingTextView;
    private int mProgressBarIndex;

//    RelativeLayout container;
//    AnimationDrawable anim;
//    AnimatorSet set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressBarIndex = 0;

        new CountDownTimer(5000, 100) {

            public void onTick(long millisUntilFinished) {
                onProgressDialog(getString(R.string.text_loading), mProgressBarIndex += 2);
            }

            public void onFinish() {
                onProgressDialog(getString(R.string.text_finished), 100);
                onTimeSpent();
            }
        }.start();

    }

    @Override
    void setContentView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_splash_screen);
    }

    @Override
    void setupActionBar() {

    }

    @Override
    void initializeEditTextComponents() {

    }

    @Override
    void initializeButtonComponents() {

    }

    @Override
    void initializeTextViewComponents() {
        mDataLoadingTextView = (TextView) findViewById(R.id.activity_splash_screen_data_loading_tv);

    }

    @Override
    void initializeImageViewComponents() {

    }

    @Override
    void initializeOtherViewComponents() {
        mAppLogoImageView = (ImageView) findViewById(R.id.activity_splash_screen_logo_iv);
        mDataLoadingProgressBar = (ProgressBar) findViewById(R.id.activity_splash_screen_data_loading_pb);
        Animation shake = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.shake_1);
        mAppLogoImageView.startAnimation(shake);

//        set = (AnimatorSet) AnimatorInflater.loadAnimator(SplashScreenActivity.this, R.animator.wave);
//        set.setTarget(mAppLogoImageView);

//        container = (RelativeLayout) findViewById(R.id.activity_splash_screen_parent_rl);
//        anim = (AnimationDrawable) container.getBackground();
//        anim.setEnterFadeDuration(6000);
//        anim.setExitFadeDuration(2000);
    }

    @Override
    void initializeViewComponentsEventListeners() {

    }

    @Override
    void removeViewComponentsEventListeners() {

    }

    @Override
    void exitThisWithAnimation() {

    }

    @Override
    void startNextWithAnimation(Intent intent, boolean isFinish) {
        if (isFinish) {
            finish();
        }
        startActivity(intent);
        overridePendingTransition(R.anim.trans_left_in,
                R.anim.trans_left_out);
    }

    private void onTimeSpent() {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startNextWithAnimation(intent, true);
    }

    private void onProgressDialog(String progressText, int totalProgress) {
        mDataLoadingTextView.setText(progressText);
        mDataLoadingProgressBar.setProgress(totalProgress);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onBackPressed() {
    }

    // Starting animation:- start the animation on onResume.
    @Override
    protected void onResume() {
        super.onResume();
//        if (anim != null && !anim.isRunning()) {
//            anim.start();
//        }
//        if (set != null && !set.isRunning()) {
//            set.start();
//        }
    }

    // Stopping animation:- stop the animation on onPause.
    @Override
    protected void onPause() {
        super.onPause();
//        if (anim != null && anim.isRunning()) {
//            anim.stop();
//        }
//        if (set != null && !set.isRunning()) {
//            set.end();
//        }
    }
}
