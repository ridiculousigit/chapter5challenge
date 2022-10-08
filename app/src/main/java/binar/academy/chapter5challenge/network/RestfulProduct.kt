package binar.academy.chapter5challenge.network

import binar.academy.chapter5challenge.model.DataProduct
import binar.academy.chapter5challenge.model.ResponseDataProductItem
import binar.academy.chapter5challenge.model.ResponseDataUserItem
import retrofit2.Call
import retrofit2.http.*

interface RestfulProduct {

    @GET("product")
    fun getAllProduct(): Call<List<ResponseDataProductItem>>

    @GET("product/{id}")
    fun getDetail(@Path("id") id: Int): Call<List<ResponseDataUserItem>>

    @POST("product")
    fun addProduct(@Body product: DataProduct): Call<ResponseDataProductItem>

    @PUT("product/{id}")
    fun updateProduct(@Path("id") id: Int, @Body product: DataProduct): Call<List<ResponseDataProductItem>>

    @DELETE("product/{id}")
    fun deleteProduct(@Path("id") id: Int): Call<Int>
}