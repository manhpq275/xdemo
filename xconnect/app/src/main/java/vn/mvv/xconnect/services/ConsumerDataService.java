package vn.mvv.xconnect.services;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import vn.mvv.xconnect.XConnectApplication;
import vn.mvv.xconnect.apis.ConsumerDataApi;
import vn.mvv.xconnect.interfaces.IGetVendorListCallback;
import vn.mvv.xconnect.models.ConsumerDataView;
import vn.mvv.xconnect.models.ErrorView;
import vn.mvv.xconnect.models.VendorView;
import vn.mvv.xconnect.models.enums.XErrorCode;
import vn.mvv.xconnect.utils.AppConstants;
import vn.mvv.xconnect.utils.AppUtils;
import vn.mvv.xconnect.utils.CustomStringRequest;
import vn.mvv.xconnect.utils.XLog;

/**
 * Created by phuc.nguyen on 5/26/2016.
 */
public class ConsumerDataService extends BaseService<ConsumerDataView> {
    public ConsumerDataService(){
        super(ConsumerDataApi.getInstance(), ConsumerDataView.class, ConsumerDataView[].class);
    }

    private static ConsumerDataService sInstance;

    public static ConsumerDataService getInstance() {
        if (sInstance == null) sInstance = new ConsumerDataService();
        return sInstance;
    }

    public void getFavouriteVendors(int page, int quantity, final String token, final IGetVendorListCallback callback) {
        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.GET, ConsumerDataApi.getInstance().getFavouriteVendorsApiUrl(page, quantity), new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {
                XLog.d(ConsumerDataApi.class, response);
                try {
                    callback.onSuccess(parseItemViews(response, VendorView[].class));
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
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put(AppConstants.AUTHORIZATION_HEADER_KEY, AppUtils.buildTokenValue(token));
                return headers;
            }
        };
        XConnectApplication.getInstance().addToRequestQueue(baseStringRequest);
    }
}
