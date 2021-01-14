package com.miaofen.xiaoying.fragment.home.search.history



import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.mvp.BaseMvpFragment
import com.miaofen.xiaoying.fragment.home.search.back.ObserverListener
import com.miaofen.xiaoying.fragment.home.search.back.ObserverManager
import kotlinx.android.synthetic.main.fragment_history.*
import kotlin.collections.ArrayList

/**
 * 搜索历史
 */
class HistoryFragment(recommend: ArrayList<String>?) : BaseMvpFragment<HistoryContract.Presenter>(),
    ObserverListener, HistoryContract.View {

    private var listRecommend: ArrayList<String>? = recommend

    private var listHistory = ArrayList<String>()

    override fun getLayoutResources() = R.layout.fragment_history

    override fun initView() {
        super.initView()
        HistoryPresenter(this)
        mPresenter?.doHistory()
        ObserverManager.getInstance().add(this)
    }

    override fun initData() {
        super.initData()
        if (listRecommend == null) return
        if (listRecommend!!.size <= 0) return
        //热门推荐
        for (i in listRecommend!!.indices) {
            val textView = LayoutInflater.from(activity)
                .inflate(R.layout.item_flow, hot_floeviewgroup, false) as TextView
            textView.text = listRecommend!![i]
            textView.setOnClickListener(View.OnClickListener {
                ObserverManager.getInstance().notifyObserver(textView.text.toString())
            })
            hot_floeviewgroup.addView(textView)
        }
    }

    override fun observerUpData(content: String?) {
        if (activity != null){
            val textView = LayoutInflater.from(activity)
                .inflate(R.layout.item_flow, flowlayout, false) as TextView
            textView.text = content
            textView.setOnClickListener {
                ObserverManager.getInstance().notifyObserver(textView.text.toString());
            }
            flowlayout.addView(textView)
        }
    }

    /*-----------搜索历史------------*/
    override fun onHistorySuccess(data: List<String>) {
        if (data != null) {
            for (item in data) {
                listHistory.add(item)
            }
            for (i in listHistory.indices) {
                val textView = LayoutInflater.from(activity)
                    .inflate(R.layout.item_flow, flowlayout, false) as TextView
                textView.text = listHistory[i]
                textView.setOnClickListener(View.OnClickListener {
                    ObserverManager.getInstance().notifyObserver(textView.text.toString());
                })
                flowlayout.addView(textView)
            }
        }
    }

    override fun onHistoryError() {

    }

}