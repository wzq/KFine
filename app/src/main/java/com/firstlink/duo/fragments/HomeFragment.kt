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

    var recycler : RecyclerView by Delegates.notNull()

    var adapter : HomeAdapter by Delegates.notNull()

    var startRow = 0; var pageSize = 20

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater?.inflate(R.layout.fragment_home, container, false) as SwipeRefreshLayout
        root.setColorSchemeResources(R.color.accent)
        root.setOnRefreshListener { Handler().postDelayed({root.isRefreshing = false}, 1000) }

        recycler = root.findViewById(R.id.home_recycler) as RecyclerView
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.addItemDecoration(VerticalItemDecoration.Builder(activity)
                .type(HomeAdapter.TYPE_NORMAL, R.drawable.div_home_list)
                .type(HomeAdapter.TYPE_TOPIC, R.drawable.div_home_list)
                .last(R.drawable.div_home_list).create())

        val params = hashMapOf(Pair("start_row", 0), Pair("page_size", 20))
        VolleyHelper.call(activity).addPost(UrlSet.FIND_HOME_DATA, params, HomeListData::class.java, updater)
        return root
    }
    val updater = fun (result: HomeListData?, urlSet : UrlSet, resul: Original) : Unit{
        when (urlSet) {
            UrlSet.FIND_HOME_DATA -> {
                result!!.topicList.map { goods -> goods.displayType = HomeAdapter.TYPE_TOPIC }
                result.list.map { goods -> goods.displayType = HomeAdapter.TYPE_NORMAL }
                var data = arrayListOf<Any>()
                data.add(fun (): Goods { val temp = Goods(); temp.displayType=HomeAdapter.TYPE_NATION; return temp }())
                data.addAll(result.topicList)
                data.addAll(result.list)
                adapter = HomeAdapter(activity, data)
                recycler.adapter = adapter
            }
            else -> return Unit
        }
    }
}