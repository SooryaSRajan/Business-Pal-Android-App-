package com.example.businesspal.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.businesspal.DisplaysDataActivity
import com.example.businesspal.R
import com.example.businesspal.model.BusinessDataModel
import kotlinx.android.synthetic.main.list_tile.view.*

class MainAdapter(var context: Context) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var mutableList = mutableListOf<BusinessDataModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setBusinessList(list: List<BusinessDataModel>) {
        mutableList = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_tile, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = mutableList[position]
        holder.title.text = data.BusinessName.trim()
        holder.subTitle.text = data.BusinessCaption.trim()
        holder.createdTime.text = data.CreatedTime.trim()
    }

    override fun getItemCount(): Int {
        return mutableList.size
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.businessTitle!!
        var subTitle = itemView.caption!!
        var createdTime = itemView.createdTime!!

        init {
            itemView.setOnClickListener {
                val intent = Intent(context, DisplaysDataActivity::class.java)
                intent.putExtra("DATA", mutableList[adapterPosition])
                context.startActivity(intent)
            }
        }
    }
}

