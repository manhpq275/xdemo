package vn.mvv.xconnect.services;

import com.android.volley.Request;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;

import vn.mvv.xconnect.XConnectApplication;
import vn.mvv.xconnect.apis.CommentApi;
import vn.mvv.xconnect.interfaces.IGetCommentsCallback;
import vn.mvv.xconnect.models.CommentView;
import vn.mvv.xconnect.models.ErrorView;
import vn.mvv.xconnect.models.enums.XErrorCode;
import vn.mvv.xconnect.utils.CustomStringRequest;
import vn.mvv.xconnect.utils.XLog;

/**
 * Created by admin on 6/15/16.
 */
public class CommentService extends BaseService<CommentView> {
    public CommentService(){
        super(CommentApi.getInstance(), CommentView.class, CommentView[].class);
    }

    private static CommentService sInstance;

    public static CommentService getInstance() {
        if (sInstance == null) sInstance = new CommentService();
        return sInstance;
    }
    public void getCommentsForEvent(int page, int quantity,String eventId ,final IGetCommentsCallback callback) {

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.GET, CommentApi.getInstance().getCommentsForEventApiUrl(page,quantity,eventId), new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {
                XLog.d(CommentService.class, response);
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
