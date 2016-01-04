package com.firstlink.duo.model

import com.google.gson.annotations.SerializedName

/**
 * Created by wzq on 16/1/3.
 */
data class Pager(
        @SerializedName("start_row")
        val startRow: Int = 0,
        @SerializedName("page_size")
        val pageSize: Int = 0,
        val total: Int = 0
)