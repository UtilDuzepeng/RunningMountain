package com.miaofen.xiaoying.fragment.search

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseFragment
import com.miaofen.xiaoying.adapter.MyAdapter
import kotlinx.android.synthetic.main.fragment_result.*


/**
 * 搜索结果页面
 */
class ResultFragment : BaseFragment(), TabLayout.OnTabSelectedListener {

    val list = listOf<String>("计划", "用户")

    override fun getLayoutResources() = R.layout.fragment_result

    override fun initView() {
        super.initView()
        setTab()
        setItem()
    }

    private fun setTab() {
        val tab1: PlanFragment = PlanFragment()
        val tab2: UseFragment = UseFragment()
        var list = listOf<Fragment>(tab1, tab2)
        result_viewpager.adapter = MyAdapter(
            list,
            fragmentManager
        ) //让tab和viewpager关联起来
        result_tablayout.setupWithViewPager(result_viewpager)
        result_tablayout.setOnTabSelectedListener(this)
    }

    private fun setItem() {
        result_tablayout.getTabAt(0)?.text = list[0]
        result_tablayout.getTabAt(1)?.text = list[1]

        for (i in 0 until result_tablayout.getTabCount()) {
            val tab: TabLayout.Tab = result_tablayout.getTabAt(i)!!
            if (tab != null) {
                tab.customView = getTabView(i)
            }
        }
        updateTabTextView(result_tablayout.getTabAt(result_tablayout.getSelectedTabPosition()), true)
        updateTabTextView(result_tablayout.getTabAt(1), false)
    }

    private fun getTabView(currentPosition: Int): View? {
        val view: View = LayoutInflater.from(context).inflate(R.layout.tab_item, null)
        val textView = view.findViewById<View>(R.id.tab_item_textview) as TextView
        textView.setText(list.get(currentPosition))
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
            tabSelect.setTextSize(18f)
            tabSelect.text = tab.text

        } else {
            val tabUnSelect = tab?.customView
                ?.findViewById<TextView>(R.id.tab_item_textview) as TextView
            tabUnSelect.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
            tabUnSelect.setTextColor(getResources().getColor(R.color.A686E7A))
            tabUnSelect.setTextSize(16f)
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


}