package vn.mvv.xconnect.models.enums;

/**
 * Created by phuc.nguyen on 03/06/2016.
 */
public enum PromotionUnit {
    Undefined(0),
    Vnd(1),
    Usd(2),
    Percent(3);

    private int value;
    PromotionUnit(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static PromotionUnit fromInteger(int id){
        PromotionUnit[] values = PromotionUnit.values();
        for (PromotionUnit v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
}
