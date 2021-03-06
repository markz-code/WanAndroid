package com.zpl.wanandroid

import android.animation.ValueAnimator
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.zpl.wanandroid.base.BaseViewModel


/**
 * des 关于播放viewModel,播放、播放列表、首页悬浮共用
 * @author zs
 * @date 2020/6/28
 */
class PlayViewModel : BaseViewModel() {
    /**
     * 歌名
     */
    val songName = ObservableField<String>().apply { set("暂无播放") }

    /**
     * 歌手
     */
    val singer = ObservableField<String>().apply { set("") }

    /**
     * 专辑图片
     */
    val albumPic = ObservableField<Long>()

    /**
     * 播放状态
     */
    val playStatus = ObservableField<Int>()

    /**
     * 图片播放模式
     */
    val playModePic = ObservableField<Int>().apply {
        set(R.mipmap.play_order)
    }

    /**
     * 文字播放模式
     */
    val playModeText = ObservableField<String>()

    /**
     * 总播放时长-文本
     */
    val maxDuration = ObservableField<String>().apply {
        set("00:00")
    }

    /**
     * 当前播放时长-文本
     */
    val currentDuration = ObservableField<String>().apply {
        set("00:00")
    }

    /**
     * 总长度
     */
    val maxProgress = ObservableField<Int>()

    /**
     * 播放进度
     */
    val playProgress = ObservableField<Int>()

}