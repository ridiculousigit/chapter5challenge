package binar.academy.chapter5challenge.network

import binar.academy.chapter5challenge.model.DataUser
import binar.academy.chapter5challenge.model.ResponseDataUserItem
import retrofit2.Call
import retrofit2.http.*

interface RestfulUser {

    @GET("user")
    fun getAllUser(): Call<List<ResponseDataUserItem>>

    @POST("user")
    fun addUser(@Body request: DataUser): Call<ResponseDataUserItem>

    @PUT("user/{id}")
    fun putUser(@Path("id") id: String, @Body request: DataUser): Call<List<ResponseDataUserItem>>
}