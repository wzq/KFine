package com.firstlink.duo.activities

import android.os.Bundle
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
    }
}