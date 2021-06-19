package com.aya.emitter.network

import com.aya.emitter.`interface`.ApiInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


object  RetrofitClient  {
    var Base_Url = "https://jsonplaceholder.typicode.com/"
    private lateinit var mInstance: RetrofitClient
    private var retrofit: Retrofit? = null

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        val client = OkHttpClient()
        client.newBuilder()
            .addInterceptor(loggingInterceptor)
            .callTimeout(90, TimeUnit.SECONDS)
            .connectTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()

        retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

    }


    @Synchronized
    fun getInstance(): RetrofitClient {
        if (!::mInstance.isInitialized)
            mInstance = RetrofitClient
        return mInstance
    }

    fun getApi(): ApiInterface {
        return retrofit!!.create(ApiInterface::class.java)
    }

}