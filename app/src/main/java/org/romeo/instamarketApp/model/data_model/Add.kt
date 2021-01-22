package org.romeo.instamarketApp.model.data_model

/**
 * A model received from the server.
 * In future it will be shown in the
 * AddsFragment.
 * */
class Add(
        var title: String,
        var src_media: String,
        var description: String,
        var minFollowersNumber: Int,
        var maxPrise: Int,
        var user_PK: Int)