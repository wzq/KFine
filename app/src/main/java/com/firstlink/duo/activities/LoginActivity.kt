package com.firstlink.duo.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import com.firstlink.duo.R

/**
 * Created by wzq on 15/12/26.
 */
class LoginActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Sign In"
        initRootView(R.layout.activity_login)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        baseProgress.visibility = View.GONE

        val phone = findViewById(R.id.login_phone) as EditText

        val password = findViewById(R.id.login_password) as EditText

        findViewById(R.id.login_submit)?.setOnClickListener({
            //do login
        })


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}