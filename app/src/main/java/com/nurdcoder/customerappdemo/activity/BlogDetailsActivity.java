package com.nurdcoder.customerappdemo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nurdcoder.customerappdemo.R;

/**
 * Created by ZOARDER AL MUKTADIR on 11/21/2016.
 */

public class BlogDetailsActivity extends BaseActivity {

    private CollapsingToolbarLayout mParentCollapsingToolbar;
    private ImageView mHeaderImageView;
    private TextView mBlogDescriptionTextView;
    private TextView mBlogDateTimeTextView;
    String title;
    int image;
    String content;
    boolean hasExpandedTitle = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        setContentView(R.layout.activity_blog_details);
        if (getIntent() != null) {
            title = getIntent().getStringExtra("title");
            content = getIntent().getStringExtra("content");
            image = getIntent().getIntExtra("image", 0);
            hasExpandedTitle = getIntent().getBooleanExtra("hasExpandedTitle", true);
        }
    }

    @Override
    void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_blog_details_parent_tb);
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
        mBlogDescriptionTextView = (TextView) findViewById(R.id.activity_blog_details_content_layout_blog_description_tv);
        mBlogDescriptionTextView.setText(Html.fromHtml(content));
        mBlogDateTimeTextView = (TextView) findViewById(R.id.activity_blog_details_content_layout_blog_date_time_tv);
    }

    @Override
    void initializeImageViewComponents() {
        mHeaderImageView = (ImageView) findViewById(R.id.activity_blog_details_header_ib);
        mHeaderImageView.setImageResource(image);
    }

    @Override
    void initializeOtherViewComponents() {
        mParentCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.activity_blog_details_parent_ctl);
        if (!hasExpandedTitle) {
            mParentCollapsingToolbar.setTitle(" ");
            AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.activity_blog_details_parent_abl);
            appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                boolean isShow = false;
                int scrollRange = -1;

                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (scrollRange == -1) {
                        scrollRange = appBarLayout.getTotalScrollRange();
                    }
                    if (scrollRange + verticalOffset == 0) {
                        mParentCollapsingToolbar.setTitle(title);
                        isShow = true;
                    } else if (isShow) {
                        mParentCollapsingToolbar.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                        isShow = false;
                    }
                }
            });
        } else {
            mParentCollapsingToolbar.setTitle(title);
        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_blog_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                exitThisWithAnimation();
                break;
            case R.id.action_share:
                Toast.makeText(BlogDetailsActivity.this, "Process Under Development", Toast.LENGTH_SHORT).show();
//                List<Intent> targetShareIntents = new ArrayList<Intent>();
//                Intent shareIntent = new Intent();
//                shareIntent.setAction(Intent.ACTION_SEND);
//                shareIntent.setType("text/plain");
//                List<ResolveInfo> resInfos = getPackageManager().queryIntentActivities(shareIntent, 0);
//                String encodedTitle = Base64.encodeToString((mBlogModelItem.getBlogID() + "").getBytes(), Base64.DEFAULT);
//                Log.e("encodedTitle", encodedTitle);
//                String uri = "http://scirf.com/scirfblogs/share.php?q=" + encodedTitle;
////                uri = uri.replaceAll(" ", "%20");
//                if (!resInfos.isEmpty()) {
//                    Log.e("Package Name", "Have package");
//                    for (ResolveInfo resInfo : resInfos) {
//                        String packageName = resInfo.activityInfo.packageName;
//                        Log.e("Package Name", packageName);
//
//                        Intent intent = new Intent();
//                        intent.setComponent(new ComponentName(packageName, resInfo.activityInfo.name));
//                        intent.setAction(Intent.ACTION_SEND);
//                        intent.setPackage(packageName);
//
//                        // Messengers
//                        if (packageName.contains("com.android.mms")) {
//                            intent.setType("text/plain");
//                            intent.putExtra(Intent.EXTRA_TEXT, uri);
//                            targetShareIntents.add(intent);
//                        }
//
//                        if (packageName.contains("com.google.android.talk")) {
//                            intent.setType("text/plain");
//                            intent.putExtra(Intent.EXTRA_TEXT, uri);
//                            targetShareIntents.add(intent);
//                        }
//
//                        if (packageName.contains("com.google.android.apps.messaging")) {
//                            intent.setType("text/plain");
//                            intent.putExtra(Intent.EXTRA_TEXT, uri);
//                            targetShareIntents.add(intent);
//                        }
//
//                        if (packageName.contains("com.viber.voip")) {
//                            intent.setType("text/plain");
//                            intent.putExtra(Intent.EXTRA_TEXT, uri);
//                            targetShareIntents.add(intent);
//                        }
//
//                        if (packageName.contains("com.whatsapp")) {
//                            intent.setType("text/plain");
//                            intent.putExtra(Intent.EXTRA_TEXT, uri);
//                            targetShareIntents.add(intent);
//                        }
//
//                        if (packageName.contains("com.facebook.orca")) {
//                            intent.setType("text/plain");
//                            intent.putExtra(Intent.EXTRA_TEXT, uri);
//                            targetShareIntents.add(intent);
//                        }
//
//                        if (packageName.contains("com.imo.android.imoim")) {
//                            intent.setType("text/plain");
//                            intent.putExtra(Intent.EXTRA_TEXT, uri);
//                            targetShareIntents.add(intent);
//                        }
//
//                        if (packageName.contains("com.skype.raider")) {
//                            intent.setType("text/plain");
//                            intent.putExtra(Intent.EXTRA_TEXT, uri);
//                            targetShareIntents.add(intent);
//                        }
//
//                        // Social Media
//                        if (packageName.contains("com.twitter.android")) {
//                            intent.setType("text/plain");
//                            intent.putExtra(Intent.EXTRA_TEXT, uri);
//                            targetShareIntents.add(intent);
//                        }
//
//                        if (packageName.contains("com.facebook.katana")) {
//                            intent.setType("text/plain");
//                            intent.putExtra(Intent.EXTRA_TEXT, uri);
//                            targetShareIntents.add(intent);
//                        }
//
//                        if (packageName.contains("com.google.android.apps.plus")) {
//                            intent.setType("text/plain");
//                            intent.putExtra(Intent.EXTRA_TEXT, uri);
//                            targetShareIntents.add(intent);
//                        }
//
//                        // Mail Client
//                        if (packageName.contains("com.yahoo.mobile.client.android")) {
//                            intent.setType("text/plain");
//                            intent.putExtra(Intent.EXTRA_TEXT, uri);
//                            intent.putExtra(Intent.EXTRA_SUBJECT, mBlogModelItem.getBlogTitle());
//                            targetShareIntents.add(intent);
//                        }
//
//                        if (packageName.contains("com.microsoft.office.outlook")) {
//                            intent.setType("text/plain");
//                            intent.putExtra(Intent.EXTRA_TEXT, uri);
//                            intent.putExtra(Intent.EXTRA_SUBJECT, mBlogModelItem.getBlogTitle());
//                            targetShareIntents.add(intent);
//                        }
//
//                        if (packageName.contains("com.google.android.gm")) {
//                            intent.setType("text/plain");
//                            intent.putExtra(Intent.EXTRA_TEXT, uri);
//                            intent.putExtra(Intent.EXTRA_SUBJECT, mBlogModelItem.getBlogTitle());
//                            targetShareIntents.add(intent);
//                        }
//                    }
//                    if (!targetShareIntents.isEmpty()) {
//                        System.out.println("Have Intent");
//                        Intent chooserIntent = Intent.createChooser(targetShareIntents.remove(0), getString(R.string.share_title));
//                        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetShareIntents.toArray(new Parcelable[]{}));
//                        startActivity(chooserIntent);
//                    } else {
//                        Log.e("Package Name", "Do not Have Intent");
//                    }
//                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        exitThisWithAnimation();
    }
}