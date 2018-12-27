package com.nurdcoder.customerappdemo.adapter;

/**
 * Created by ZOARDER on 10/28/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.volley.toolbox.NetworkImageView;
import com.nurdcoder.customerappdemo.R;
import com.nurdcoder.customerappdemo.other.CustomVolleyRequestQueue;

public class CustomAdapter extends BaseAdapter {

    private final Context mContext;
    String[] urls;
    private LayoutInflater inflater;
    private com.android.volley.toolbox.ImageLoader mImageLoader;

    public CustomAdapter(Context context, String[] urls) {
        mContext = context;
        this.urls = urls;
        inflater = LayoutInflater.from(context);
        mImageLoader = CustomVolleyRequestQueue.getInstance(mContext)
                .getImageLoader();
    }

    @Override
    public int getCount() {
        return urls.length;
    }

    @Override
    public Object getItem(int position) {
        return urls[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.list_item_slider, parent, false);
            holder = new ViewHolder();
            assert view != null;
            holder.imageView = (NetworkImageView) view.findViewById(R.id.list_item_slider_image);
//            holder.progressBar = (ProgressBar) view.findViewById(R.id.list_item_slider_progress);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        mImageLoader.get(urls[position], com.android.volley.toolbox.ImageLoader.getImageListener(holder.imageView,
                R.drawable.ic_stub, R.drawable.ic_empty));
        holder.imageView.setImageUrl(urls[position], mImageLoader);

        return view;
    }
}

class ViewHolder {
    NetworkImageView imageView;
//    ProgressBar progressBar;
}