package vn.mvv.xconnect.apis;

import vn.mvv.xconnect.models.VendorView;

/**
 * Created by phuc.nguyen on 5/18/2016.
 */
public class VendorApi extends BaseApi<VendorView> {
    private static VendorApi sInstance;

    public static VendorApi getInstance() {
        if (sInstance == null) sInstance = new VendorApi();
        return sInstance;
    }

    public VendorApi(){
        super("api/vendor");
    }
}
