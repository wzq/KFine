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
import com.firstlink.duo.util.network.Original
import com.firstlink.duo.util.network.UrlSet
import com.firstlink.duo.util.network.VolleyHelper
import com.firstlink.duo.widget.decorations.VerticalItemDecoration
import kotlin.properties.Delegates

/**
 * Created by wzq on 15/12/22.
 */
class HomeFragment : Fragment() {

    var recycler: RecyclerView by Delegates.notNull()

    var adapter: HomeAdapter by Delegates.notNull()

    var startRow = 0;
    var pageSize = 20

    var url = ""

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

        VolleyHelper.call().addPost(UrlSet.FIND_NATIONS, null, HomeListData::class.java, {
            obj: HomeListData?, urlSet: UrlSet, result: Original ->
            val params = hashMapOf(Pair("start_row", startRow), Pair("page_size", pageSize))
            url = obj!!.list[0].targetUrl
            VolleyHelper.call().addPost(UrlSet.FIND_HOME_DATA, params, HomeListData::class.java, updater)
        })

        return root
    }

    val updater = fun(obj: HomeListData?, urlSet: UrlSet, result: Original): Unit {
        when (urlSet) {
            UrlSet.FIND_HOME_DATA -> {
                if (obj != null) {
                    obj.topicList?.map { goods -> goods.displayType = HomeAdapter.TYPE_TOPIC }
                    obj.list?.map { goods -> goods.displayType = HomeAdapter.TYPE_NORMAL }
                    var data = arrayListOf<Any>()
                    data.add(fun(): Goods {
                        val temp = Goods(); temp.displayType = HomeAdapter.TYPE_NATION;temp.targetUrl = url; return temp
                    }())
                    data.add(fun(): Goods {
                        val temp = Goods(); temp.title = "专题活动"; temp.displayType = HomeAdapter.TYPE_TAG; return temp
                    }())
                    data.addAll(if (obj.topicList.size > 3) obj.topicList.subList(0, 3) else obj.topicList)

                    data.add(fun(): Goods {
                        val temp = Goods(); temp.title = "精品推荐"; temp.displayType = HomeAdapter.TYPE_TAG; return temp
                    }())
                    data.addAll(obj.list)
                    adapter = HomeAdapter(activity, data)
                    recycler.adapter = adapter
                }
            }
            else -> return Unit
        }
    }
}