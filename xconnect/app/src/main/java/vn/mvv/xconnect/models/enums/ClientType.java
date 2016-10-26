package vn.mvv.xconnect.models.enums;

/**
 * Created by phuc.nguyen on 5/25/2016.
 */
public enum ClientType {
    Undefined(0),
    Web(1),
    AndroidApp(2),
    IosApp(3),
    WindowsPhoneApp(4);

    private int value;
    ClientType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static ClientType fromInteger(int id){
        ClientType[] values = ClientType.values();
        for (ClientType v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
}
