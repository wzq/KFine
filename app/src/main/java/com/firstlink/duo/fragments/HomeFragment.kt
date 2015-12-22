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
import com.firstlink.duo.adapters.TYPE_NORMAL
import com.firstlink.duo.util.VerticalItemDecoration
import kotlin.properties.Delegates

/**
 * Created by wzq on 15/12/22.
 */
class HomeFragment : Fragment() {

    var recycler : RecyclerView by Delegates.notNull()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater?.inflate(R.layout.fragment_home, container, false)
        recycler = root?.findViewById(R.id.home_recycler) as RecyclerView
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.addItemDecoration(VerticalItemDecoration.Builder(activity).first(R.drawable.div_home_list).type(TYPE_NORMAL, R.drawable.div_home_list).last(R.drawable.div_home_list).create())
        recycler.adapter = HomeAdapter(arrayListOf(1,2,3,4,5,6,7))
        return root
    }
}