package vn.mvv.xconnect.apis;

import vn.mvv.xconnect.models.RegionView;

/**
 * Created by phuc.nguyen on 5/20/2016.
 */
public class RegionApi extends BaseApi<RegionView> {
    private static RegionApi sInstance;

    public static RegionApi getInstance() {
        if (sInstance == null) sInstance = new RegionApi();
        return sInstance;
    }

    public RegionApi(){
        super("api/region");
    }
}
