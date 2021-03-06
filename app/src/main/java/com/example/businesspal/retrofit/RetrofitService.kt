package com.example.businesspal.retrofit

import com.example.businesspal.model.BusinessDataModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

/**
 * Local debug server Address:
 *http://192.168.29.126:8080/
 */

interface RetrofitService {

    @GET("read")
    fun getAllData(): Call<List<BusinessDataModel>>

    @POST("write")
    fun writeData(@Body dataModal: BusinessDataModel?): Call<BusinessDataModel?>?

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://business-pal-api.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}
