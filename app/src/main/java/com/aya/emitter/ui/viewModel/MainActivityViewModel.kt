package com.aya.emitter.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aya.emitter.`interface`.ApiInterface
import com.aya.emitter.model.UserModel
import com.aya.emitter.network.RetrofitClient
import com.aya.emitter.responsitory.GeneralDataRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    lateinit var generalDataRepository: GeneralDataRepository

    lateinit var apiClient: ApiInterface
    private val TAG = "MainActivityViewModel"

    private val _userModel_list = MutableLiveData<ArrayList<UserModel>>()
    val userModel_list: LiveData<ArrayList<UserModel>> get() = _userModel_list


    fun Init(){
        //Init network layer
        apiClient = RetrofitClient.getInstance().getApi()
        generalDataRepository = GeneralDataRepository(apiClient)
    }


     fun getHomeData() {

         generalDataRepository.getHomeData().enqueue( object : Callback<ArrayList<UserModel>> {
             override fun onFailure(call: Call<ArrayList<UserModel>>, t: Throwable) {
                 Log.e("ApiException", "exception handled" + (t.toString()))
             }

             override fun onResponse(
                 call: Call<ArrayList<UserModel>>,
                 response: Response<ArrayList<UserModel>>
             ) {
                 Log.d(TAG, "onResponse: "+ response.body()?.size)
                 _userModel_list.value = response.body()

             }

         })

         }

}
