package com.miaofen.xiaoying.fragment.home


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.activity.ProjectDetailsActivity
import com.miaofen.xiaoying.adapter.MyAdapter
import com.miaofen.xiaoying.base.mvp.BaseMvpFragment
import com.miaofen.xiaoying.common.data.bean.response.ImagerDataBean
import com.miaofen.xiaoying.fragment.ImageAdapter
import com.miaofen.xiaoying.fragment.home.hot.HotFragment
import com.miaofen.xiaoying.fragment.home.hottest.NewestFragment
import com.miaofen.xiaoying.fragment.home.nearby.NearbyFragment
import com.miaofen.xiaoying.utils.ToastUtils
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.home_toolbar_layout.*


/**
 * 首页
 */
class HomeFragment : BaseMvpFragment<RotationChartContract.Presenter>(), RotationChartContract.View,
    TabLayout.OnTabSelectedListener {

    val list = listOf<String>("最新", "热门", "附近")

    private var list_path = ArrayList<ImagerDataBean>()

    override fun getLayoutResources() = R.layout.fragment_home

    override fun initView() {
        super.initView()
        RotationChartPresenter(this)
        mPresenter?.doRotationChart()
        setTab()
        setItem()
        setBanner()
    }

    override fun initData() {
        super.initData()
        val texts: MutableList<String> = ArrayList()
        for (i in 0..9) {
            texts.add("晋阳湖公园$i")
        }
        toolbar_search_box.setDataList(context,texts)
//        toolbar_search_box.linearLayout.setOnClickListener { ToastUtils.showToast(texts.get(toolbar_search_box.marker)) }
        toolbar_search_box.linearLayout.setOnClickListener { ProjectDetailsActivity.start(activity) }
    }

    private fun setBanner() {
        val imagerDataBean = ImagerDataBean()
        imagerDataBean.url = "http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg"
        list_path.add(imagerDataBean)
        val imagerDataBean1 = ImagerDataBean()
        imagerDataBean1.url = "http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg"
        list_path.add(imagerDataBean)
        val imagerDataBean2 = ImagerDataBean()
        imagerDataBean2.url = "http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg"
        list_path.add(imagerDataBean)
        val imagerDataBean3 = ImagerDataBean()
        imagerDataBean3.url = "http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg"
        list_path.add(imagerDataBean)
        banner_layout.setFilletBanner(list_path,this.activity)
    }


    private fun setItem() {
        business_tablayout.getTabAt(0)?.text = list[0]
        business_tablayout.getTabAt(1)?.text = list[1]
        business_tablayout.getTabAt(2)?.text = list[2]

        for (i in 0 until business_tablayout.getTabCount()) {
            val tab: TabLayout.Tab = business_tablayout.getTabAt(i)!!
            if (tab != null) {
                tab.customView = getTabView(i)
            }
        }
        updateTabTextView(
            business_tablayout.getTabAt(business_tablayout.selectedTabPosition), true
        )
        updateTabTextView(business_tablayout.getTabAt(1), false);
        updateTabTextView(business_tablayout.getTabAt(2), false);
    }


    private fun setTab() {
        val tab1: NewestFragment = NewestFragment()
        val tab2: HotFragment = HotFragment()
        val tab3: NearbyFragment = NearbyFragment()
        var list = listOf<Fragment>(tab1, tab2, tab3)
        viewpager.adapter = MyAdapter(
            list,
            fragmentManager
        ) //让tab和viewpager关联起来
        business_tablayout.setupWithViewPager(viewpager)
        business_tablayout.setOnTabSelectedListener(this)
    }


    private fun getTabView(currentPosition: Int): View? {
        val view: View = LayoutInflater.from(this.context).inflate(R.layout.tab_item, null)
        val textView = view.findViewById<View>(R.id.tab_item_textview) as TextView
        textView.text = list.get(currentPosition)
        return view
    }

    override fun onTabReselected(p0: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        updateTabTextView(tab, false);
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        //tab被选的时候回调
        updateTabTextView(tab, true);
    }

    /**
     * 选中加粗
     */
    private fun updateTabTextView(tab: TabLayout.Tab?, isSelect: Boolean) {
        if (isSelect) {
            //选中加粗
            val tabSelect = tab?.customView
                ?.findViewById<TextView>(R.id.tab_item_textview) as TextView
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

    override fun onRotationChartSuccess(data: String) {

    }

    override fun onRotationChartError() {

    }


}