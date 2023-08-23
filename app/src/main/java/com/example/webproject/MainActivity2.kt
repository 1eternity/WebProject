package com.example.webproject

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.webproject.adapter.GameAdapter
import com.example.webproject.databinding.ActivityMainBinding
import com.example.webproject.entity.GameBean
import com.example.webproject.web.WebActivity

class MainActivity2 : AppCompatActivity(), View.OnClickListener {
    private val mV by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var mAdapter: GameAdapter?=null
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        setContentView(mV.root)
        mAdapter = GameAdapter()
        mV.recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        mV.recycleView.adapter = mAdapter
        mAdapter?.data = getData()

        initClick()
    }

    private fun getData(): MutableList<GameBean> {
       val list:MutableList<GameBean> = mutableListOf()
        list.apply {
            add(GameBean("汉字找茬王","https://idl62uattc.feishu.cn/docx/doxcnPO8TjkrLhmXItifbaYZa7e","https://docs.qq.com/doc/DZkpKbnN4UnF2bndP","https://mini.ssflashgo.com/stonesh/wll/wzyx/WEB6/web_0812_2200/index.html",R.drawable.game_five))
            add(GameBean("隐秘的档案","https://g9nrxp26rc.feishu.cn/docx/KmASdd95roE6HNxxLpUcMcBInKc","https://g9nrxp26rc.feishu.cn/docx/KmASdd95roE6HNxxLpUcMcBInKc","https://file.ym.cdn.kidikidi.net/secretfiles/online/web_after/web-mobile/index.html",R.drawable.game_six))
            add(GameBean("疯狂梗传","https://wd8jamga21.feishu.cn/docx/HDaJdR5WCoJHvDxtl8YcP1s8n0d?from=from_copylink","https://docs.qq.com/doc/DZmpGQURrY1lKdmZZ","https://fkwg.xyx.mmhygame.com/fkwg/web/index.html",R.drawable.game_seven))
            add(GameBean("文字来找茬","https://short.puzzles100.com/sl/pQVCHD","https://short.puzzles100.com/sl/pQVCHD","https://short.puzzles100.com/sl/Gbjiu8",R.drawable.game_two))
            add(GameBean("一代沙雕","https://ha7atrsdg1.feishu.cn/docx/EnwuddoTpoqXBrxr1YPcD4x2nio","https://short.puzzles100.com/sl/pQVCHD","https://chuangling.cdn.xiaojing.work/byte/h5/web-mobile/index.html",R.drawable.game_three))
            add(GameBean("就挺秃然的/沙雕驾到","https://lwadfgz361.feishu.cn/docx/TCYGdsu0FoYsedxeiPMcNHo1nzf","https://lwadfgz361.feishu.cn/docx/TCYGdsu0FoYsedxeiPMcNHo1nzf","https://jturdcdn.volcano.chuanglinggame.com/downRes/byte/web/web-mobile/",R.drawable.game_four))
            add(GameBean("爆梗找茬王","https://wcwx641mg9.feishu.cn/docx/TPJUdvr7roxSYwxuriXcrIa7nDG","","https://static.zuiqiangyingyu.net/wb_webview/myDog/findFaultKing/web-mobile/index.html",R.drawable.game_one))
            add(GameBean("全民剧本大师","https://tjg64fq1kd.feishu.cn/docx/TY4MdEkiIo9qjYxxTCHcD7XEndg?from=from_copylink","","https://scriptmaster-1300577131.cos.ap-nanjing.myqcloud.com/web/web-mobile/index.html",R.drawable.game_eight))
            add(GameBean("文字真相","https://jjqoz35dc2.feishu.cn/docx/OPd9dUha0oRT1ExTDTAcg3nKntc","","https://z10.tanyu.mobi/tanpaixyx/29000/web_mobile/1.0.0/index.html",R.drawable.game_nine))
        }
        return list
    }


    private fun initClick() {
        mAdapter?.setOnItemClickListener(object :OnItemClickListener{
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

            }
        })
        mAdapter?.setOnItemChildClickListener(object :OnItemChildClickListener{
            override fun onItemChildClick(
                adapter: BaseQuickAdapter<*, *>,
                view: View,
                position: Int
            ) {
                when(view.id){
                    R.id.kuaiShouImg ->{
                        val documentUrl = mAdapter?.data?.get(position)?.kuaiShouUrl
                        documentUrl?.let { WebActivity.start(this@MainActivity2, it) }
                    }
                    R.id.douyinImg ->{
                        val documentUrl = mAdapter?.data?.get(position)?.documentUrl
                        documentUrl?.let { WebActivity.start(this@MainActivity2, it) }
                    }
                    R.id.gameTv ->{
                        val gameUrl = mAdapter?.data?.get(position)?.gameUrl
                        gameUrl?.let { WebActivity.start(this@MainActivity2, it) }
                    }
                }
            }
        })
    }


    /**
     * 设置监听
     */
    protected fun addClick(vararg vs: View?) {
        for (v in vs) {
            v?.setOnClickListener(this)
        }
    }
    override fun onClick(v: View?) {

    }

}