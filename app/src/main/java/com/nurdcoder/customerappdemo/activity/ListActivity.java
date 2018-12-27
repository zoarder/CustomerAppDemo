package com.nurdcoder.customerappdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.nurdcoder.customerappdemo.R;
import com.nurdcoder.customerappdemo.adapter.ListAdapter;

/**
 * Created by afchealthltd on 9/16/16.
 */
public class ListActivity extends BaseActivity {
    private String TAG = "ListActivity";
    private RecyclerView recycler_view;
    private ListAdapter mAdapter;
    private TextView activity_patient_like_me_tv_empty;
    private Activity activity;
    private Context context;

    private String[] content;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        context = getApplicationContext();
    }

    @Override
    void setContentView() {
        setContentView(R.layout.activity_list);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            content = b.getStringArray("content");
            title = b.getString("title");
        }
    }

    @Override
    void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_list_parent_tb);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        recycler_view = (RecyclerView) findViewById(R.id.activity_list_content_layout_list_rv);
        activity_patient_like_me_tv_empty = (TextView) findViewById(R.id.activity_list_tv_empty);
        recycler_view.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());

        if (content.length == 0) {
            activity_patient_like_me_tv_empty.setVisibility(View.VISIBLE);
            recycler_view.setVisibility(View.GONE);
        } else {
            mAdapter = new ListAdapter(activity, content);
            recycler_view.setAdapter(mAdapter);
            activity_patient_like_me_tv_empty.setVisibility(View.GONE);
            recycler_view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    void initializeViewComponentsEventListeners() {
        recycler_view.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
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
