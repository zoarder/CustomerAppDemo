package com.nurdcoder.customerappdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterViewFlipper;

import com.nurdcoder.customerappdemo.R;
import com.nurdcoder.customerappdemo.adapter.CustomAdapter;

public class GalleryActivity extends BaseActivity {
    AdapterViewFlipper adapterViewFlipper;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.simpleAdapterViewFlipper);
        CustomAdapter customAdapter = new CustomAdapter(GalleryActivity.this, getResources().getStringArray(R.array.gallary_image_urls));
        adapterViewFlipper.setAdapter(customAdapter);
        adapterViewFlipper.startFlipping();
        // Next screen comes in from left.
        adapterViewFlipper.setInAnimation(this, R.animator.slide_in_from_left);
        // Current screen goes out from right.
        adapterViewFlipper.setOutAnimation(this, R.animator.slide_out_to_right);
        adapterViewFlipper.setFlipInterval(5000);
    }

    @Override
    void setContentView() {
        setContentView(R.layout.activity_gallery);
        if (getIntent() != null) {
            title = getIntent().getStringExtra("title");
        }
    }

    @Override
    void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    void initializeEditTextComponents() {

    }

    @Override
    void initializeButtonComponents() {

    }

    @Override
    void initializeTextViewComponents() {

    }

    @Override
    void initializeImageViewComponents() {

    }

    @Override
    void initializeOtherViewComponents() {

    }

    @Override
    void initializeViewComponentsEventListeners() {

    }

    @Override
    void removeViewComponentsEventListeners() {

    }

    @Override
    void exitThisWithAnimation() {
        finish();
        overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_out);
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

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {

            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                exitThisWithAnimation();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        exitThisWithAnimation();
    }
}
