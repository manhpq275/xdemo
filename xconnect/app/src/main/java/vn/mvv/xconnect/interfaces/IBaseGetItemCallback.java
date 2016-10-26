package vn.mvv.xconnect.interfaces;

import java.util.ArrayList;

import vn.mvv.xconnect.models.BaseView;
import vn.mvv.xconnect.models.ErrorView;

/**
 * Created by phuc.nguyen on 5/18/2016.
 */
public interface IBaseGetItemCallback<T extends BaseView>  {
    void onSuccess(T item);
    void onError(ArrayList<ErrorView> errors);
}
