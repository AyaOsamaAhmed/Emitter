package com.aya.emitter.responsitory

import com.aya.emitter.`interface`.ApiInterface
import com.aya.emitter.model.UserModel
import retrofit2.Call


class GeneralDataRepository (
    private val api: ApiInterface
) {

      fun getHomeData(): Call<ArrayList<UserModel>> {
        return api.HomeData()
    }
}