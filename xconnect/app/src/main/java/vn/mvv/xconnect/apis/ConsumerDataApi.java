package vn.mvv.xconnect.apis;

import vn.mvv.xconnect.models.ConsumerDataView;
import vn.mvv.xconnect.utils.AppConstants;

/**
 * Created by phuc.nguyen on 5/26/2016.
 */
public class ConsumerDataApi extends BaseApi<ConsumerDataView> {
    private static ConsumerDataApi sInstance;
    public static ConsumerDataApi getInstance() {
        if (sInstance == null) sInstance = new ConsumerDataApi();
        return sInstance;
    }

    public ConsumerDataApi() {
        super("api/consumerdata");
    }

    public String updateFavouriteVendorApiUrl(String id, boolean isFavourite) {
        return String.format(AppConstants.API_CONSUMER_DATA_UPDATE_FAVOURITE_VENDOR_PATTERN, this.getBaseUrl(), id, isFavourite);
    }

    public String updateFavouriteEventApiUrl(String id, boolean isFavourite) {
        return String.format(AppConstants.API_CONSUMER_DATA_UPDATE_FAVOURITE_EVENT_PATTERN, this.getBaseUrl(), id, isFavourite);
    }

    public String updateLikeEventApiUrl(String id, boolean isFavourite) {
        return String.format(AppConstants.API_CONSUMER_DATA_UPDATE_LIKE_EVENT_PATTERN, this.getBaseUrl(), id, isFavourite);
    }

    public String getFavouriteVendorsApiUrl(int page, int quantity) {
        return String.format(AppConstants.API_CONSUMER_DATA_GET_FAVOURITE_VENDORS_PATTERN, this.getBaseUrl(), page, quantity);
    }

    public String getFavouriteEventsApiUrl(int page, int quantity) {
        return String.format(AppConstants.API_CONSUMER_DATA_GET_FAVOURITE_EVENTS_PATTERN, this.getBaseUrl(), page, quantity);
    }

    public String getUnfavouriteVendorsApiUrl(int page, int quantity, String searchValue) {
        return String.format(AppConstants.API_CONSUMER_DATA_GET_UNFAVOURITE_VENDORS_PATTERN, this.getBaseUrl(), page, quantity, searchValue);
    }

    public String getSuggestedVendorsApiUrl(int page, int quantity) {
        return String.format(AppConstants.API_CONSUMER_DATA_GET_SUGGESTED_VENDORS_PATTERN, this.getBaseUrl(), page, quantity);
    }

    public String getEventsFromFavouriteVendors(int page, int quantity, int numOfEventsPerVendor) {
        return String.format(AppConstants.API_CONSUMER_DATA_GET_EVENTS_FROM_FAVOURITE_VENDORS_PATTERN, this.getBaseUrl(), page, quantity, numOfEventsPerVendor);
    }
}
