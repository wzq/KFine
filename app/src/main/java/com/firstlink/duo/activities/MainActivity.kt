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
import com.firstlink.duo.R
import com.firstlink.duo.fragments.HomeFragment
import com.firstlink.duo.util.PreferenceTools
import com.firstlink.duo.util.Tools
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.properties.Delegates

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    var userPicture by Delegates.notNull<CircleImageView>()
    var userName by Delegates.notNull<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
        
        userPicture = navigationView.getHeaderView(0).findViewById(R.id.user_head) as CircleImageView
        userName = navigationView.getHeaderView(0).findViewById(R.id.user_name) as TextView
        val user =  PreferenceTools.getUser(this@MainActivity)
        if(user == null) {
            navigationView.getHeaderView(0).setOnClickListener({ startActivity(Intent(this@MainActivity, LoginActivity::class.java)) })
        }else {
            Picasso.with(this@MainActivity).load(user.headUrl).into(userPicture)
            userName.text = user.nickName
        }

        supportFragmentManager.beginTransaction().replace(R.id.main_container, HomeFragment()).commit()

        val filter = IntentFilter()
        filter.addAction(Tools.LOGIN_OK)
        registerReceiver(receiver, filter);
    }


    val receiver = object: BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            val user =  PreferenceTools.getUser(this@MainActivity)
            if(user!=null){
                runOnUiThread({
                    Picasso.with(this@MainActivity).load(user.headUrl).into(userPicture)
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
        menuInflater.inflate(R.menu.main, menu);
        return true
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
