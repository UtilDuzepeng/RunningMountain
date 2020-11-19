package com.miaofen.xiaoying.activity

import android.content.Context
import android.content.Intent
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseActivity
import com.miaofen.xiaoying.fragment.search.HistoryFragment
import com.miaofen.xiaoying.fragment.search.ResultFragment
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() {

    private var curTabIndex = -1

    override fun returnLayoutId() = R.layout.activity_search

    override fun initView() {
        super.initView()
        //todo 现在输入框内容
//        InputLenLimit.lengthFilter(this,ed_search,10)
        showTab(0)
    }

    override fun initData() {
        super.initData()
        tv_search.setOnClickListener{
            hideKeyboard()
            showTab(1)
        }
        image_find.setOnClickListener { finish() }
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
            transaction.add(R.id.search_framelayout, targetFragment, genFragmentTag(newTabIndex))
        } else {
            transaction.show(targetFragment)
        }
        transaction.commit()
    }

    private fun genFragmentTag(index: Int) = FRAGMENT_TAG_PREFIX + index

    private fun createFragment(index: Int) = when (index) {
        0 -> HistoryFragment()
        1 -> ResultFragment()
        else -> HistoryFragment()
    }


    /**
     * 关闭软键盘ß
     */
    private fun hideKeyboard() {
        val imm: InputMethodManager =
            this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm.isActive() && this.currentFocus != null) {
            if (this.currentFocus.windowToken != null) {
                imm.hideSoftInputFromWindow(
                    this.currentFocus.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }
    }

    companion object {
        private const val FRAGMENT_TAG_PREFIX = "Fragment_"
        fun start(context: Context?) {
            val intent = Intent(context, SearchActivity::class.java)
            context?.startActivity(intent)
        }
    }

}