package com.firstlink.duo.adapters

import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.firstlink.duo.R
import com.firstlink.duo.activities.DetailActivity
import com.firstlink.duo.model.Goods
import com.firstlink.duo.util.IntentFor
import com.firstlink.duo.util.formatPrice
import com.firstlink.duo.util.handleByCDN
import com.squareup.picasso.Picasso

/**
 * Created by wzq on 15/12/10.
 */

val TYPE_NORMAL = 0

class HomeAdapter(context : Context, data: List<Any>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val data = data
    val context = context

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val item = data?.get(position)
        when (holder) {
            is NormalViewHolder -> {
                if(item is Goods){
                    Picasso.with(context).load(handleByCDN(context, item.indexPic, 200, 200)).into(holder.picture)
                    holder.title.text = item.title
                    holder.content.text = item.description.replace("\\r|\\n", "")
                    holder.price.text = formatPrice(item.price.toFloat())
                    holder.priceX.text = formatPrice(item.refer_price.toFloat())
                    holder.source.text = item.source
                }
                holder.itemView.setOnClickListener({
                    context.startActivity(IntentFor<DetailActivity>(context))
//                    val options = ActivityOptionsCompat.makeScaleUpAnimation(holder.picture, holder.picture.width /2, holder.picture.height /2, 0, 0);
//                    ActivityCompat.startActivity(context as BaseActivity, IntentFor<DetailActivity>(context).putExtra("goods_id", 0).putExtra("owner_id", 0), options.toBundle());
                })
            }

        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        return NormalViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_home, parent, false));
    }

    class NormalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val picture : ImageView
        val title : TextView
        val content : TextView
        val price : TextView
        val priceX : TextView
        val source : TextView
        init {
            picture = itemView.findViewById(R.id.home_pic) as ImageView
            title = itemView.findViewById(R.id.home_title) as TextView
            content = itemView.findViewById(R.id.home_content) as TextView
            price = itemView.findViewById(R.id.home_price) as TextView
            priceX = itemView.findViewById(R.id.home_price_x) as TextView
            priceX.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            priceX.paint.isAntiAlias = true
            source = itemView.findViewById(R.id.home_source) as TextView
        }
    }

    override fun getItemViewType(position: Int): Int {
        return TYPE_NORMAL
    }

}