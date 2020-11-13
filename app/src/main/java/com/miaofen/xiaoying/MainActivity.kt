package com.miaofen.xiaoying

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.miaofen.xiaoying.base.BaseActivity
import com.miaofen.xiaoying.fragment.HomeFragment
import com.miaofen.xiaoying.fragment.NewsFragment
import com.miaofen.xiaoying.fragment.ReleaseFragment
import com.miaofen.xiaoying.fragment.UserFragment
import com.miaofen.xiaoying.utils.showToast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private var mExitTime: Long = 0 //延时事件

    var toast: Toast? = null //提示

    private var curTabIndex = -1

    private val rbIds =
        intArrayOf(R.id.rb_home, R.id.rb_release,  R.id.rb_news,  R.id.rb_mine)

    override fun returnLayoutId() = R.layout.activity_main

    override fun initFragment(savedInstanceState: Bundle?) {

        val index: Int = savedInstanceState?.getInt(STATE_CURRENT_TAB_INDEX) ?: DEFAULT_INDEX

        showTab(index)
        rg_root.check(rbIds[index])

        rg_root.setOnCheckedChangeListener { _, checkedId ->
            val pos = rbIds.indexOf(checkedId)
            showTab(pos)
        }
    }

    private fun showTab(index: Int) {
        if (index != curTabIndex) {
            changeFragment(index, curTabIndex)
            curTabIndex = index
        }
    }

    private fun changeFragment(newTabIndex: Int, oldTabIndex: Int) {
        val transaction = supportFragmentManager.beginTransaction()

        var currentFragment: Fragment? = null

        if (oldTabIndex >= 0) {
            currentFragment = supportFragmentManager.findFragmentByTag(genFragmentTag(oldTabIndex))
        }

        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }

        var targetFragment =
            supportFragmentManager.findFragmentByTag(genFragmentTag(newTabIndex))

        if (targetFragment == null) {
            targetFragment = createFragment(newTabIndex)
            transaction.add(R.id.fl_content, targetFragment, genFragmentTag(newTabIndex))
        } else {
            transaction.show(targetFragment)
        }

        transaction.commit()
    }

    private fun genFragmentTag(index: Int) = FRAGMENT_TAG_PREFIX + index

    private fun createFragment(index: Int) = when (index) {
        0 -> HomeFragment()
        1 -> ReleaseFragment()
        2 -> NewsFragment()
        3 -> UserFragment()
        else -> HomeFragment()
    }


    override fun onPause() {
        super.onPause()
        toast?.let { toast!!.cancel() }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mExitTime <= 3000) {
                finish()
                toast!!.cancel()
            } else {
                mExitTime = System.currentTimeMillis()
                toast = showToast("在按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }



    companion object {
        const val STATE_CURRENT_TAB_INDEX = "StateCurrentTabIndex"
        private const val FRAGMENT_TAG_PREFIX = "MainActivityFragment_"
        private const val DEFAULT_INDEX = 0

        fun startLogin(context: Context?) {
            val intent = Intent(context, MainActivity::class.java)
            context?.startActivity(intent)
        }
    }

}