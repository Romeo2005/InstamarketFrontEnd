package org.romeo.instamarketApp.activities.content;

public class ContentPresenter {
    private final ContentActivityTemplate activity;

    public ContentPresenter(ContentActivityTemplate activity) {
        this.activity = activity;
        activity.initViewPager();
    }
}
