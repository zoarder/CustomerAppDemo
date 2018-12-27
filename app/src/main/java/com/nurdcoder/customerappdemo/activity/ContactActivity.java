package com.nurdcoder.customerappdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nurdcoder.customerappdemo.R;

/**
 * Created by ZOARDER AL MUKTADIR on 11/21/2016.
 */

public class ContactActivity extends BaseActivity {

    private CollapsingToolbarLayout mParentCollapsingToolbar;
    private ImageView mHeaderImageView;
    private TextView mBlogDescriptionTextView;
    private TextView mBlogDateTimeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    void setContentView() {
        setContentView(R.layout.activity_contact);
    }

    @Override
    void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_contact_parent_tb);
        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setDisplayShowHomeEnabled(true);
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
        mBlogDescriptionTextView = (TextView) findViewById(R.id.activity_contact_content_layout_blog_description_tv);
        mBlogDateTimeTextView = (TextView) findViewById(R.id.activity_contact_content_layout_blog_date_time_tv);
    }

    @Override
    void initializeImageViewComponents() {
        mHeaderImageView = (ImageView) findViewById(R.id.activity_contact_header_ib);
    }

    @Override
    void initializeOtherViewComponents() {
        mParentCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.activity_contact_parent_ctl);
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