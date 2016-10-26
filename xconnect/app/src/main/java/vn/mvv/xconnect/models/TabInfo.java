package vn.mvv.xconnect.models;

/**
 * Created by phuc.nguyen on 5/24/2016.
 */
public class TabInfo{
    private String title;
    private int icon;

    public TabInfo(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
