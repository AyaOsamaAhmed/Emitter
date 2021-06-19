package com.aya.emitter.BroadCast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class MyBroadCastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent!!.action.equals("")) {
            val receiveData : String = intent.getStringExtra("data").toString()
            Toast.makeText(context,"Emitter."+ receiveData, Toast.LENGTH_SHORT).show()

            val noConnectivity: Boolean = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
            if (noConnectivity) {
            //    Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show()
            } else {
              //  Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show()
            }
        }
    }

}