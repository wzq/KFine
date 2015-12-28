package com.firstlink.duo.activities

import android.os.Bundle
import android.view.View
import com.firstlink.duo.R

/**
 * Created by wzq on 15/12/26.
 */
class LoginActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Sign In"
        initRootView(R.layout.activity_login)
        supportActionBar.setDisplayHomeAsUpEnabled(true)

        baseProgress.visibility = View.GONE
    }
}