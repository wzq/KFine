package com.firstlink.duo.activities

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.firstlink.duo.R

/**
 * Created by wzq on 15/12/28.
 */
class WebActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRootView(R.layout.activity_web)

        supportActionBar.setDisplayHomeAsUpEnabled(true)
        val webView = findViewById(R.id.web_view) as WebView
        val progress = findViewById(R.id.web_progress) as ProgressBar

        progress.max = 100
        webView.settings.javaScriptEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true

        webView.setWebViewClient(object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return super.shouldOverrideUrlLoading(view, url)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        })

        webView.setWebChromeClient(object : WebChromeClient(){
            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, title)
                setTitle(title)
            }

            override fun onProgressChanged(view: WebView, newProgress: Int) {
                progress.progress = newProgress
                if (newProgress == 100) {
                    progress.visibility = View.GONE
                }
                super.onProgressChanged(view, newProgress)
            }
        })

        webView.loadUrl(intent.getStringExtra("web_url"))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            finishAfterTransition()
        else
            finish()
        return super.onOptionsItemSelected(item)
    }

}