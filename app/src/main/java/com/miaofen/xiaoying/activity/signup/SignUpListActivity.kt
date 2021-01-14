package com.miaofen.xiaoying.activity.signup

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.activity.details.ProjectDetailsActivity
import com.miaofen.xiaoying.activity.signup.examine.ExamineFragment
import com.miaofen.xiaoying.activity.signup.refuse.RefuseFragment
import com.miaofen.xiaoying.adapter.MyAdapter
import com.miaofen.xiaoying.base.BaseActivity
import com.miaofen.xiaoying.utils.updateTabTextView
import kotlinx.android.synthetic.main.activity_signup_list.*
import kotlinx.android.synthetic.main.toobar_layout.*
import kotlinx.android.synthetic.main.toobar_layout.title_bar_back

/**
 * 报名列表
 */
class SignUpListActivity : BaseActivity(), TabLayout.OnTabSelectedListener {

    val list = listOf<String>("审核中", "已拒绝")

    var planId: Int? = -1

    override fun returnLayoutId() = R.layout.activity_signup_list

    override fun initView() {
        super.initView()
        planId = intent.getIntExtra(ID, -1)
        title_bar_back?.visibility = View.VISIBLE
        title_bar_back?.setOnClickListener { finish() }
        title_bar_title.text = "报名列表"
        tab_signUpList.tabRippleColor = ColorStateList.valueOf(resources.getColor(R.color.transparent))
        setTab()
        setItem()
    }

    //设置tab
    private fun setTab() {
        val tab1: ExamineFragment = ExamineFragment(planId)
        val tab2: RefuseFragment = RefuseFragment(planId)
        val list = listOf<Fragment>(tab1, tab2)
        signUp_viewpager.adapter = MyAdapter(list, supportFragmentManager) //让tab和viewpager关联起来
        tab_signUpList.setupWithViewPager(signUp_viewpager)
        tab_signUpList.setOnTabSelectedListener(this)
    }

    private fun setItem() {
        tab_signUpList.getTabAt(0)?.text = list[0]
        tab_signUpList.getTabAt(1)?.text = list[1]

        for (i in 0 until tab_signUpList.tabCount) {
            val tab: TabLayout.Tab = tab_signUpList.getTabAt(i)!!
            if (tab != null) {
                tab.customView = getTabView(i)
            }
        }
        updateTabTextView(
            tab_signUpList.getTabAt(tab_signUpList.selectedTabPosition), true, this
        )
        updateTabTextView(tab_signUpList.getTabAt(1), false, this)
    }

    private fun getTabView(currentPosition: Int): View? {
        val view: View = LayoutInflater.from(this).inflate(R.layout.tab_item, null)
        val textView = view.findViewById<View>(R.id.tab_item_textview) as TextView
        textView.text = list[currentPosition]
        return view
    }


    companion object {
        const val ID = "id"
        fun start(context: Context?, id: Int?) {
            val intent = Intent(context, SignUpListActivity::class.java)
            intent.putExtra(ProjectDetailsActivity.ID, id)
            context?.startActivity(intent)
        }
    }

    override fun onTabReselected(p0: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        updateTabTextView(tab, false, this)
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        updateTabTextView(tab, true, this)
    }

}