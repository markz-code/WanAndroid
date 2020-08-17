package com.zpl.wanandroid

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.BallPulseFooter

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        baseApplication = this
        MultiDex.install(this)

        initSmartHead()
    }

    private fun initSmartHead() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context: Context, _: RefreshLayout ->
            MaterialHeader(context)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context: Context, _: RefreshLayout ->
            BallPulseFooter(context)
        }
    }

    companion object {
        private lateinit var baseApplication: Application

        fun getContext(): Context {
            return baseApplication
        }
    }
}