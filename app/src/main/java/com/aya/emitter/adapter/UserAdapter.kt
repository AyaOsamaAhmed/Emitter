package com.aya.emitter.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.aya.emitter.R
import com.aya.emitter.model.UserModel


class UserAdapter : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    var userModel: ArrayList<UserModel> = ArrayList()
    var context : Context? = null

    fun setUser(userModel: ArrayList<UserModel> , context : Context) {
        this.context = context
        this.userModel = userModel
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return  userModel.size
    }

    override fun onBindViewHolder(holder: UserAdapter.MyViewHolder, position: Int) {
        holder.user_name.text = userModel[position].name
        holder.user_email.text = userModel[position].email
        holder.user_phone.text = userModel[position].phone
        holder.user_company.text = userModel[position].company.name
        holder.user_city.text = userModel[position].address.city

        holder.itemView.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(context!!)
            alertDialogBuilder.setTitle(R.string.dialog_title)
            alertDialogBuilder.setMessage(R.string.dialog_message)
            alertDialogBuilder.setPositiveButton(R.string.yes){ dialogInterface, i ->

                Intent().also { intent ->
                    intent.setAction("com.aya.emitter")
                    intent.putExtra("data", "Nothing to see here, move along.")
                    context!!.sendBroadcast(intent)
                }

            }
            alertDialogBuilder.setNegativeButton(R.string.no){dialogInterface, i ->
                dialogInterface.dismiss()
            }
            alertDialogBuilder.show()

            userModel[position]
        }

    }

   inner class MyViewHolder(item: View) : ViewHolder(item) {
        var user_name: TextView
        var user_email: TextView
       var user_phone: TextView
       var user_company: TextView
       var user_city: TextView
        init {
            user_name = item.findViewById(R.id.user_name)
            user_email = item.findViewById(R.id.user_email)
            user_phone = item.findViewById(R.id.user_phone)
            user_company = item.findViewById(R.id.user_company)
            user_city = item.findViewById(R.id.user_city)
        }
    }
}