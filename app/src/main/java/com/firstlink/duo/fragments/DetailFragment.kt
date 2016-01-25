package com.firstlink.duo.fragments

import android.graphics.Paint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.firstlink.duo.R
import com.firstlink.duo.model.vo.DetailData
import com.firstlink.duo.util.Tools
import com.firstlink.duo.util.network.Original
import com.firstlink.duo.util.network.UrlSet
import com.firstlink.duo.util.network.VolleyHelper
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

/**
 * Created by wzq on 16/1/20.
 */
class DetailFragment : Fragment() {

    var root: View? = null

    var pictureParams: Pair<Int, Int>? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater?.inflate(R.layout.fragment_detail, container, false)

        val header = root?.findViewById(R.id.detail_t)
        val p = header?.layoutParams!!
        p.width = resources.displayMetrics.widthPixels
        p.height = (p.width * 0.75).toInt()
        pictureParams = Pair(p.width, p.height)
        header?.layoutParams = p
        ViewCompat.setTransitionName(root?.findViewById(R.id.detail_header), "image")

        getData()
        return root
    }

    private fun getData() {
        val params = hashMapOf<String, String>()
        params.put("id", "${activity.intent.getIntExtra("id", 0)}")
        params.put("user_id", "${activity.intent.getIntExtra("uid", 0)}")
        VolleyHelper.call().addPost(UrlSet.FIND_GOODS_DETAIL, params, DetailData::class.java, updater)
    }

    private val updater = fun(data: DetailData?, urlSet: UrlSet, original: Original): Unit {
        if (urlSet == UrlSet.FIND_GOODS_DETAIL && original.code == 1) {
            loadViews(data!!)
        }

    }

    private fun loadViews(data: DetailData) {
        val picture = (root?.findViewById(R.id.detail_header) as ImageView)
        Picasso.with(activity).load(Tools.cdn1(data.post?.firstPic, pictureParams?.first, pictureParams?.second)).into(picture, object: Callback.EmptyCallback(){
            override fun onSuccess() {
                picture.visibility = View.VISIBLE
            }
        })
        (root?.findViewById(R.id.detail_title) as TextView).text = data.post.name.trim()
        (root?.findViewById(R.id.detail_limit) as TextView).text = data.post.title.trim()
        (root?.findViewById(R.id.detail_price) as TextView).text = Tools.formatPrice(data.post.postExtData.estimatePrice.toFloat())
        (root?.findViewById(R.id.detail_source) as TextView).text = data.post.postExtData.source
        (root?.findViewById(R.id.detail_desc) as TextView).text = data.post.description

        val beforePrice = root?.findViewById(R.id.detail_price_before) as TextView;
        beforePrice.text = Tools.formatPrice(data.post.postExtData.referPrice.toFloat())
        beforePrice.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
        beforePrice.paint.isAntiAlias = true
    }


    inner class DetailAdapter(val itemViews: List<ImageView>) : PagerAdapter() {

        override fun getCount(): Int {
            return itemViews.size
        }

        override fun isViewFromObject(arg0: View, arg1: Any): Boolean {
            return arg0 === arg1
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(itemViews[position])
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            container.addView(itemViews[position], 0)
            return itemViews[position]
        }
    }
}