package vn.mvv.xconnect.apis;

import vn.mvv.xconnect.models.BaseView;
import vn.mvv.xconnect.models.enums.SortBy;
import vn.mvv.xconnect.models.enums.SortDirection;
import vn.mvv.xconnect.utils.AppConstants;

/**
 * Created by phuc.nguyen on 5/18/2016.
 */
public abstract class BaseApi<T extends BaseView> {
    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private String baseUrl;

    public BaseApi(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getItemApiUrl(String id) {
        return String.format(AppConstants.API_GET_ITEM_PATTERN, this.baseUrl, id);
    }

    public String getItemsApiUrl(int page, int quantity, String searchValue, SortBy sortBy, SortDirection sortDirection) {
        return String.format(AppConstants.API_GET_ITEMS_PATTERN, this.baseUrl, page, quantity,searchValue, sortBy, sortDirection);
    }
}
