package vn.mvv.xconnect.models.enums;

/**
 * Created by phuc.nguyen on 10/06/2016.
 */
public enum CodeType {
    Undefined(0),
    AutoGenerate(1),
    Input(2);

    private int value;
    CodeType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static CodeType fromInteger(int id){
        CodeType[] values = CodeType.values();
        for (CodeType v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
}
