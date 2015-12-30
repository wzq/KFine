package com.firstlink.duo.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import com.firstlink.duo.R
import com.firstlink.duo.model.vo.LoginResult
import com.firstlink.duo.util.Tools
import com.firstlink.duo.util.network.OkHelper
import com.firstlink.duo.util.network.UrlSet

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

        val phone = findViewById(R.id.login_phone) as EditText

        val password = findViewById(R.id.login_password) as EditText

        findViewById(R.id.login_submit).setOnClickListener({
            val params = hashMapOf<String, String>()
            params.put("mobile", phone.text.toString())
            params.put("password", Tools.getEncrypy(password.text.toString().trim())!!)
            params.put("d_id", OkHelper.getDeviceId(this@LoginActivity))
            params.put("p", "a")
            params.put("en_m", "RSA")
            OkHelper(this).asyncPost(UrlSet.LOGIN, params, LoginResult::class.java, updater)
        })


    }


    val updater = fun (user: LoginResult?, urlSet : UrlSet, resultCode: Int, msg: String) : Unit{
        println(user?.mark)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}