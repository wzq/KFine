package com.firstlink.duo.activities

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import com.firstlink.duo.R
import com.firstlink.duo.fragments.TopicFragment

/**
 * Created by wzq on 16/1/6.
 */
class TopicActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRootView(R.layout.activity_topic)
        supportFragmentManager.beginTransaction().replace(R.id.topic_container, TopicFragment()).commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            finishAfterTransition()
        else
            finish()
        return super.onOptionsItemSelected(item)
    }
}