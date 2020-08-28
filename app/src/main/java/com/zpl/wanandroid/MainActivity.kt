package com.zpl.wanandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.zpl.wanandroid.base.BaseActivity
import com.zpl.wanandroid.common.stringForTime
import com.zpl.wanandroid.constants.Constants
import com.zpl.wanandroid.play.AudioBean
import com.zpl.wanandroid.play.AudioObserver
import com.zpl.wanandroid.play.PlayList
import com.zpl.wanandroid.play.PlayerManager
import com.zpl.wanandroid.ui.MainFragment
import com.zpl.wanandroid.utils.PrefUtils
import com.zpl.wanandroid.utils.StatusUtils

class MainActivity : BaseActivity(), AudioObserver {
    private var playVM: PlayViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        changeTheme()
        super.onCreate(savedInstanceState)
    }

    override fun initViewModel() {
        playVM = getActivityViewModel(PlayViewModel::class.java)
    }

    override fun init(savedInstanceState: Bundle?) {
        PlayerManager.instance.register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        PlayerManager.instance.unregister(this)
    }

    override fun getLayoutId() = R.layout.activity_main

    /**
     * 歌曲信息
     */
    override fun onAudioBean(audioBean: AudioBean) {
        playVM?.songName?.set(audioBean.name)
        playVM?.singer?.set(audioBean.singer)
        playVM?.maxDuration?.set(stringForTime(audioBean.duration))
        playVM?.maxProgress?.set(audioBean.duration)
        playVM?.albumPic?.set(audioBean.albumId)
    }

    /**
     * 播放状态-暂停/播放
     */
    override fun onPlayStatus(playStatus: Int) {
        playVM?.playStatus?.set(playStatus)
    }

    /**
     * 当前播放进度
     */
    override fun onProgress(currentDuration: Int, totalDuration: Int) {
        playVM?.currentDuration?.set(stringForTime(currentDuration))
        playVM?.playProgress?.set(currentDuration)
    }

    /**
     * 播放模式
     */
    override fun onPlayMode(playMode: Int) {
        when (playMode) {
            PlayList.PlayMode.ORDER_PLAY_MODE -> playVM?.playModePic?.set(R.mipmap.play_order)
            PlayList.PlayMode.SINGLE_PLAY_MODE -> playVM?.playModePic?.set(R.mipmap.play_single)
            PlayList.PlayMode.RANDOM_PLAY_MODE -> playVM?.playModePic?.set(R.mipmap.play_random)
        }
    }

    /**
     * 动态切换主题
     */
    private fun changeTheme() {
        val theme = PrefUtils.getBoolean(Constants.SP_THEME_KEY,false)
        if (theme) {
            setTheme(R.style.AppTheme_Night)
        } else {
            setTheme(R.style.AppTheme)
        }
    }

    /**
     * 沉浸式状态,随主题改变
     */
    override fun setSystemInvadeBlack() {
        val theme = PrefUtils.getBoolean(Constants.SP_THEME_KEY,false)
        if (theme) {
            StatusUtils.setSystemStatus(this, true, false)
        } else {
            StatusUtils.setSystemStatus(this, true, true)
        }
    }

    override fun onBackPressed() {
        //获取hostFragment
        val mMainNavFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.host_fragment)
        //获取当前所在的fragment
        val fragment =
            mMainNavFragment?.childFragmentManager?.primaryNavigationFragment
        //如果当前处于根fragment即HostFragment
        if (fragment is MainFragment) {
            //Activity退出但不销毁
            moveTaskToBack(false)
        }else{
            super.onBackPressed()
        }
    }
}
