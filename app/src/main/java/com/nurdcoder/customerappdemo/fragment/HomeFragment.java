package com.nurdcoder.customerappdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.nurdcoder.customerappdemo.R;
import com.nurdcoder.customerappdemo.other.DateTimeUtils;

import java.util.Calendar;

/**
 * Created by Ratan on 7/27/2015.
 */
public class HomeFragment extends Fragment {

    private ViewFlipper viewFlipper;
    private TextView time;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View x = inflater.inflate(R.layout.fragment_home, null);
        viewFlipper = (ViewFlipper) x.findViewById(R.id.ViewFlipper01);
        time = (TextView) x.findViewById(R.id.reference_pharmacopeia);
        Calendar calendar = Calendar.getInstance();
        time.setText("Last Updated: " + DateTimeUtils.getDateTimeFromMilliSeconds(calendar.getTimeInMillis(), DateTimeUtils.DATE_TIME_FORMAT));
        viewFlipper.startFlipping();
        // Next screen comes in from left.
        viewFlipper.setInAnimation(getActivity(), R.anim.slide_in_from_left);
        // Current screen goes out from right.
        viewFlipper.setOutAnimation(getActivity(), R.anim.slide_out_to_right);
        viewFlipper.setFlipInterval(3000);

        return x;

    }
}
