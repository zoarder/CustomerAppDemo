package com.nurdcoder.customerappdemo.model;

/**
 * Created by to_mu on 3/6/2017.
 */

public class StoreItem {
    private String mStoreName;
    private String mStoreMobileNo;
    private double mStoreLatitude;
    private double mStoreLongitude;
    private String mStoreAddress;

    public StoreItem(String mStoreName, String mStoreMobileNo, double mStoreLatitude, double mStoreLongitude, String mStoreAddress) {
        this.mStoreName = mStoreName;
        this.mStoreMobileNo = mStoreMobileNo;
        this.mStoreLatitude = mStoreLatitude;
        this.mStoreLongitude = mStoreLongitude;
        this.mStoreAddress = mStoreAddress;
    }

    public String getStoreName() {
        return mStoreName;
    }

    public void setStoreName(String mStoreName) {
        this.mStoreName = mStoreName;
    }

    public String getStoreMobileNo() {
        return mStoreMobileNo;
    }

    public void setStoreMobileNo(String mStoreMobileNo) {
        this.mStoreMobileNo = mStoreMobileNo;
    }

    public double getStoreLatitude() {
        return mStoreLatitude;
    }

    public void setStoreLatitude(double mStoreLatitude) {
        this.mStoreLatitude = mStoreLatitude;
    }

    public double getStoreLongitude() {
        return mStoreLongitude;
    }

    public void setStoreLongitude(double mStoreLongitude) {
        this.mStoreLongitude = mStoreLongitude;
    }

    public String getStoreAddress() {
        return mStoreAddress;
    }

    public void setStoreAddress(String mStoreAddress) {
        this.mStoreAddress = mStoreAddress;
    }
}
