package vn.mvv.xconnect.models;

/**
 * Created by phuc.nguyen on 5/19/2016.
 */
public class ErrorView {
    private String field;
    private int errorCode;

    public ErrorView(String field, int errorCode) {
        this.field = field;
        this.errorCode = errorCode;
    }

    public String getField() {
        return field;
    }

    public int getErrorCode(){
        return errorCode;
    }

    @Override
    public String toString() {
        return "Error{" +
                "field='" + field + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
