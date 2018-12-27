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
import com.nurdcoder.customerappdemo.activity.GalleryActivity;
import com.nurdcoder.customerappdemo.activity.ReferralActivity;

public class AboutUsFragment extends Fragment {

    public AboutUsFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_about_us, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.fragment_about_us_parent_rv);
        rv.setHasFixedSize(true);
        AboutUsAdapter adapter = new AboutUsAdapter(getActivity().getResources().getStringArray(R.array.about_us_item));
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
                    String[] title = getActivity().getResources().getStringArray(R.array.about_us_item);
                    switch (position) {
                        case 0:
                            intent = new Intent(getActivity(), BlogDetailsActivity.class);
                            intent.putExtra("content", getActivity().getResources().getString(R.string.text_history));
                            intent.putExtra("image", R.drawable.aboutus_history);
                            intent.putExtra("hasExpandedTitle", false);

                            break;
                        case 1:
                            intent = new Intent(getActivity(), BlogDetailsActivity.class);
                            intent.putExtra("content", getActivity().getResources().getString(R.string.text_mission_vision));
                            intent.putExtra("image", R.drawable.aboutus_mission_vision);
                            intent.putExtra("hasExpandedTitle", false);
                            break;
                        case 2:
                            intent = new Intent(getActivity(), ReferralActivity.class);
                            intent.putExtra("image_urls", getActivity().getResources().getStringArray(R.array.sponsor_directors_urls));
                            intent.putExtra("content", getActivity().getResources().getStringArray(R.array.sponsor_directors_name));
                            break;
                        case 3:
                            intent = new Intent(getActivity(), ReferralActivity.class);
                            intent.putExtra("image_urls", getActivity().getResources().getStringArray(R.array.board_directors_urls));
                            intent.putExtra("content", getActivity().getResources().getStringArray(R.array.board_directors_name));
                            break;
                        case 4:
                            intent = new Intent(getActivity(), ReferralActivity.class);
                            intent.putExtra("image_urls", getActivity().getResources().getStringArray(R.array.executive_committe_urls));
                            intent.putExtra("content", getActivity().getResources().getStringArray(R.array.executive_committe_name));
                            break;
                        case 5:
                            intent = new Intent(getActivity(), ReferralActivity.class);
                            intent.putExtra("image_urls", getActivity().getResources().getStringArray(R.array.audit_committe_urls));
                            intent.putExtra("content", getActivity().getResources().getStringArray(R.array.audit_committe_name));
                            break;
                        case 6:
                            intent = new Intent(getActivity(), ReferralActivity.class);
                            intent.putExtra("image_urls", getActivity().getResources().getStringArray(R.array.risk_committee_urls));
                            intent.putExtra("content", getActivity().getResources().getStringArray(R.array.risk_committee_name));
                            break;
                        case 7:
                            intent = new Intent(getActivity(), ReferralActivity.class);
                            intent.putExtra("image_urls", getActivity().getResources().getStringArray(R.array.management_committe_urls));
                            intent.putExtra("content", getActivity().getResources().getStringArray(R.array.management_committe_name));
                            break;
                        case 8:
                            intent = new Intent(getActivity(), ReferralActivity.class);
                            intent.putExtra("image_urls", getActivity().getResources().getStringArray(R.array.asset_liability_committe_urls));
                            intent.putExtra("content", getActivity().getResources().getStringArray(R.array.asset_liability_committe_name));
                            break;
                        case 9:
                            intent = new Intent(getActivity(), GalleryActivity.class);
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


    class AboutUsAdapter extends RecyclerView.Adapter<AboutUsAdapter.AboutUsViewHolder> {
        private String[] mAboutUsItems;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class AboutUsViewHolder extends RecyclerView.ViewHolder {
            public CardView mParentCardView;
            public TextView mTitleTextView;

            public AboutUsViewHolder(View v) {
                super(v);

                mParentCardView = (CardView) v.findViewById(R.id.about_us_item_layout_parent_cv);
                mTitleTextView = (TextView) v.findViewById(R.id.about_us_item_layout_title_tv);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public AboutUsAdapter(String[] myDataset) {
            mAboutUsItems = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public AboutUsViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.about_us_item_layout, parent, false);
            // set the view's size, margins, paddings and layout parameters
            AboutUsViewHolder vh = new AboutUsViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(AboutUsViewHolder holder, int position) {
            holder.mTitleTextView.setText(mAboutUsItems[position]);
        }

        @Override
        public int getItemCount() {
            return mAboutUsItems.length;
        }
    }
}