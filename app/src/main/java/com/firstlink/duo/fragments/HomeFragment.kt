package com.firstlink.duo.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firstlink.duo.R
import com.firstlink.duo.adapters.HomeAdapter
import com.firstlink.duo.util.*
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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater?.inflate(R.layout.fragment_home, container, false)
        recycler = root?.findViewById(R.id.home_recycler) as RecyclerView
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.addItemDecoration(VerticalItemDecoration.Builder(activity).first(R.drawable.div_home_list).type(HomeAdapter.TYPE_NORMAL, R.drawable.div_home_list).last(R.drawable.div_home_list).create())
        OkHelper(updater).asyncPost(HostSet.FIND_HOME_DATA, getCommonParams(activity).add(POST_JSON, "{start_row:0,page_size:20}").build())
        return root
    }

    val updater = fun (response : String?) : Unit{
        val result = Gson().fromJson(JSONObject(response).getJSONObject("data").toString(), HomeListData::class.java)
        adapter = HomeAdapter(activity, result.list)
        recycler.adapter = adapter
    }
}