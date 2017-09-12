package com.github.lakeshire.frozen

import com.google.gson.annotations.SerializedName

class ListEntity<T> {
    var start: Int = 0
    var count: Int = 0
    var total: Int = 0
    var title: String? = null

    @SerializedName("subjects")
    var list: List<T>? = null
}
