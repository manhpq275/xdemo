package vn.mvv.xconnect.interfaces;

import java.util.ArrayList;

import vn.mvv.xconnect.models.BaseView;
import vn.mvv.xconnect.models.ErrorView;

/**
 * Created by phuc.nguyen on 5/17/2016.
 */
public interface IBaseGetItemsCallback<T extends BaseView> {
    void onSuccess(ArrayList<T> items);
    void onError(ArrayList<ErrorView> errors);
}
