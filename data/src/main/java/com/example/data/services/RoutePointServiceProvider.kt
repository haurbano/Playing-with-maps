package com.example.data.services

import com.example.data.models.RoutePointsResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

class RoutePointServiceProvider {
    private val BASE_URL = "https://maps.googleapis.com/maps/api/directions/"
    private val retrofitClient by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun get(): PointService {
        return retrofitClient.create(PointService::class.java)
    }


    interface PointService {
        @GET("json")
        fun getRoutePoints(
            @Query("origin") origin: String,
            @Query("destination") destination: String,
            @Query("key") key: String
        ): Single<RoutePointsResponse>
    }
}