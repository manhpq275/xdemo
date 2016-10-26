package vn.mvv.xconnect.models;

import java.util.ArrayList;

/**
 * Created by dgm-dev3 on 5/25/2016.
 */
public class GroupExpandHomeForyou {
   private VendorView vendorView;
    private ArrayList<EventView> arrItems;

    public VendorView getVendorView() {
        return vendorView;
    }

    public void setVendorView(VendorView vendorView) {
        this.vendorView = vendorView;
    }

    public ArrayList<EventView> getArrItems() {
        return arrItems;
    }

    public void setArrItems(ArrayList<EventView> arrItems) {
        this.arrItems = arrItems;
    }
}
