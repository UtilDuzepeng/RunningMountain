package com.miaofen.xiaoying.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseActivity
import com.miaofen.xiaoying.fragment.home.search.HistoryFragment
import com.miaofen.xiaoying.fragment.home.search.ObserverManager
import com.miaofen.xiaoying.fragment.home.search.ResultFragment
import kotlinx.android.synthetic.main.activity_search.*
import java.io.Serializable;

class SearchActivity : BaseActivity() {

    private var curTabIndex = -1

    private var type: Boolean = false

    private var arrayList: ArrayList<String>? = null

    override fun returnLayoutId() = R.layout.activity_search

    override fun initView() {
        super.initView()
        arrayList = intent.extras.getSerializable(LIST) as ArrayList<String>
        if (arrayList != null && arrayList!!.size > 0){
            ed_search.hint = arrayList!![intent.getIntExtra(MARKEY, -1)]
        }
        //todo 现在输入框内容
        showTab(0)
    }

    override fun initData() {
        super.initData()
        tv_search.setOnClickListener {
            hideKeyboard()
            if (ed_search.text.toString().isNotEmpty()) {
                ObserverManager.getInstance().notifyObserver(ed_search.text.toString());
            }
            showTab(1)
            type = true
        }

        image_search_find.setOnClickListener {
            hideKeyboard()
            if (type) {
                showTab(0)
                type = false
            } else {
                finish()
            }
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
            transaction.add(R.id.search_framelayout, targetFragment, genFragmentTag(newTabIndex))
        } else {
            transaction.show(targetFragment)
        }
        transaction.commit()
    }

    private fun genFragmentTag(index: Int) = FRAGMENT_TAG_PREFIX + index

    private fun createFragment(index: Int) = when (index) {
        0 -> HistoryFragment(arrayList)
        1 -> ResultFragment()
        else -> HistoryFragment(arrayList)
    }


    /**
     * 关闭软键盘ß
     */
    private fun hideKeyboard() {
        val imm: InputMethodManager =
            this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm.isActive && this.currentFocus != null) {
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
        private const val LIST = "list"
        private const val MARKEY = "marker"
        fun start(context: Context?, list: ArrayList<String>?, marker: Int?) {
            val intent = Intent(context, SearchActivity::class.java)
            intent.putStringArrayListExtra(LIST, list)
            val bundle = Bundle()
            bundle.putSerializable(LIST, list as Serializable)
            intent.putExtras(bundle)
            intent.putExtra(MARKEY, marker)
            context?.startActivity(intent)
        }
    }


}