package org.romeo.instamarketApp.activities.content

class ContentPresenter(activity: ContentActivityTemplate) {
    init {
        activity.showProgressbar()

        activity.initViewPager()

        activity.hideProgressbar()
    }
}