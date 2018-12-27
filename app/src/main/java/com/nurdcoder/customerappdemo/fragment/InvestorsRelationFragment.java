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

public class InvestorsRelationFragment extends Fragment {

    public InvestorsRelationFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_investors_relation, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.fragment_investors_relation_parent_rv);
        rv.setHasFixedSize(true);
        InvestorsRelationAdapter adapter = new InvestorsRelationAdapter(getActivity().getResources().getStringArray(R.array.investors_relation_item));
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


    class InvestorsRelationAdapter extends RecyclerView.Adapter<InvestorsRelationAdapter.InvestorsRelationViewHolder> {
        private String[] mInvestorsRelationItems;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class InvestorsRelationViewHolder extends RecyclerView.ViewHolder {
            public CardView mParentCardView;
            public TextView mTitleTextView;

            public InvestorsRelationViewHolder(View v) {
                super(v);

                mParentCardView = (CardView) v.findViewById(R.id.investors_relation_item_layout_parent_cv);
                mTitleTextView = (TextView) v.findViewById(R.id.investors_relation_item_layout_title_tv);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public InvestorsRelationAdapter(String[] myDataset) {
            mInvestorsRelationItems = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public InvestorsRelationViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.investors_relation_item_layout, parent, false);
            // set the view's size, margins, paddings and layout parameters
            InvestorsRelationViewHolder vh = new InvestorsRelationViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(InvestorsRelationViewHolder holder, int position) {
            holder.mTitleTextView.setText(mInvestorsRelationItems[position]);
        }

        @Override
        public int getItemCount() {
            return mInvestorsRelationItems.length;
        }
    }
}