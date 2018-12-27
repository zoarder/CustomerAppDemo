package com.nurdcoder.customerappdemo.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.nurdcoder.customerappdemo.R;
import com.nurdcoder.customerappdemo.other.CustomVolleyRequestQueue;

/**
 * Created by ZOARDER AL MUKTADIR on 11/21/2016.
 */

public class ReferralGridAdapter extends RecyclerView.Adapter<ReferralGridAdapter.ReferralGridItemViewHolder> {

    private Activity mActivity;
    private String[] image_urls;
    private String[] content;
    private com.android.volley.toolbox.ImageLoader mImageLoader;

    public ReferralGridAdapter(Activity activity, String[] image_urls, String[] content) {
        this.image_urls = image_urls;
        this.content = content;
        mActivity = activity;
        mImageLoader = CustomVolleyRequestQueue.getInstance(mActivity)
                .getImageLoader();
    }

    @Override
    public ReferralGridItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.referral_grid_item_layout, viewGroup, false);
        return new ReferralGridItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ReferralGridItemViewHolder viewHolder, int position) {
        mImageLoader.get(image_urls[position], com.android.volley.toolbox.ImageLoader.getImageListener(viewHolder.mLogoImageView,
                R.drawable.stub, R.drawable.dummy));
        viewHolder.mLogoImageView.setImageUrl(image_urls[position], mImageLoader);
        viewHolder.mTitleTextView.setText(content[position]);

    }

//    public void notifyReferralDataSetChanged(ArrayList<String> mReferralPackages) {
//        this.mReferralPackages = mReferralPackages;
//        notifyDataSetChanged();
//    }

    @Override
    public int getItemCount() {
        return image_urls.length;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ReferralGridItemViewHolder extends RecyclerView.ViewHolder {
        private NetworkImageView mLogoImageView;
        private TextView mTitleTextView;

        public ReferralGridItemViewHolder(View view) {
            super(view);
            mLogoImageView = (NetworkImageView) view.findViewById(R.id.referral_grid_item_layout_logo_niv);
            mTitleTextView = (TextView) view.findViewById(R.id.referral_grid_item_layout_title_tv);
        }
    }
}
