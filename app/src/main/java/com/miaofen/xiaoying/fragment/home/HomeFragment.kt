package com.miaofen.xiaoying.fragment.home


import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.activity.SearchActivity
import com.miaofen.xiaoying.adapter.MyAdapter
import com.miaofen.xiaoying.base.mvp.BaseMvpFragment
import com.miaofen.xiaoying.comm.Constant
import com.miaofen.xiaoying.common.data.bean.response.BannerResponse
import com.miaofen.xiaoying.common.data.bean.response.ImagerDataBean
import com.miaofen.xiaoying.fragment.ImageAdapter
import com.miaofen.xiaoying.fragment.home.hot.HotFragment
import com.miaofen.xiaoying.fragment.home.hottest.NewestFragment
import com.miaofen.xiaoying.fragment.home.nearby.NearbyFragment
import com.miaofen.xiaoying.utils.CacheUtils
import com.miaofen.xiaoying.utils.updateTabTextView
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.home_toolbar_layout.*


/**
 * 首页
 */
class HomeFragment : BaseMvpFragment<HomeContract.Presenter>(), HomeContract.View,
    TabLayout.OnTabSelectedListener {

    val list = listOf<String>("最新", "热门", "附近")
    private var list_path = ArrayList<ImagerDataBean>()

    override fun getLayoutResources() = R.layout.fragment_home

    override fun initView() {
        super.initView()
        HomePresenter(this)
        mPresenter?.doRotationChart()
        mPresenter?.recommend()
        setTab()
        setItem()
    }

    override fun initData() {
        super.initData()
        //获取定位信息
        val readJson = CacheUtils.readJson(context, Constant.JSON_ADDRESS)
        toolbar_search_box.setPositionText(readJson.getString("city"))
        //位置点击事件
        toolbar_search_box.setOnClickPosition(context)
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
        if (context != null) {
            updateTabTextView(
                business_tablayout.getTabAt(business_tablayout.selectedTabPosition), true
                , context!!
            )
            updateTabTextView(business_tablayout.getTabAt(1), false, context!!)
            updateTabTextView(business_tablayout.getTabAt(2), false, context!!)
        }

    }


    private fun setTab() {
        val tab1: NewestFragment = NewestFragment()
        val tab2: HotFragment = HotFragment()
        val tab3: NearbyFragment = NearbyFragment()
        val list = listOf<Fragment>(tab1, tab2, tab3)
        viewpager.adapter = MyAdapter(list, fragmentManager) //让tab和viewpager关联起来
        business_tablayout.setupWithViewPager(viewpager)
        business_tablayout.setOnTabSelectedListener(this)
    }


    private fun getTabView(currentPosition: Int): View? {
        val view: View = LayoutInflater.from(this.context).inflate(R.layout.tab_item, null)
        val textView = view.findViewById<View>(R.id.tab_item_textview) as TextView
        textView.text = list[currentPosition]
        return view
    }

    override fun onTabReselected(p0: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        if (context != null) {
            updateTabTextView(tab, false, context!!)
        }
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        //tab被选的时候回调
        if (context != null) {
            updateTabTextView(tab, true, context!!)
        }
    }

    /**---------------------轮播图-----------------------**/
    override fun onRotationChartSuccess(data: List<BannerResponse>) {

        for (item in data) {
            val imagerDataBean = ImagerDataBean()
            imagerDataBean.url = item.coverImage
            list_path.add(imagerDataBean)
        }
        banner_layout.addBannerLifecycleObserver(this) //添加生命周期观察者
            .setAdapter(ImageAdapter(list_path))
            .indicator = CircleIndicator(activity)
    }

    override fun onRotationChartError() {

    }

    /**---------------------热门推荐------------------------**/

    override fun onRecommendSuccess(data: ArrayList<String>) {
        toolbar_search_box.setDataList(context, data)
        toolbar_search_box.linearLayout.setOnClickListener {
            toolbar_search_box.marker
            SearchActivity.start(activity, data, toolbar_search_box.marker)
        }
    }

    override fun onRecommendError() {

    }


}