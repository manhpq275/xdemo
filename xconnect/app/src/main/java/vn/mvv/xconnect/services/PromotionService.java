package vn.mvv.xconnect.services;

import com.android.volley.Request;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;

import vn.mvv.xconnect.XConnectApplication;
import vn.mvv.xconnect.apis.PromotionApi;
import vn.mvv.xconnect.interfaces.IGetPromotionCodeCallback;
import vn.mvv.xconnect.models.ErrorView;
import vn.mvv.xconnect.models.PromotionView;
import vn.mvv.xconnect.models.enums.XErrorCode;
import vn.mvv.xconnect.utils.CustomStringRequest;
import vn.mvv.xconnect.utils.XLog;

/**
 * Created by phuc.nguyen on 16/06/2016.
 */
public class PromotionService extends BaseService<PromotionView> {
    public PromotionService(){
        super(PromotionApi.getInstance(), PromotionView.class, PromotionView[].class);
    }

    private static PromotionService sInstance;

    public static PromotionService getInstance() {
        if (sInstance == null) sInstance = new PromotionService();
        return sInstance;
    }

    public void getPromotionCode(String consumnerId,String eventId ,final IGetPromotionCodeCallback callback) {
        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.GET, PromotionApi.getInstance().getPromotionCodeApiUrl(consumnerId,eventId), new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {
                XLog.d(PromotionService.class, response);
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
}
