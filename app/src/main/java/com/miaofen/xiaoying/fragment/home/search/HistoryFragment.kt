package com.miaofen.xiaoying.fragment.home.search


import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_history.*
import kotlin.collections.ArrayList

/**
 * 搜索历史
 */
class HistoryFragment( recommend: ArrayList<String>?) : BaseFragment(), ObserverListener {

    private var listRecommend: ArrayList<String>? = recommend

    override fun getLayoutResources() = R.layout.fragment_history

    override fun initView() {
        super.initView()
        ObserverManager.getInstance().add(this)
    }

    override fun initData() {
        super.initData()
        if (listRecommend == null) return
        if (listRecommend!!.size <= 0) return
        for (i in listRecommend!!.indices) {
            val textView = LayoutInflater.from(activity)
                .inflate(R.layout.item_flow, hot_floeviewgroup, false) as TextView
            textView.text = listRecommend!![i]
            textView.setOnClickListener(View.OnClickListener {
                // et_search.setText((v as TextView).text)
            })
            hot_floeviewgroup.addView(textView)
        }
    }

    private fun initDatas() {
//        if (list.size <= 0) return
//        for (i in list.indices) {
//            val textView = LayoutInflater.from(activity)
//                .inflate(R.layout.item_flow, flowlayout, false) as TextView
//            textView.text = list[i]
//            textView.setOnClickListener(View.OnClickListener {
//                // et_search.setText((v as TextView).text)
//            })
//            flowlayout.addView(textView)
//        }
    }

    override fun observerUpData(content: String?) {
//        list.clear()
//        list.add(content!!)
//        Log.e("TAG", "我收到消息了 ： $content")
//        for (i in list.indices) {
//            val textView = LayoutInflater.from(activity)
//                .inflate(R.layout.item_flow, flowlayout, false) as TextView
//            textView.text = content
//            flowlayout.addView(textView)
//        }
    }

}