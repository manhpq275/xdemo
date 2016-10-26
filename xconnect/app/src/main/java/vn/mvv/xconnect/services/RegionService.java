package vn.mvv.xconnect.services;

import java.util.ArrayList;
import vn.mvv.xconnect.apis.RegionApi;
import vn.mvv.xconnect.models.RegionView;

/**
 * Created by phuc.nguyen on 5/20/2016.
 */
public class RegionService extends BaseService<RegionView> {
    public RegionService(){
        super(RegionApi.getInstance(), RegionView.class, RegionView[].class);
    }

    private static RegionService sInstance;

    public static RegionService getInstance() {
        if (sInstance == null) sInstance = new RegionService();
        return sInstance;
    }

    //TODO: Get regions from API
    public ArrayList<String> getRegions(){

        ArrayList<String> regions = new ArrayList<>();
        regions.add("Hà Nội");
        regions.add("TP HCM");
        regions.add("Đà Nẵng");
        return regions;
    }
}
