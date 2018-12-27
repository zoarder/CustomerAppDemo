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

public class DepositProductsFragment extends Fragment {

    public DepositProductsFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_deposit_products, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.fragment_deposit_products_parent_rv);
        rv.setHasFixedSize(true);
        DepositProductsAdapter adapter = new DepositProductsAdapter(getActivity().getResources().getStringArray(R.array.deposit_products_item));
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
                    String[] title = getActivity().getResources().getStringArray(R.array.deposit_products_item);
                    String[] content = getActivity().getResources().getStringArray(R.array.deposit_products_content);

                    Intent intent = new Intent(getActivity(), BlogDetailsActivity.class);
                    intent.putExtra("title", title[position]);
                    intent.putExtra("content", content[position]);

                    switch (position) {
                        case 0:
                            intent.putExtra("image", R.drawable.deposite_1);
                            intent.putExtra("hasExpandedTitle", true);
                            break;
                        case 1:
                            intent.putExtra("image", R.drawable.deposite_2);
                            intent.putExtra("hasExpandedTitle", true);
                            break;
                        case 2:
                            intent.putExtra("image", R.drawable.deposite_3);
                            intent.putExtra("hasExpandedTitle", true);
                            break;
                        case 3:
                            intent.putExtra("image", R.drawable.deposite_4);
                            intent.putExtra("hasExpandedTitle", true);
                            break;
                        case 4:
                            intent.putExtra("image", R.drawable.deposite_1);
                            intent.putExtra("hasExpandedTitle", true);
                            break;
                        case 5:
                            intent.putExtra("image", R.drawable.deposite_2);
                            intent.putExtra("hasExpandedTitle", true);
                            break;
                        case 6:
                            intent.putExtra("image", R.drawable.nbl_nms_2016);
                            intent.putExtra("hasExpandedTitle", false);
                            break;
                        case 7:
                            intent.putExtra("image", R.drawable.nbl_mes_2016);
                            intent.putExtra("hasExpandedTitle", false);
                            break;
                        case 8:
                            intent.putExtra("image", R.drawable.deposite_3);
                            intent.putExtra("hasExpandedTitle", true);
                            break;
                        case 9:
                            intent.putExtra("image", R.drawable.nbl_mds_2016);
                            intent.putExtra("hasExpandedTitle", false);
                            break;
                        case 10:
                            intent.putExtra("image", R.drawable.ps_atsp);
                            intent.putExtra("hasExpandedTitle", false);
                            break;
                        case 11:
                            intent.putExtra("image", R.drawable.ps_lsds);
                            intent.putExtra("hasExpandedTitle", false);
                            break;

                    }

                    getActivity().startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.trans_left_in,
                            R.anim.trans_left_out);
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


    class DepositProductsAdapter extends RecyclerView.Adapter<DepositProductsAdapter.DepositProductsViewHolder> {
        private String[] mDepositProductsItems;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class DepositProductsViewHolder extends RecyclerView.ViewHolder {
            public CardView mParentCardView;
            public TextView mTitleTextView;

            public DepositProductsViewHolder(View v) {
                super(v);

                mParentCardView = (CardView) v.findViewById(R.id.deposit_products_item_layout_parent_cv);
                mTitleTextView = (TextView) v.findViewById(R.id.deposit_products_item_layout_title_tv);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public DepositProductsAdapter(String[] myDataset) {
            mDepositProductsItems = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public DepositProductsViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.deposit_products_item_layout, parent, false);
            // set the view's size, margins, paddings and layout parameters
            DepositProductsViewHolder vh = new DepositProductsViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(DepositProductsViewHolder holder, int position) {
            holder.mTitleTextView.setText(mDepositProductsItems[position]);
        }

        @Override
        public int getItemCount() {
            return mDepositProductsItems.length;
        }
    }
}