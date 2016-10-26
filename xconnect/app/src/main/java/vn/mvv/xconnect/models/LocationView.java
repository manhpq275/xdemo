package vn.mvv.xconnect.models;

import android.graphics.Region;

/**
 * Created by phuc.nguyen on 5/26/2016.
 */
public class LocationView extends BaseView {
    private String address;
    private String phone;
    private int region;
    private double latitude;
    private double longitude;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public int getRegion() {
        return region;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
