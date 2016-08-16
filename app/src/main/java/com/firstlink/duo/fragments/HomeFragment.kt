package com.firstlink.duo.fragments

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firstlink.duo.R
import com.firstlink.duo.adapters.HomeAdapter
import com.firstlink.duo.model.Goods
import com.firstlink.duo.model.vo.HomeListData
import com.firstlink.duo.network.RequestManager
import com.firstlink.duo.util.Extensions.buildParams
import com.firstlink.duo.widget.decorations.VerticalItemDecoration
import com.google.gson.Gson
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import kotlin.properties.Delegates

/**
 * Created by wzq on 15/12/22.
 */
class HomeFragment : Fragment() {

    var recycler: RecyclerView by Delegates.notNull()

    val data = arrayListOf<Any>()

    lateinit var adapter :HomeAdapter

    var startRow = 0
    var pageSize = 20

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater?.inflate(R.layout.fragment_home, container, false) as SwipeRefreshLayout
        root.setColorSchemeResources(R.color.accent)
        root.setOnRefreshListener { Handler().postDelayed({ root.isRefreshing = false }, 1000) }

        recycler = root.findViewById(R.id.home_recycler) as RecyclerView
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.addItemDecoration(VerticalItemDecoration.Builder(activity)
                .type(HomeAdapter.TYPE_NORMAL, R.drawable.div_home_list)
                .type(HomeAdapter.TYPE_TOPIC, R.drawable.div_home_list)
                .type(HomeAdapter.TYPE_TAG, R.drawable.div_home_list)
                .last(R.drawable.div_home_list).create())
        adapter = HomeAdapter(activity, data)
        recycler.adapter = adapter

        with(RequestManager.productApi) {
            Observable.zip(
                    getNations(RequestManager.requestMap),
                    getProductList(buildParams("product_json", hashMapOf("start_row" to startRow, "page_size" to pageSize))),
                    { a, b ->
                        val url = Gson().fromJson(a.getAsJsonObject("data"), HomeListData::class.java).list[0].targetUrl
                        with(Gson().fromJson(b.getAsJsonObject("data"), HomeListData::class.java)){
                            topicList?.map { goods -> goods.displayType = HomeAdapter.TYPE_TOPIC }
                            list?.map { goods -> goods.displayType = HomeAdapter.TYPE_NORMAL }
                            data.add(Goods().apply { displayType = HomeAdapter.TYPE_NATION; targetUrl = url })
                            data.add(Goods().apply { title = "专题活动"; displayType = HomeAdapter.TYPE_TAG })
                            data.addAll(if (topicList.size > 3) topicList.subList(0, 3) else topicList)
                            data.add(Goods().apply { title = "精品推荐"; displayType = HomeAdapter.TYPE_TAG })
                            data.addAll(list)
                        }
                        data
                    }
            ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ it ->
                adapter.notifyDataSetChanged()
            })
        }
        return root
    }
}