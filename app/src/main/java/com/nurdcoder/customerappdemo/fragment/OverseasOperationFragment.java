package com.nurdcoder.customerappdemo.fragment;

/**
 * Created by ZOARDER AL MUKTADIR on 01/23/2017.
 */

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

public class OverseasOperationFragment extends Fragment {

    public OverseasOperationFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_overseas_operation, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.fragment_overseas_operation_parent_rv);
        rv.setHasFixedSize(true);
        OverseasOperationAdapter adapter = new OverseasOperationAdapter(getActivity().getResources().getStringArray(R.array.overseas_operation_item));
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

        return rootView;
    }


    class OverseasOperationAdapter extends RecyclerView.Adapter<OverseasOperationAdapter.OverseasOperationViewHolder> {
        private String[] mOverseasOperationItems;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class OverseasOperationViewHolder extends RecyclerView.ViewHolder {
            public CardView mParentCardView;
            public TextView mTitleTextView;

            public OverseasOperationViewHolder(View v) {
                super(v);

                mParentCardView = (CardView) v.findViewById(R.id.overseas_operation_item_layout_parent_cv);
                mTitleTextView = (TextView) v.findViewById(R.id.overseas_operation_item_layout_title_tv);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public OverseasOperationAdapter(String[] myDataset) {
            mOverseasOperationItems = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public OverseasOperationViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.overseas_operation_item_layout, parent, false);
            // set the view's size, margins, paddings and layout parameters
            OverseasOperationViewHolder vh = new OverseasOperationViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(OverseasOperationViewHolder holder, int position) {
            holder.mTitleTextView.setText(mOverseasOperationItems[position]);
        }

        @Override
        public int getItemCount() {
            return mOverseasOperationItems.length;
        }
    }
}