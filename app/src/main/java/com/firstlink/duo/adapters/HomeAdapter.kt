package com.firstlink.duo.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.firstlink.duo.R
import com.firstlink.duo.activities.BaseActivity
import com.firstlink.duo.activities.DetailActivity
import com.firstlink.duo.model.Goods
import com.firstlink.duo.util.Tools
import com.squareup.picasso.Picasso

/**
 * Created by wzq on 15/12/10.
 */

class HomeAdapter(context: Context, data: List<Any>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val data = data
    val context = context as BaseActivity
    var lastPosition = -1

    companion object {
        val TYPE_NORMAL = 0
        val TYPE_TOPIC = 1
        val TYPE_NATION = 2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val item = data?.get(position)
        when (holder) {
            is NormalViewHolder -> {
                if (item is Goods) {
                    Picasso.with(context).load(Tools.handleByCDN(context, item.indexPic, 200, 200)).into(holder.picture)
                    holder.title.text = item.title
                    holder.content.text = item.description
                    holder.price.text = Tools.formatPrice(item.price.toFloat())
                    holder.priceX.text = Tools.formatPrice(item.refer_price.toFloat())
                    holder.source.text = item.source
                    holder.ripple.setOnClickListener({
                        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context, holder.picture, "image");
                        ActivityCompat.startActivity(context, Intent(context, DetailActivity::class.java).putExtra("id", item.id).putExtra("uid", item.user_id), options.toBundle());
                    })
                }

            }
            is TopicViewHolder -> {
                if (item is Goods) {
                    Picasso.with(context).load(Tools.handleByCDN(context, item.picUrl, 640, 260)).into(holder.picture)
                }
            }
            is NationViewHolder -> {

            }

        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        when (viewType) {
            TYPE_NORMAL -> return NormalViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_home, parent, false))
            TYPE_TOPIC -> return TopicViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_home_topic, parent, false))
            TYPE_NATION -> return NationViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_home_nation, parent, false))
            else -> return null
        }

    }

    override fun getItemViewType(position: Int): Int {
        val temp = data?.get(position);
        if (temp is Goods) return temp.displayType else return -1
    }


    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder?) {
        super.onViewDetachedFromWindow(holder)
        if (holder?.itemViewType != TYPE_NATION) {
            holder?.itemView?.clearAnimation()
        }
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder?) {
        super.onViewAttachedToWindow(holder)
        if (holder?.itemViewType != TYPE_NATION) {
            holder?.itemView!!.clearAnimation()
            if (holder?.layoutPosition!! > lastPosition) {
                val animBottom = AnimationUtils.loadAnimation(holder?.itemView!!.context, R.anim.bottom_in)
                holder?.itemView!!.startAnimation(animBottom)

            } else if (holder?.layoutPosition!! < lastPosition) {
                val animLeft = AnimationUtils.loadAnimation(holder?.itemView!!.context, R.anim.top_in)
                holder?.itemView!!.startAnimation(animLeft)
            }
            lastPosition = holder?.layoutPosition ?: -1
        }
    }

    inner class NormalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ripple = itemView.findViewById(R.id.ripple)
        val picture = itemView.findViewById(R.id.home_pic) as ImageView
        val title = itemView.findViewById(R.id.home_title) as TextView
        val content = itemView.findViewById(R.id.home_content) as TextView
        val price = itemView.findViewById(R.id.home_price) as TextView
        val priceX = itemView.findViewById(R.id.home_price_x) as TextView
        val source = itemView.findViewById(R.id.home_source) as TextView

        init {
            priceX.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            priceX.paint.isAntiAlias = true
        }
    }

    inner class TopicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val picture = itemView.findViewById(R.id.topic_pic) as ImageView

        init {
            val p = picture.layoutParams
            p.width = context.resources.displayMetrics.widthPixels - Tools.dp2px(context, 16)
            p.height = p.width * 26 / 64
            picture.layoutParams = p
        }
    }

    inner class NationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}