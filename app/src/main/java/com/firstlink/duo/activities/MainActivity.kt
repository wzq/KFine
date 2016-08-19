package com.firstlink.duo.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.bumptech.glide.Glide
import com.firstlink.duo.R
import com.firstlink.duo.fragments.HomeFragment
import com.firstlink.duo.util.PreferenceTools
import com.firstlink.duo.util.Tools
import com.lapism.searchview.SearchHistoryTable
import com.lapism.searchview.SearchView
import com.lapism.searchview.SearchAdapter
import com.lapism.searchview.SearchItem
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.properties.Delegates

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    var userPicture by Delegates.notNull<CircleImageView>()
    var userName by Delegates.notNull<TextView>()

    lateinit var searchView: SearchView
    val searchHistory = SearchHistoryTable(this)

    val historyList = arrayListOf<SearchItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchView = findViewById(R.id.search_view) as SearchView
        searchView.setOnQueryTextListener(searchListener)
        val searchAdapter = SearchAdapter(this, historyList)
        searchAdapter.setOnItemClickListener { view, position ->
            searchView.close(false)
            searchHistory.addItem(SearchItem((view.findViewById(R.id.textView_item_text) as TextView).text))
            println("p-$position")
        }
        searchView.setAdapter(searchAdapter)


        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
        
        userPicture = navigationView.getHeaderView(0).findViewById(R.id.user_head) as CircleImageView
        userName = navigationView.getHeaderView(0).findViewById(R.id.user_name) as TextView
        val user =  PreferenceTools.getUser()
        if(user == null) {
            navigationView.getHeaderView(0).setOnClickListener({ startActivity(Intent(this@MainActivity, LoginActivity::class.java)) })
        }else {
            Glide.with(this@MainActivity).load(user.headUrl).into(userPicture)
            userName.text = user.nickName
        }

        supportFragmentManager.beginTransaction().replace(R.id.main_container, HomeFragment()).commit()

        val filter = IntentFilter()
        filter.addAction(Tools.LOGIN_OK)
        registerReceiver(receiver, filter)
    }

    val searchListener = object : SearchView.OnQueryTextListener{
        override fun onQueryTextChange(newText: String?): Boolean { return false }

        override fun onQueryTextSubmit(query: String?): Boolean {
            searchView.close(false)
            searchHistory.addItem(SearchItem(query))
            println(query)
            return false
        }

    }


    val receiver = object: BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            val user =  PreferenceTools.getUser()
            if(user!=null){
                runOnUiThread({
                    Glide.with(this@MainActivity).load(user.headUrl).into(userPicture)
                    userName.text = user.nickName
                })
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.action_search ->{
                historyList.clear()
                historyList.addAll(searchHistory.allItems)
                searchView.open(true)
            }
            R.id.action_message -> { println("message") }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
