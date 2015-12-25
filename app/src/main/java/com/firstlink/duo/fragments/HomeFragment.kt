package com.firstlink.duo.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firstlink.duo.R
import com.firstlink.duo.adapters.HomeAdapter
import com.firstlink.duo.util.HostSet
import com.firstlink.duo.util.OkHelper
import com.firstlink.duo.util.VerticalItemDecoration
import com.firstlink.duo.util.runDelayed
import com.firstlink.duo.vo.HomeListData
import com.google.gson.Gson
import org.json.JSONObject
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
        root.setOnRefreshListener { runDelayed(1500, {root.isRefreshing = false}) }
        recycler = root.findViewById(R.id.home_recycler) as RecyclerView
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.addItemDecoration(VerticalItemDecoration.Builder(activity).first(R.drawable.div_home_list)
                .type(HomeAdapter.TYPE_NORMAL, R.drawable.div_home_list)
                .type(HomeAdapter.TYPE_TOPIC, R.drawable.div_home_list)
                .last(R.drawable.div_home_list).create())
        OkHelper(updater).asyncPost(activity, HostSet.FIND_HOME_DATA, "{start_row:$startRow,page_size:$pageSize}")

        return root
    }

    val updater = fun (hostSet : HostSet, response : String?) : Unit{
        var result = Gson().fromJson(JSONObject(response).getJSONObject("data").toString(), HomeListData::class.java)
        result.topicList.map { goods -> goods.displayType = HomeAdapter.TYPE_TOPIC }
        result.list.map { goods -> goods.displayType = HomeAdapter.TYPE_NORMAL }
        var data = arrayListOf<Any>()
        data.addAll(result.topicList)
        data.addAll(result.list)
        adapter = HomeAdapter(activity, data)
        recycler.adapter = adapter
    }
}