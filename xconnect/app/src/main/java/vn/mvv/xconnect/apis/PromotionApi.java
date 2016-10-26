package vn.mvv.xconnect.apis;

import vn.mvv.xconnect.models.PromotionView;
import vn.mvv.xconnect.utils.AppConstants;

/**
 * Created by phuc.nguyen on 16/06/2016.
 */
public class PromotionApi extends BaseApi<PromotionView> {
    private static PromotionApi sInstance;
    public static PromotionApi getInstance() {
        if (sInstance == null) sInstance = new PromotionApi();
        return sInstance;
    }

    public PromotionApi() {
        super("api/promotion");
    }

    public String getPromotionCodeApiUrl(String consumerID,String eventId) {
        return String.format(AppConstants.API_PROMOTION_GENERATE_CODE_PATTERN, this.getBaseUrl(),consumerID, eventId);
    }
}
