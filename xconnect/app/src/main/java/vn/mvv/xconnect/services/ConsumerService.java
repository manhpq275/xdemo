package vn.mvv.xconnect.services;

import com.android.volley.Request;
import com.google.gson.Gson;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;

import vn.mvv.xconnect.XConnectApplication;
import vn.mvv.xconnect.apis.ConsumerApi;
import vn.mvv.xconnect.apis.PromotionApi;
import vn.mvv.xconnect.interfaces.IGetPromotionCodeCallback;
import vn.mvv.xconnect.interfaces.ILoginCallback;
import vn.mvv.xconnect.models.ConsumerView;
import vn.mvv.xconnect.models.ErrorView;
import vn.mvv.xconnect.models.enums.ClientType;
import vn.mvv.xconnect.models.enums.XErrorCode;
import vn.mvv.xconnect.utils.CustomStringRequest;
import vn.mvv.xconnect.utils.XLog;

/**
 * Created by phuc.nguyen on 16/06/2016.
 */
public class ConsumerService extends BaseService<ConsumerView> {
    public ConsumerService(){
        super(ConsumerApi.getInstance(), ConsumerView.class, ConsumerView[].class);
    }

    private static ConsumerService sInstance;

    public static ConsumerService getInstance() {
        if (sInstance == null) sInstance = new ConsumerService();

        return sInstance;
    }

    public void login(String email, String password, ClientType clientType, final ILoginCallback callback) {
        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST, ConsumerApi.getInstance().loginApiUrl(email, password, clientType), new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {
                XLog.d(ConsumerService.class, response);
                Gson gson = new Gson();
                ConsumerView consumerView = gson.fromJson(response, ConsumerView.class);
                consumerView.setJson(response);
                XLog.d(ConsumerService.class, "======>ConsumerView: " + XConnectApplication.getInstance().getGson().toJson(consumerView));

                try {
                    callback.onSuccess(consumerView);
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
