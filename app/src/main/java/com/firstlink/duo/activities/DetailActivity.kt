package com.firstlink.duo.activities

import android.os.Build
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPager
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.firstlink.duo.R
import com.firstlink.duo.model.vo.DetailData
import com.firstlink.duo.util.network.Original
import com.firstlink.duo.util.network.UrlSet
import com.firstlink.duo.util.network.VolleyHelper
import com.squareup.picasso.Picasso

/**
 * Created by wzq on 15/12/22.
 */
class DetailActivity : BaseActivity() {


    var pager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRootView(R.layout.activity_detail)
        val color = ColorGenerator.MATERIAL.randomColor
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = color
        }
        baseToolBar.setBackgroundColor(color)
        title = "Item Detail"
        supportActionBar.setDisplayHomeAsUpEnabled(true)

        pager = findViewById(R.id.detail_pager) as ViewPager
        ViewCompat.setTransitionName(pager, "image")

        getData()
    }

    private fun getData() {
        val params = hashMapOf<String, String>()
        params.put("id", "${intent.getIntExtra("id", 0)}")
        params.put("user_id", "${intent.getIntExtra("uid", 0)}")
        VolleyHelper.call(this).addPost(UrlSet.FIND_GOODS_DETAIL, params, DetailData::class.java, updater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            finishAfterTransition()
        else
            finish()
        return super.onOptionsItemSelected(item)
    }

    private val updater = fun(data: DetailData?, urlSet: UrlSet, resul: Original): Unit {
        baseProgress.visibility = View.GONE

        val p = pager?.layoutParams!!
        p.width = resources.displayMetrics.widthPixels
        p.height = (p.width * 0.618).toInt()
        pager?.layoutParams = p

        val images = arrayListOf<ImageView>()
        for (item in data!!.post.itemPics) {
            val image = ImageView(this@DetailActivity)
            image.layoutParams = p
            image.scaleType = ImageView.ScaleType.FIT_CENTER
            Picasso.with(this).load(item.picUrl).into(image)// into ${@link Target} object can get bitmap
            images.add(image)
        }
        pager?.adapter = DetailAdapter(images)

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