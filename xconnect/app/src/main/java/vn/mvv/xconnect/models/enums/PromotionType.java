package vn.mvv.xconnect.models.enums;

/**
 * Created by phuc.nguyen on 5/26/2016.
 */
public enum PromotionType {
    Undefined(0),
    Discount(1),
    Other(2);

    private int value;
    PromotionType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static PromotionType fromInteger(int id){
        PromotionType[] values = PromotionType.values();
        for (PromotionType v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
}
