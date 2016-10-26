package vn.mvv.xconnect.models;

import java.util.Date;

/**
 * Created by phuc.nguyen on 16/06/2016.
 */
public class PromotionView extends BaseView{
    private String promotionCode;
    private Date expiredDate;

    public Date getExpiredDate() {
        return expiredDate;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

}
