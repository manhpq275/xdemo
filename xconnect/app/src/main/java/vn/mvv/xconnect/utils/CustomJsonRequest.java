package vn.mvv.xconnect.utils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import vn.mvv.xconnect.models.ErrorView;
import vn.mvv.xconnect.models.enums.XErrorCode;

/**
 * Created by phuc.nguyen on 5/19/2016.
 */
public class CustomJsonRequest extends JsonObjectRequest {
    public CustomJsonRequest(int method, String url, JSONObject jsonRequest,
                           final IResponseJsonCallback callback) {
        super(method, url, jsonRequest, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse != null) {
                    String responseData = new String(error.networkResponse.data);
                    XLog.e(CustomStringRequest.class, responseData);
                    if (error.networkResponse.statusCode == XErrorCode.Unauthorized.getValue()) {
                        callback.onError(new ArrayList<>(Arrays.asList(new ErrorView("", XErrorCode.Unauthorized.getValue()))));
                    } else {
                        try {
                            ArrayList<ErrorView> errorViews = AppUtils.parseErrorViews(responseData);
                            if (errorViews == null || errorViews.size() == 0) {
                                callback.onError(new ArrayList<>(Arrays.asList(new ErrorView("", XErrorCode.ConnectionError.getValue()))));
                            } else {
                                callback.onError(errorViews);
                            }
                        } catch (Exception ex) {
                            callback.onError(new ArrayList<>(Arrays.asList(new ErrorView("", XErrorCode.ConnectionError.getValue()))));
                        }
                    }
                } else
                    callback.onError(new ArrayList<>(Arrays.asList(new ErrorView("", XErrorCode.ConnectionError.getValue()))));
            }
        });
        setRetryPolicy(new DefaultRetryPolicy(AppConstants.VOLLEY_TIMEOUT, AppConstants.VOLLEY_MAX_NUM_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        if (volleyError.networkResponse != null) {
            volleyError = new VolleyError(volleyError.networkResponse);
        }
        return volleyError;
    }

    public interface IResponseJsonCallback {
        void onSuccess(JSONObject response);
        void onError(ArrayList<ErrorView> errors);
    }
}
