package com.example.webproject.web

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.webproject.databinding.ActivityWebBinding
import java.util.Collections


class WebActivity : AppCompatActivity() {
    private var loadURL = "https://www.Degrii.com/"
    private var mTitle:String?=null
    companion object {
        const val EXTRA_STRING_TITLE = "intent_string_title"
            const val EXTRA_PARCELABLE = "intent_parce"
            const val EXTRA_DATA = "intent_data"
            const val EXTRA_STRING = "intent_string"
            const val EXTRA_INT = "intent_int"
            const val EXTRA_TYPE = "intent_type"
            const val EXTRA_BOOLEAN = "intent_boolean"

        fun start(activity: Activity) {
            val intent = Intent(activity, WebActivity::class.java)
            activity.startActivity(intent)
        }

        fun start(activity: Activity, url: String) {
            val intent = Intent(activity, WebActivity::class.java)
            intent.putExtra(EXTRA_STRING, url)
            activity.startActivity(intent)
        }
        fun start(activity: Activity, url: String,title:String) {
            val intent = Intent(activity, WebActivity::class.java)
            intent.putExtra(EXTRA_STRING, url)
            intent.putExtra(EXTRA_STRING_TITLE, title)
            activity.startActivity(intent)
        }
    }

    private var web: WebView? = null
    private val v by lazy { ActivityWebBinding.inflate(layoutInflater) }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        initView()
    }
     @RequiresApi(Build.VERSION_CODES.P)
     fun initView() {
         //         隐藏导航栏
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
             window.decorView.systemUiVisibility = (
                     View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                             or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                             or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                             or View.SYSTEM_UI_FLAG_FULLSCREEN
                             or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                     )
         }
         // 设置全屏显示并隐藏状态栏
         window.decorView.systemUiVisibility = (
                 View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                         or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                         or View.SYSTEM_UI_FLAG_FULLSCREEN
                 )
         window.decorView.systemUiVisibility = (
                 View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                         or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                         or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                         or View.SYSTEM_UI_FLAG_FULLSCREEN
                         or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                 )

         val params = window.attributes
         params.layoutInDisplayCutoutMode =
             WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
         window.attributes = params


         setContentView(v.root)
        web = WebView(this)
        v.viewLayout.addView(web)
         logic()
    }

     fun logic() {
        val url = intent.getStringExtra(EXTRA_STRING)
         mTitle = intent.getStringExtra(EXTRA_STRING_TITLE)
        if (!TextUtils.isEmpty(url)) {
            loadURL = url!!
        }
        web?.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                view.loadUrl(request.url.toString())
                return true
            }
        }
        web?.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress == 100) {
                }
            }

            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                if (!title.isNullOrEmpty()){
                }
            }
        }
       val webSettings: WebSettings? =  web?.settings
        // 保存表单数据
        webSettings?.saveFormData = true
        // 是否应该支持使用其屏幕缩放控件和手势缩放
        webSettings?.setSupportZoom(true)
        webSettings?.builtInZoomControls = true
        webSettings?.displayZoomControls = false
        // 启动应用缓存
        // 设置缓存模式
        webSettings?.cacheMode = WebSettings.LOAD_DEFAULT
        // setDefaultZoom  api19被弃用
        // 网页内容的宽度自适应屏幕
        webSettings?.loadWithOverviewMode = true
        webSettings?.useWideViewPort = true
        // 告诉WebView启用JavaScript执行。默认的是false。
        webSettings?.javaScriptEnabled = true
        //  页面加载好以后，再放开图片
        webSettings?.blockNetworkImage = false
        // 使用localStorage则必须打开
        webSettings?.domStorageEnabled = true
        // 排版适应屏幕
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webSettings?.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        } else {
            webSettings?.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
        }
        // WebView是否新窗口打开(加了后可能打不开网页)
//        webSettings?.setSupportMultipleWindows(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // WebView从5.0开始默认不允许混合模式,https中不能加载http资源,需要设置开启。
            webSettings?.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        web?.loadUrl(loadURL)
    }

}


