package vn.mvv.xconnect.models;

import vn.mvv.xconnect.models.enums.PromotionType;
import vn.mvv.xconnect.models.enums.PromotionUnit;

/**
 * Created by phuc.nguyen on 03/06/2016.
 */
public class EventInVendorView extends BaseView {
    private String endDate;
    private PromotionType promotionType;
    private PromotionUnit promotionUnit;
    private double promotionValue;
    private String coverImageUrl;
    private long numberOfLikes;
    private long numberOfComments;

    public String getEndDate() {
        return endDate;
    }

    public PromotionType getPromotionType() {
        return promotionType;
    }

    public PromotionUnit getPromotionUnit() {
        return promotionUnit;
    }

    public double getPromotionValue() {
        return promotionValue;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public long getNumberOfLikes() {
        return numberOfLikes;
    }

    public long getNumberOfComments() {
        return numberOfComments;
    }
}
