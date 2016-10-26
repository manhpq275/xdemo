package vn.mvv.xconnect.models.enums;

/**
 * Created by PHUC-PC on 5/25/2016.
 */
public enum SortBy {
    Undefined(0),
    Code(1),
    Name(2),
    EditedBy(3),
    LastModified(4),
    CreatedDate(5),
    Email(6);

    private int value;
    SortBy(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static SortBy fromInteger(int id){
        SortBy[] values = SortBy.values();
        for (SortBy v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
}
