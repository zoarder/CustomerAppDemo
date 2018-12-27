package com.nurdcoder.customerappdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.nurdcoder.customerappdemo.R;
import com.nurdcoder.customerappdemo.adapter.ReferralGridAdapter;

/**
 * Created by ZOARDER AL MUKTADIR on 11/21/2016.
 */

public class ReferralActivity extends BaseActivity {
    public static final String TAG = "ReferralActivity";

    private RecyclerView mReferralGridRecyclerView;
    private CoordinatorLayout mParentCoordinatorLayout;
    private ReferralGridAdapter mReferralGridRecyclerViewAdapter;
    private Activity activity;
    private Context context;

    private String[] image_urls;
    private String[] content;
    private String title;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    void setContentView() {
        setContentView(R.layout.activity_referral);
        if (getIntent() != null) {
            image_urls = getIntent().getStringArrayExtra("image_urls");
            content = getIntent().getStringArrayExtra("content");
            title = getIntent().getStringExtra("title");
        }
    }

    @Override
    void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_referral_parent_tb);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(title);
        }

        activity = this;
        context = getApplicationContext();
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

        mParentCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_referral_parent_cl);
        mReferralGridRecyclerView = (RecyclerView) findViewById(R.id.activity_referral_content_layout_referral_grid_rv);
        mReferralGridRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ReferralActivity.this, 2);
        mReferralGridRecyclerView.setLayoutManager(layoutManager);

        mReferralGridRecyclerViewAdapter = new ReferralGridAdapter(ReferralActivity.this, image_urls, content);
        mReferralGridRecyclerView.setAdapter(mReferralGridRecyclerViewAdapter);
    }

    @Override
    void initializeViewComponentsEventListeners() {
        mReferralGridRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);
                    Snackbar.make(child, "Process Under Development", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    @Override
    void removeViewComponentsEventListeners() {
        mReferralGridRecyclerView.addOnItemTouchListener(null);
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
    public void onBackPressed() {
        exitThisWithAnimation();
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
}
