package com.nurdcoder.customerappdemo.fragment;

/**
 * Created by ZOARDER AL MUKTADIR on 01/23/2017.
 */

import android.content.Intent;
import android.os.Bundle;
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

public class CardsFragment extends Fragment {

    public CardsFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_cards, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.fragment_cards_parent_rv);
        rv.setHasFixedSize(true);
        CardsAdapter adapter = new CardsAdapter(getActivity().getResources().getStringArray(R.array.cards_item));
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
                    String[] title = getActivity().getResources().getStringArray(R.array.cards_item);
                    String[] content = getActivity().getResources().getStringArray(R.array.cards_content);

                    Intent intent = new Intent(getActivity(), BlogDetailsActivity.class);
                    intent.putExtra("title", title[position]);
                    intent.putExtra("content", content[position]);
                    intent.putExtra("hasExpandedTitle", true);

                    switch (position) {
                        case 0:
                            intent.putExtra("image", R.drawable.card_1);
                            break;
                        case 1:
                            intent.putExtra("image", R.drawable.card_2);
                            break;
                        case 2:
                            intent.putExtra("image", R.drawable.card_3);
                            break;
                        case 3:
                            intent.putExtra("image", R.drawable.card_1);
                            break;
                        case 4:
                            intent.putExtra("image", R.drawable.card_2);
                            break;
                        case 5:
                            intent.putExtra("image", R.drawable.card_3);
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


    class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardsViewHolder> {
        private String[] mCardsItems;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class CardsViewHolder extends RecyclerView.ViewHolder {
            public CardView mParentCardView;
            public TextView mTitleTextView;

            public CardsViewHolder(View v) {
                super(v);

                mParentCardView = (CardView) v.findViewById(R.id.cards_item_layout_parent_cv);
                mTitleTextView = (TextView) v.findViewById(R.id.cards_item_layout_title_tv);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public CardsAdapter(String[] myDataset) {
            mCardsItems = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public CardsViewHolder onCreateViewHolder(ViewGroup parent,
                                                  int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cards_item_layout, parent, false);
            // set the view's size, margins, paddings and layout parameters
            CardsViewHolder vh = new CardsViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(CardsViewHolder holder, int position) {
            holder.mTitleTextView.setText(mCardsItems[position]);
        }

        @Override
        public int getItemCount() {
            return mCardsItems.length;
        }
    }
}