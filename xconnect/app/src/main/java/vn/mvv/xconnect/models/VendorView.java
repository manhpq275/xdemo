package vn.mvv.xconnect.models;

import java.util.ArrayList;

import vn.mvv.xconnect.utils.AppUtils;

/**
 * Created by phuc.nguyen on 5/18/2016.
 */
public class VendorView extends BaseView {
    private String description;
    private String email;
    private String phone;
    private String website;
    private String logoUrl;
    private boolean isFavourite;
    private ArrayList<String> imageUrls;
    private ArrayList<LocationView> locations;
    private ArrayList<EventInVendorView> events;

    public ArrayList<LocationView> getLocations() {
        return locations;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public ArrayList<String> getImageUrls() {
        return imageUrls;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public ArrayList<EventInVendorView> getEvents() {
        return events;
    }
}
