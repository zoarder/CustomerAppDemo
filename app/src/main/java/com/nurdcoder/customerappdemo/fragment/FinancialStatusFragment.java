package com.nurdcoder.customerappdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nurdcoder.customerappdemo.R;

/**
 * Created by Ratan on 7/27/2015.
 */
public class FinancialStatusFragment extends Fragment {

    private TabLayout mParentTabLayout;
    private ViewPager mParentViewPager;
    private int mTabItemsCount = 6;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View x = inflater.inflate(R.layout.fragment_financial_status, null);
        mParentTabLayout = (TabLayout) x.findViewById(R.id.fragment_financial_status_parent_tl);
        mParentViewPager = (ViewPager) x.findViewById(R.id.fragment_financial_status_parent_vp);

        /**
         *Set an Apater for the View Pager
         */
        mParentViewPager.setAdapter(new FinancialStatusAdapter(getChildFragmentManager()));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        mParentTabLayout.post(new Runnable() {
            @Override
            public void run() {
                mParentTabLayout.setupWithViewPager(mParentViewPager);
            }
        });

        return x;

    }

    class FinancialStatusAdapter extends FragmentPagerAdapter {

        public FinancialStatusAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FinancialStatementFragment();
                case 1:
                    return new AnnualReportFragment();
                case 2:
                    return new InterestRatesFragment();
                case 3:
                    return new TransactionChargesFragment();
                case 4:
                    return new CreditReportFragment();
                case 5:
                    return new InvestorsRelationFragment();
            }
            return null;
        }

        @Override
        public int getCount() {

            return mTabItemsCount;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return "Financial Statement";
                case 1:
                    return "Annual Report";
                case 2:
                    return "Interest Rates";
                case 3:
                    return "Transaction Charges";
                case 4:
                    return "Credit Report";
                case 5:
                    return "Investor's Relation";
            }
            return null;
        }
    }

}
