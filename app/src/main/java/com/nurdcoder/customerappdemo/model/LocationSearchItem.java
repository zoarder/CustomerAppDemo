package com.nurdcoder.customerappdemo.model;

import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

/**
 * Created by to_mu on 3/23/2017.
 */

public class LocationSearchItem implements SearchSuggestion {
    public static final Creator<LocationSearchItem> CREATOR = new Creator<LocationSearchItem>() {
        @Override
        public LocationSearchItem createFromParcel(Parcel in) {
            return new LocationSearchItem(in);
        }

        @Override
        public LocationSearchItem[] newArray(int size) {
            return new LocationSearchItem[size];
        }
    };
    private String mAdress;
    private double mLatitude;
    private double mLongitude;

    public LocationSearchItem(String mAdress, double mLatitude, double mLongitude) {
        this.mAdress = mAdress;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
    }

    public LocationSearchItem(Parcel source) {
        this.mAdress = source.readString();
        this.mLatitude = source.readDouble();
        this.mLongitude = source.readDouble();
    }

    public String getAdress() {
        return mAdress;
    }

    public void setAdress(String mAdress) {
        this.mAdress = mAdress;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }

    @Override
    public String getBody() {
        return mAdress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mAdress);
        dest.writeDouble(mLatitude);
        dest.writeDouble(mLongitude);
    }
}
