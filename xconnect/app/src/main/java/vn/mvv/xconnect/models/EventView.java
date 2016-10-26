package vn.mvv.xconnect.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import vn.mvv.xconnect.models.enums.PromotionType;
import vn.mvv.xconnect.models.enums.PromotionUnit;
import vn.mvv.xconnect.utils.AppUtils;

/**
 * Created by phuc.nguyen on 5/18/2016.
 */
public class EventView extends BaseView {
    private Map<Integer, String> description;
    private String phone;
    private Date startDate;
    private Date endDate;
    private int codeType;
    private int promotionType;
    private int promotionUnit;
    private double promotionValue;
    private Map<Integer, String> guideline;
    private int timeGetCode;
    private String coverImageUrl;
    private ArrayList<String> imageUrls;
    private long numberOfLikes;
    private long numberOfComments;
    private boolean isLiked;
    private boolean isFavourite;
    private ArrayList<LocationView> locations;
    private VendorView vendor;
    private PromotionView promotion;

    public VendorView getVendor() {
        return vendor;
    }

    public Map<Integer, String> getDescription() {
        return description;
    }

    public String getPhone() {
        return phone;
    }

    public Date getEndDate() {
        return endDate;
    }

    public PromotionView getPromotion() {
        return promotion;
    }

    public int getPromotionType() {
        return promotionType;
    }

    public double getPromotionValue() {
        return promotionValue;
    }

   public Map<Integer, String> getGuideline() {
        return guideline;
    }

    public int getTimeGetCode() {
        return timeGetCode;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public ArrayList<String> getImageUrls() {
        return imageUrls;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getPromotionUnit() {
        return promotionUnit;
    }

    public long getNumberOfLikes() {
        return numberOfLikes;
    }

    public long getNumberOfComments() {
        return numberOfComments;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public int getCodeType() {
        return codeType;
    }

    public ArrayList<LocationView> getLocations() {
        return locations;
    }
}
