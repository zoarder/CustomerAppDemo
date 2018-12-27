package com.nurdcoder.customerappdemo.fragment;

/**
 * Created by ZOARDER AL MUKTADIR on 01/23/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nurdcoder.customerappdemo.R;
import com.nurdcoder.customerappdemo.activity.BlogDetailsActivity;
import com.nurdcoder.customerappdemo.activity.ListActivity;

public class ServiceLocationFragment extends Fragment {

    public ServiceLocationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_service_location, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.fragment_service_location_parent_rv);
        rv.setHasFixedSize(true);
        ServiceLocationAdapter adapter = new ServiceLocationAdapter(getActivity().getResources().getStringArray(R.array.service_location_item));
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        rv.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {

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
//                    Snackbar.make(child, "Process Under Development", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
                    Intent intent = null;
                    String[] title = getActivity().getResources().getStringArray(R.array.service_location_item);
                    intent = new Intent(getActivity(), ListActivity.class);
                    switch (position) {
                        case 0:
                            intent.putExtra("content", getActivity().getResources().getStringArray(R.array.branch_content));
                            break;
                        case 1:
                            intent.putExtra("content", getActivity().getResources().getStringArray(R.array.dealer_branch_content));
                            break;
                        case 2:
                            intent.putExtra("content", getActivity().getResources().getStringArray(R.array.atm_content));
                            break;
                        case 3:
                            intent.putExtra("content", getActivity().getResources().getStringArray(R.array.egp_branch_content));
                            break;
                    }
                    if (intent != null) {
                        intent.putExtra("title", title[position]);
                        getActivity().startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.trans_left_in,
                                R.anim.trans_left_out);
                    }
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

        return rootView;
    }


    class ServiceLocationAdapter extends RecyclerView.Adapter<ServiceLocationAdapter.ServiceLocationViewHolder> {
        private String[] mServiceLocationItems;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ServiceLocationViewHolder extends RecyclerView.ViewHolder {
            public CardView mParentCardView;
            public TextView mTitleTextView;

            public ServiceLocationViewHolder(View v) {
                super(v);

                mParentCardView = (CardView) v.findViewById(R.id.service_location_item_layout_parent_cv);
                mTitleTextView = (TextView) v.findViewById(R.id.service_location_item_layout_title_tv);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public ServiceLocationAdapter(String[] myDataset) {
            mServiceLocationItems = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ServiceLocationViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.service_location_item_layout, parent, false);
            // set the view's size, margins, paddings and layout parameters
            ServiceLocationViewHolder vh = new ServiceLocationViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ServiceLocationViewHolder holder, int position) {
            holder.mTitleTextView.setText(mServiceLocationItems[position]);
        }

        @Override
        public int getItemCount() {
            return mServiceLocationItems.length;
        }
    }
}