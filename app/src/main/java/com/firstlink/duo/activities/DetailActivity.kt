package com.firstlink.duo.activities

import android.os.Build
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.view.MenuItem
import android.widget.ImageView
import com.firstlink.duo.R
import com.squareup.picasso.Picasso

/**
 * Created by wzq on 15/12/22.
 */
class DetailActivity : BaseActivity() {
    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRootView(R.layout.activity_detail)
        title = "Detail"
        
        val img = findViewById(R.id.test_img) as ImageView
        ViewCompat.setTransitionName(img, "image")
        Picasso.with(this).load(intent.getStringExtra("image")).into(img)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            finishAfterTransition()
        else
            finish()
        return super.onOptionsItemSelected(item)
    }
}