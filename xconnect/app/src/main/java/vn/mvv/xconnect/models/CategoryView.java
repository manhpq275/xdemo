package vn.mvv.xconnect.models;

import android.text.TextUtils;

import vn.mvv.xconnect.utils.AppUtils;

/**
 * Created by phuc.nguyen on 5/17/2016.
 */
public class CategoryView extends BaseView {
    private String uniqueKey;
    private int imgIcon;
    private String description;

    public String getDescription() {
        if (this.description.equals(TextUtils.isEmpty(uniqueKey))) {
            this.description = AppUtils.getResourceStringByKey(this.uniqueKey);
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String key) {
        this.uniqueKey = key;
    }

    public int getImgIcon() {
        if (this.imgIcon == 0) {
            this.imgIcon = AppUtils.getCategoryIcon(this.uniqueKey);
        }
        ;
        return imgIcon;
    }

    public void setImgIcon(int imgIcon) {
        this.imgIcon = imgIcon;
    }
}
