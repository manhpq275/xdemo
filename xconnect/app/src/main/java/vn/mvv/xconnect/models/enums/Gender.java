package vn.mvv.xconnect.models.enums;

/**
 * Created by phuc.nguyen on 5/17/2016.
 */
public enum Gender {
    Both(0),
    Male(1),
    Female(2);

    private int value;
    Gender(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static Gender fromInteger(int id){
        Gender[] values = Gender.values();
        for (Gender v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
}
