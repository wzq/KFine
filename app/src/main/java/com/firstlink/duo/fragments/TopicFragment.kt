package com.firstlink.duo.fragments

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firstlink.duo.R
import com.firstlink.duo.adapters.TopicAdapter
import com.firstlink.duo.model.Topic
import com.firstlink.duo.model.vo.TopicData
import com.firstlink.duo.util.network.Original
import com.firstlink.duo.util.network.UrlSet
import com.firstlink.duo.util.network.VolleyHelper
import com.firstlink.duo.widget.decorations.GridItemDecoration
import kotlin.properties.Delegates

/**
 * Created by wzq on 16/1/6.
 */
class TopicFragment : Fragment(){

    var recycler: RecyclerView by Delegates.notNull()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater?.inflate(R.layout.fragment_topic, container, false) as SwipeRefreshLayout
        root.setColorSchemeResources(R.color.accent)
        root.setOnRefreshListener { Handler().postDelayed({ root.isRefreshing = false }, 1000) }
        val list = arrayListOf<Any?>();

        recycler = root.findViewById(R.id.topic_recycler) as RecyclerView
        val g = GridLayoutManager(activity, 2)
        recycler.layoutManager = g
        recycler.addItemDecoration(GridItemDecoration(8f))
        g.spanSizeLookup = object: GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                if(list[position] is Topic) return 2 else return 1
            }

        }

        val params = hashMapOf(Pair("start_row", 0), Pair("page_size", 20), Pair("id", activity.intent.getIntExtra("tid", 0)))
        VolleyHelper.call().addPost(UrlSet.FIND_TOPICS, params, TopicData::class.java, {
            obj: TopicData?, urlSet: UrlSet, original: Original ->
            activity.title = obj?.topic?.name
            list.addAll(obj?.list!!)
            recycler.adapter = TopicAdapter(activity, list)
        })
        return root
    }
}