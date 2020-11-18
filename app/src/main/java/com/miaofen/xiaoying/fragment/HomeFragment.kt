package com.miaofen.xiaoying.fragment


import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.activity.SearchActivity
import com.miaofen.xiaoying.base.BaseFragment
import com.miaofen.xiaoying.common.data.bean.response.ImagerDataBean
import com.miaofen.xiaoying.fragment.hot.HotFragment
import com.miaofen.xiaoying.fragment.nearby.NearbyFragment
import com.miaofen.xiaoying.fragment.newest.NewestFragment
import com.youth.banner.config.BannerConfig
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.home_toolbar_layout.*


/**
 * 首页
 */
class HomeFragment : BaseFragment(), TabLayout.OnTabSelectedListener {


    val list = listOf<String>("最新", "热门", "附近")

    var list_path = ArrayList<ImagerDataBean>()


    override fun getLayoutResources() = R.layout.fragment_home

    override fun initView() {
        super.initView()
        setTitleToCollapsingToolbarLayout()
        setTab()
        setItem()
        setBanner()
    }

    override fun initData() {
        super.initData()
        view_search.setOnClickListener { SearchActivity.start(activity) }
    }

    /**
     * 使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，
     * 设置到Toolbar上则不会显示
     */
    private fun setTitleToCollapsingToolbarLayout() {
        app_bar_layout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset <= -banner.getHeight() / 2) {
                toolbar.visibility = View.VISIBLE
            } else {
                toolbar.visibility = View.INVISIBLE
            }
        })
    }

    private fun setBanner(){
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
        banner.addBannerLifecycleObserver(this)//添加生命周期观察者
            .setAdapter(ImageAdapter(list_path))
            .setIndicator(CircleIndicator(this.context));
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
        updateTabTextView(business_tablayout.getTabAt(business_tablayout.getSelectedTabPosition()), true);
        updateTabTextView(business_tablayout.getTabAt(1), false);
        updateTabTextView(business_tablayout.getTabAt(2), false);
    }


    private fun setTab() {
        val tab1: NewestFragment = NewestFragment()
        val tab2: HotFragment = HotFragment()
        val tab3: NearbyFragment = NearbyFragment()
        var list = listOf<Fragment>(tab1, tab2, tab3)
        viewpager.adapter = MyAdapter(list, fragmentManager) //让tab和viewpager关联起来
        business_tablayout.setupWithViewPager(viewpager)
        business_tablayout.setOnTabSelectedListener(this)
    }


    private fun getTabView(currentPosition: Int): View? {
        val view: View = LayoutInflater.from(this.context).inflate(R.layout.tab_item, null)
        val textView = view.findViewById<View>(R.id.tab_item_textview) as TextView
        textView.setText(list.get(currentPosition))
        return view
    }

    override fun onTabReselected(p0: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        updateTabTextView(tab, false);
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        //tab被选的时候回调
//        viewpager.currentItem = tab!!.position
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


}