package vn.mvv.xconnect.services;
import com.android.volley.Request;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;

import vn.mvv.xconnect.XConnectApplication;
import vn.mvv.xconnect.apis.BaseApi;
import vn.mvv.xconnect.interfaces.IBaseGetItemCallback;
import vn.mvv.xconnect.interfaces.IBaseGetItemsCallback;
import vn.mvv.xconnect.models.BaseView;
import vn.mvv.xconnect.models.ErrorView;
import vn.mvv.xconnect.models.enums.SortBy;
import vn.mvv.xconnect.models.enums.SortDirection;
import vn.mvv.xconnect.models.enums.XErrorCode;
import vn.mvv.xconnect.utils.CustomStringRequest;
import vn.mvv.xconnect.utils.XLog;

/**
 * Created by phuc.nguyen on 5/17/2016.
 */
public abstract class BaseService<T extends BaseView> {
    private BaseApi<T> baseApi;
    private Class<T> singleType;
    private Class<T[]> arrayType;

    public BaseService(BaseApi<T> baseApi, Class<T> singleType, Class<T[]> arrayType){
        this.baseApi = baseApi;
        this.singleType = singleType;
        this.arrayType = arrayType;
    }

    public void getItem(String idOrCode, final IBaseGetItemCallback<T> callback) {
        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.GET, baseApi.getItemApiUrl(idOrCode), new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {
                XLog.d(BaseService.class, response);
                try {
                    callback.onSuccess(parseItemView(response));
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

    public void getItems(int page, int quantity, String searchValue, SortBy sortBy, SortDirection sortDirection, final IBaseGetItemsCallback<T> callback) {
        XLog.d(BaseService.class,"baseApi = " + baseApi.getItemsApiUrl(page, quantity, searchValue, sortBy, sortDirection));
        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.GET, baseApi.getItemsApiUrl(page, quantity, searchValue, sortBy, sortDirection), new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {
                XLog.d(BaseService.class, response);
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

    protected T parseItemView(String json) {
       return parseItemView(json, singleType);
    }

    protected <TView extends BaseView> TView parseItemView(String json, Class<TView> type) {
        Gson gson = XConnectApplication.getInstance().getGson();
        return gson.fromJson(json, type);
    }

    protected ArrayList<T> parseItemViews(String json) {
        return parseItemViews(json, arrayType);
    }

    protected <TView extends BaseView> ArrayList<TView > parseItemViews(String json, Class<TView[]> type) {
        Gson gson = XConnectApplication.getInstance().getGson();
        ArrayList<TView> itemViews = new ArrayList<>();
        TView[] parses = gson.fromJson(json, type);
        itemViews.addAll(Arrays.asList(parses));
        return itemViews;
    }
}

