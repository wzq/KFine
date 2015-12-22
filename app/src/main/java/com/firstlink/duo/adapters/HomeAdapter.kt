package com.firstlink.duo.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firstlink.duo.R

/**
 * Created by wzq on 15/12/10.
 */

val TYPE_NORMAL = 0

class HomeAdapter(data: List<Int>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val data = data


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is ViewHolder){
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }     

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {

        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_home, parent, false));
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
        }
    }

    override fun getItemViewType(position: Int): Int {
        return TYPE_NORMAL
    }

}