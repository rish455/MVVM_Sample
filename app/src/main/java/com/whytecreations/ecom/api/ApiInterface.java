package com.whytecreations.ecom.api;

import com.whytecreations.ecom.data.remote.LoginResp;
import com.whytecreations.ecom.data.remote.ProductResp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author Rishad
 * @since 16/08/2021
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    Call<LoginResp> login(@Field("phone") String phone,
                          @Field("password") String password);

    @FormUrlEncoded
    @POST("list_products")
    Call<ProductResp> listProduct(
            /*@Field("main_category") String mainCategory,
            @Field("sub_category") String subCategory,
            @Field("child_category") String childCategory,
            @Field("keywords") String keyword,
            @Field("price_filter") String priceFilter,
            @Field("shop_id") String shopId,
            @Field("new_collection") String newCollection,
            @Field("best_sellers") String bestSeller,
            @Field("brands_id"), String brandId*/
            @Field("page") int page);
}