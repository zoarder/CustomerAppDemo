package com.nurdcoder.customerappdemo.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nurdcoder.customerappdemo.R;

/**
 * Created by Arup on 14-08-2016.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private Activity activity;
    private String[] title11;


    public ListAdapter(Activity activity, String[] title1) {
        //super();
        this.activity = activity;
        this.title11 = title1;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_listactivity, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return title11.length;
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (holder instanceof RecyclerView.ViewHolder) {
            try {
                holder.list_row_listactivity_title1.setText(Html.fromHtml(title11[position]));
            } catch (IndexOutOfBoundsException ex) {
                Log.e("Error", ex.getMessage());
            }
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView list_row_listactivity_title1;

        public MyViewHolder(View view) {
            super(view);
            list_row_listactivity_title1 = (TextView) view.findViewById(R.id.list_item_layout_title_tv);
        }
    }
}
