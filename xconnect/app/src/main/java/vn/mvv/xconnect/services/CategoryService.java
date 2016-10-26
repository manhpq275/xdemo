package vn.mvv.xconnect.services;

import com.android.volley.Request;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;

import vn.mvv.xconnect.XConnectApplication;
import vn.mvv.xconnect.apis.CategoryApi;
import vn.mvv.xconnect.interfaces.IGetCategoryListCallback;
import vn.mvv.xconnect.models.CategoryView;
import vn.mvv.xconnect.models.ErrorView;
import vn.mvv.xconnect.models.enums.XErrorCode;
import vn.mvv.xconnect.utils.CustomStringRequest;
import vn.mvv.xconnect.utils.XLog;

/**
 * Created by phuc.nguyen on 5/18/2016.
 */
public class CategoryService extends BaseService<CategoryView> {
    public CategoryService(){
        super(CategoryApi.getInstance(), CategoryView.class, CategoryView[].class);
    }

    private static CategoryService sInstance;

    public static CategoryService getInstance() {
        if (sInstance == null) sInstance = new CategoryService();

        return sInstance;
    }

    public void getCategories(final IGetCategoryListCallback callback) {
        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.GET, CategoryApi.getInstance().getCategoriesApiUrl(), new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {
                XLog.d(CategoryService.class, response);
                try {
                    callback.onSuccess(parseItemViews(response));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    StringWriter trace = new StringWriter();
                    ex.printStackTrace(new PrintWriter(trace));
                    callback.onError(new ArrayList<>(Arrays.asList(new ErrorView(ex.getMessage(), XErrorCode.GeneralError.getValue()))));
                }
            }

            @Override
            public void onError(ArrayList<ErrorView> errors) {
                callback.onError(errors);
            }
        });
        XConnectApplication.getInstance().addToRequestQueue(baseStringRequest);
    }
}
