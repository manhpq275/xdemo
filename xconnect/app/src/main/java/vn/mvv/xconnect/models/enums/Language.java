package vn.mvv.xconnect.models.enums;

/**
 * Created by phuc.nguyen on 10/06/2016.
 */
public enum Language {
    Vietnamese(1),
    English(2);

    private int value;
    Language(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static Language fromInteger(int id){
        Language[] values = Language.values();
        for (Language v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
}
