package com.miaofen.xiaoying.activity.personal

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseActivity
import com.miaofen.xiaoying.adapter.MyAdapter
import com.miaofen.xiaoying.fragment.home.hot.HotFragment
import com.miaofen.xiaoying.fragment.home.hottest.NewestFragment
import kotlinx.android.synthetic.main.activity_personal_hom_pager.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.personal_hander.*

/**
 * 个人主页(三种状态都在此页面)
 */
class PersonalHomPagerActivity : BaseActivity(), TabLayout.OnTabSelectedListener {

    val list = listOf<String>("资料", "计划")

    override fun returnLayoutId() = R.layout.activity_personal_hom_pager

    override fun initView() {
        super.initView()
        setTitleToCollapsingToolbarLayout()
        setTab()
        setItem()
    }

    override fun initData() {
        super.initData()

    }

    /**
     * 使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，
     * 设置到Toolbar上则不会显示
     */
    private fun setTitleToCollapsingToolbarLayout() {
        personal_app_bar_layout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset <= -linear_hander.getHeight() / 2) {
                personal_toolbar.visibility = View.VISIBLE
            } else {
                personal_toolbar.visibility = View.INVISIBLE
            }
        })
    }

    private fun setTab() {
        val tab1: NewestFragment =
            NewestFragment()
        val tab2: HotFragment =
            HotFragment()
        var list1 = listOf<Fragment>(tab1, tab2)
        personal_viewpager.adapter = MyAdapter(list1, supportFragmentManager) //让tab和viewpager关联起来
        personal_tablayout.setupWithViewPager(viewpager)
        personal_tablayout.setOnTabSelectedListener(this)
    }

    private fun setItem() {
        personal_tablayout.getTabAt(0)?.text = list[0]
        personal_tablayout.getTabAt(1)?.text = list[1]

        for (i in 0 until personal_tablayout.getTabCount()) {
            val tab: TabLayout.Tab = personal_tablayout.getTabAt(i)!!
            if (tab != null) {
                tab.customView = getTabView(i)
            }
        }
//        updateTabTextView(personal_tablayout.getTabAt(personal_tablayout.getSelectedTabPosition()), true)
//        updateTabTextView(personal_tablayout.getTabAt(1), false)
    }


    private fun getTabView(currentPosition: Int): View? {
        val view: View = LayoutInflater.from(this).inflate(R.layout.tab_item, null)
        val textView = view.findViewById<View>(R.id.tab_item_textview) as TextView
        textView.text = list[currentPosition]
        return view
    }

    /**
     * 选中加粗
     */
    private fun updateTabTextView(tab: TabLayout.Tab?, isSelect: Boolean) {
        if (isSelect) {
            //选中加粗
            val tabSelect = tab?.customView?.findViewById<TextView>(R.id.tab_item_textview) //as TextView
            tabSelect?.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            tabSelect?.setTextColor(resources.getColor(R.color.A212A3D))
            tabSelect?.textSize = 18f
            tabSelect?.text = tab?.text

        } else {
            val tabUnSelect = tab?.customView
                ?.findViewById<TextView>(R.id.tab_item_textview) //as TextView
            tabUnSelect?.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
            tabUnSelect?.setTextColor(resources.getColor(R.color.A686E7A))
            tabUnSelect?.textSize = 16f
            tabUnSelect?.text = tab?.text
        }
    }

    companion object {
        fun start(context: Context?) {
            val intent = Intent(context, PersonalHomPagerActivity::class.java)
            context?.startActivity(intent)
        }
    }


    override fun onTabReselected(p0: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        updateTabTextView(tab, false);
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        updateTabTextView(tab, true);
    }

}