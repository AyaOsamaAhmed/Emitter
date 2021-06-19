package com.aya.emitter.`interface`

import com.aya.emitter.model.UserModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("users")
     fun HomeData(): Call<ArrayList<UserModel>>

}