package vn.mvv.xconnect.services;

import vn.mvv.xconnect.apis.VendorApi;
import vn.mvv.xconnect.models.VendorView;

/**
 * Created by phuc.nguyen on 5/18/2016.
 */
public class VendorService extends BaseService<VendorView> {
    public VendorService(){
        super(VendorApi.getInstance(), VendorView.class, VendorView[].class);
    }
    private static VendorService sInstance;

    public static VendorService getInstance() {
        if (sInstance == null) sInstance = new VendorService();
        return sInstance;
    }
}
