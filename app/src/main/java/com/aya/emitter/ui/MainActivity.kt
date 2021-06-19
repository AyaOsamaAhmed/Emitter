package com.aya.emitter.ui

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aya.emitter.BroadCast.MyBroadCastReceiver
import com.aya.emitter.R
import com.aya.emitter.adapter.UserAdapter
import com.aya.emitter.databinding.ActivityMainBinding
import com.aya.emitter.model.UserModel
import com.aya.emitter.ui.viewModel.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    var viewModel: MainActivityViewModel? = null
    var userAdapter : UserAdapter? = null
    var list_userModel : ArrayList<UserModel>? = null
    val broadCastReceiver:MyBroadCastReceiver = MyBroadCastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this,  R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel!!.Init()

        val manager = LinearLayoutManager(this)
        activityMainBinding.recyclerView.layoutManager = manager
        userAdapter = UserAdapter()
        activityMainBinding.recyclerView.adapter = userAdapter
        viewModel!!.getHomeData()

        viewModel!!.userModel_list.observe(this, object : Observer<ArrayList<UserModel>> {
            override fun onChanged(userModel: ArrayList<UserModel>) {
                list_userModel = userModel
                userAdapter!!.setUser(list_userModel!!, this@MainActivity)
                userAdapter!!.notifyDataSetChanged()
            }

        } )

        activityMainBinding.swipRecycler.setOnRefreshListener {
            viewModel!!.getHomeData()
            activityMainBinding.swipRecycler.isRefreshing = false
        }

        val intentFilter  = IntentFilter("")
        registerReceiver(broadCastReceiver,intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadCastReceiver)
    }

}