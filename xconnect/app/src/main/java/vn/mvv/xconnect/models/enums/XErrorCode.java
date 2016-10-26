package vn.mvv.xconnect.models.enums;

import com.google.gson.annotations.SerializedName;

import vn.mvv.xconnect.utils.AppConstants;
import vn.mvv.xconnect.utils.AppUtils;

/**
 * Created by phuc.nguyen on 5/19/2016.
 */
public enum XErrorCode {
    @SerializedName("401")
    Unauthorized(401),

    @SerializedName("1")
    ConnectionError(1),

    @SerializedName("1000")
    GeneralError(1000),

    @SerializedName("1005")
    ItemNotFound(1005);

    private int value;

    XErrorCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static XErrorCode fromInteger(int id){
        XErrorCode[] values = XErrorCode.values();
        for (XErrorCode v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }

    @Override
    public String toString() {
        if (value > 0)
            return AppUtils.getResourceStringByKey(AppConstants.ERROR_PREFIX + value);
        else
            return AppUtils.getResourceStringByKey(AppConstants.ERROR_PREFIX + XErrorCode.ConnectionError.getValue());
    }
}
