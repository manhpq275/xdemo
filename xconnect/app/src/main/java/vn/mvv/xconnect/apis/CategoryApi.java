package vn.mvv.xconnect.apis;

import vn.mvv.xconnect.models.CategoryView;
import vn.mvv.xconnect.utils.AppConstants;

/**
 * Created by phuc.nguyen on 5/18/2016.
 */
public class CategoryApi extends BaseApi<CategoryView> {
    private static CategoryApi sInstance;
    public static CategoryApi getInstance() {
        if (sInstance == null) sInstance = new CategoryApi();
        return sInstance;
    }

    public CategoryApi() {
        super("api/category");
    }

    public String getCategoriesApiUrl() {
        return AppConstants.ROOT_DATA_API_URL + "/" + this.getBaseUrl() + "/getcategories";
    }
}


