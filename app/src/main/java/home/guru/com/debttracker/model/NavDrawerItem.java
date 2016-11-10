package home.guru.com.debttracker.model;

/**
 * Created by Priyanka23 on 8/26/2016.
 */
public class NavDrawerItem {

    private boolean mShowNotify;
    private String mTitle;


    public NavDrawerItem() {

    }

    public NavDrawerItem(boolean showNotify, String title) {
        this.mShowNotify = showNotify;
        this.mTitle = title;
    }

    public boolean isShowNotify() {
        return mShowNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.mShowNotify = showNotify;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }
}
