package com.firstlink.duo.network.api

import com.google.gson.JsonObject
import retrofit2.http.*
import rx.Observable

/**
 * Created by wzq on 16/5/13.
 * Be part of []
 */
interface ProductApi {

    @FormUrlEncoded
    @POST("product/find_index_datas.json")
    fun getProductList(@FieldMap map: Map<String, String>) : Observable<JsonObject>

    @GET("tab_page/find_tab_pages.json")
    fun getNations(@QueryMap map: Map<String, String>) : Observable<JsonObject>

    @FormUrlEncoded
    @POST("product/find_products_by_topic.json")
    fun getTopic(@FieldMap map: Map<String, String>) : Observable<JsonObject>
}