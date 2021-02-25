package com.miaofen.xiaoying.activity.travelplan

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.activity.travelplan.ipost.IPostFragment
import com.miaofen.xiaoying.activity.travelplan.participate.ParticipateFragment
import com.miaofen.xiaoying.adapter.MyAdapter
import com.miaofen.xiaoying.base.BaseActivity
import kotlinx.android.synthetic.main.activity_travel_plan.*
import kotlinx.android.synthetic.main.toobar_layout.*

/**
 * 旅行计划
 */
 class TravelPlanActivity : BaseActivity() , TabLayout.OnTabSelectedListener {

    val list = listOf<String>("我发布的", "我参与的")

    override fun returnLayoutId() = R.layout.activity_travel_plan

    override fun initView() {
        super.initView()
        setTab()
        setItem()
        title_bar_back.visibility = View.VISIBLE
        title_bar_title.text = "旅行计划"
        travel_plan_tablayout.tabRippleColor = ColorStateList.valueOf(resources.getColor(R.color.transparent))

    }

    override fun initData() {
        super.initData()
        title_bar_back.setOnClickListener { finish() }
    }

    private fun setTab() {
        val tab1: IPostFragment = IPostFragment()
        val tab2: ParticipateFragment = ParticipateFragment()
        val list = listOf<Fragment>(tab1, tab2)
        travel_plan_viewpager.adapter = MyAdapter(list, supportFragmentManager) //让tab和viewpager关联起来
        travel_plan_tablayout.setupWithViewPager(travel_plan_viewpager)
        travel_plan_tablayout.setOnTabSelectedListener(this)
    }

    private fun setItem() {
        travel_plan_tablayout.getTabAt(0)?.text = list[0]
        travel_plan_tablayout.getTabAt(1)?.text = list[1]

        for (i in 0 until travel_plan_tablayout.tabCount) {
            val tab: TabLayout.Tab = travel_plan_tablayout.getTabAt(i)!!
            tab.customView = getTabView(i)
        }
        updateTabTextView(
            travel_plan_tablayout.getTabAt(travel_plan_tablayout.getSelectedTabPosition()),
            true)
        updateTabTextView(travel_plan_tablayout.getTabAt(1), false)
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
            val tabSelect = tab?.customView?.findViewById<TextView>(R.id.tab_item_textview) as TextView
            tabSelect.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            tabSelect.setTextColor(getResources().getColor(R.color.A212A3D))
            tabSelect.textSize = 18f
            tabSelect.text = tab.text

        } else {
            val tabUnSelect = tab?.customView
                ?.findViewById<TextView>(R.id.tab_item_textview) as TextView
            tabUnSelect.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
            tabUnSelect.setTextColor(getResources().getColor(R.color.A686E7A))
            tabUnSelect.textSize = 16f
            tabUnSelect.text = tab.text
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

    companion object {
        fun start(context: Context?) {
            val intent = Intent(context, TravelPlanActivity::class.java)
            context?.startActivity(intent)
        }
    }
}