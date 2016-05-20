package com.firstlink.duo.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.firstlink.duo.R
import com.firstlink.duo.activities.DetailActivity
import com.firstlink.duo.model.Order
import com.firstlink.duo.model.Topic
import com.firstlink.duo.util.Tools
import com.squareup.picasso.Picasso

/**
 * Created by wzq on 16/1/8.
 */
class TopicAdapter(val context: Context, val data: List<*>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    companion object {
        val TYPE_NORMAL = 0
        val TYPE_HEAD = 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val item = data?.get(position)!!
        when (holder) {
            is NormalViewHolder -> {
                if(item is Order){
                    Picasso.with(context).load(item.firstPic).into(holder.picture)
                    holder.title.text = item.title
                    holder.price.text = Tools.formatPrice(item.price.toFloat())
                    holder.source.text = item.source
                    holder.ripple.setOnClickListener({
                        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity?, holder.picture, "image");
                        ActivityCompat.startActivity(context, Intent(context, DetailActivity::class.java)
                                .putExtra("id", item.id).putExtra("uid", item.userId)
                                .putExtra("image_url", item.firstPic), options.toBundle());
                    })
                }
            }
            is HeadViewHolder -> {
                if(item is Topic){
                    Picasso.with(context).load(Tools.cdn1(item.picUrl, holder.w, holder.h)).into(holder.picture)
                    holder.title.text = item.desc
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        when (viewType) {
            TYPE_NORMAL -> return NormalViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_topic, parent, false))
            TYPE_HEAD -> return HeadViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_topic_head, parent, false))
            else -> return null
        }
    }

    override fun getItemViewType(position: Int): Int {
        when (data?.get(position)){
            is Order -> return TYPE_NORMAL
            is Topic -> return TYPE_HEAD
            else -> return -1
        }
    }

    inner class NormalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val picture = itemView.findViewById(R.id.topic_pic) as ImageView
        val title = itemView.findViewById(R.id.topic_title) as TextView
        val price = itemView.findViewById(R.id.topic_price) as TextView
        val source = itemView.findViewById(R.id.topic_source) as TextView
        val ripple = itemView.findViewById(R.id.ripple)
        val w: Int; val h: Int;
        init {
            val p = picture.layoutParams
            p.width = (context.resources.displayMetrics.widthPixels - Tools.dp2px(context, 24))/2
            p.height = p.width
            picture.layoutParams = p
            w = p.width
            h = p.height
        }
    }


    inner class HeadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val picture = itemView.findViewById(R.id.topic_pic) as ImageView
        val title = itemView.findViewById(R.id.topic_title) as TextView
        val w: Int; val h : Int
        init {
            val p = picture.layoutParams
            p.width = (context.resources.displayMetrics.widthPixels - Tools.dp2px(context, 16))
            p.height = p.width * 34 / 70
            picture.layoutParams = p
            w = p.width
            h = p.height
        }
    }
}