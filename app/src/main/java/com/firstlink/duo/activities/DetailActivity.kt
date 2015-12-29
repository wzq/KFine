package com.firstlink.duo.activities

import android.os.Build
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.view.MenuItem
import android.widget.ImageView
import com.firstlink.duo.R
import com.firstlink.duo.model.vo.DetailData
import com.firstlink.duo.util.network.UrlSet
import com.google.gson.Gson
import org.json.JSONObject

/**
 * Created by wzq on 15/12/22.
 */
class DetailActivity : BaseActivity() {


    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRootView(R.layout.activity_detail)
        title = "Detail"
        supportActionBar.setDisplayHomeAsUpEnabled(true)
        val img = findViewById(R.id.test_img) as ImageView
        ViewCompat.setTransitionName(img, "image")

//        OkHelper(updater).asyncPost(this, UrlSet.FIND_GOODS_DETAIL, "{id:${intent.getIntExtra("id", 0)},user_id:${intent.getIntExtra("uid", 0)}}")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            finishAfterTransition()
        else
            finish()
        return super.onOptionsItemSelected(item)
    }

    val updater = fun (hostSet : UrlSet, response : String?) : Unit{
        val result = Gson().fromJson(JSONObject(response).getJSONObject("data").toString(), DetailData::class.java)
    }
}