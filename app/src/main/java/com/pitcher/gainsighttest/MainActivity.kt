package com.pitcher.gainsighttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.gainsight.px.mobile.GainsightPX
import com.gainsight.px.mobile.LogLevel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val builder = GainsightPX.Builder(this, "")
            .pxHost(GainsightPX.PXHost.EU)
            .shouldTrackTapEvents(true)
            .logLevel(LogLevel.DEBUG);
        GainsightPX.setSingletonInstance(builder.build());
        GainsightPX.with().identify("Test_WebView_Id");

        setContentView(R.layout.activity_main)

        val webview = findViewById<WebView>(R.id.wv_gainsight)
        webview.settings.javaScriptEnabled = true
        webview.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                GainsightPX.attachToWebView(webview)
            }
        }
        webview.loadUrl("https://kinsta.com/blog/wordpress-vs-static-html/")
    }
}