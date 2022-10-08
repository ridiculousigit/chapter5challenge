package binar.academy.chapter5challenge.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProduct {
    private const val BASE_URL = "https://6331b39e3ea4956cfb654d4c.mockapi.io/"

    val instance : RestfulProduct by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(RestfulProduct :: class.java)
    }
}