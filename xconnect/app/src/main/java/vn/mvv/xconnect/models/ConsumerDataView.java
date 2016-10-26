package vn.mvv.xconnect.models;

import java.util.ArrayList;

/**
 * Created by phuc.nguyen on 5/26/2016.
 */
public class ConsumerDataView extends BaseView  {
    private ConsumerView consumer;
    private ArrayList<VendorView> vendors;

    public ConsumerView getConsumer() {
        return consumer;
    }

    public ArrayList<VendorView> getVendors() {
        return vendors;
    }
}
