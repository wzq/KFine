package com.firstlink.duo.activities

import android.app.Activity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.amulyakhare.textdrawable.TextDrawable
import com.firstlink.duo.R
import com.firstlink.duo.util.IntentFor
import com.firstlink.duo.util.runDelayedOnUiThread
import com.firstlink.duo.widget.Explosion.ExplosionField

/**
 * Created by wzq on 15/12/22.
 */
class LaunchActivity : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        val logo = findViewById(R.id.launch_logo) as ImageView

        val mDrawableBuilder = TextDrawable.builder().beginConfig().withBorder(0).endConfig().round();

        logo.setImageDrawable(mDrawableBuilder.build("D", ContextCompat.getColor(this, R.color.accent)))

        runDelayedOnUiThread(200, {
            val mExplosionField = ExplosionField.attach2Window(this@LaunchActivity, {
                startActivity(IntentFor<MainActivity>(this@LaunchActivity))
                finish()
            });
            mExplosionField.explode(logo, 2000)
        })
    }
}