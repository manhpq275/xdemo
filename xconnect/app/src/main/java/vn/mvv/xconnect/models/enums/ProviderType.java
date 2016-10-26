package vn.mvv.xconnect.models.enums;

/**
 * Created by phuc.nguyen on 5/17/2016.
 */
public enum ProviderType {
    Undefined(0),
    Facebook(1),
    Google(2);

    private int value;
    ProviderType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static ProviderType fromInteger(int id){
        ProviderType[] values = ProviderType.values();
        for (ProviderType v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
}
