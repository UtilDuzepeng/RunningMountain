package com.miaofen.xiaoying.activity.location

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.mvp.BaseMvpActivity
import com.miaofen.xiaoying.comm.Constant
import com.miaofen.xiaoying.common.data.bean.response.ChooseLocationResponse
import com.miaofen.xiaoying.common.data.bean.response.PositionResponse
import com.miaofen.xiaoying.utils.CacheUtils
import com.miaofen.xiaoying.view.LoadingView
import kotlinx.android.synthetic.main.activity_position.*
import kotlinx.android.synthetic.main.toobar_layout.*
import java.util.*


/**
 * 位置页面
 */
class PositionActivity : BaseMvpActivity<PositionContract.Presenter>(), PositionContract.View,
    PositionRecyclerViewAdapter.OnPositionRecyclerItem {

    private var indexAdapter: IndexAdapter? = null

    private var list = ArrayList<ChooseLocationResponse>()

    private var areaNameList = ArrayList<ChooseLocationResponse>()

    private var mAdapter: PositionRecyclerViewAdapter? = null

    private val handler = Handler() //用于隐藏切换后的字母,在主线程中运行

    private var position = -1

    private var gson = Gson()

    private var province: String? = null

    private var region: String? = null

    private val loadingDialog: LoadingView by lazy {
        LoadingView(this).apply {
            setTipMsg("正在加载")
        }
    }

    override fun returnLayoutId() = R.layout.activity_position

    override fun initView() {
        super.initView()
        title_bar_back.visibility = View.VISIBLE
        title_bar_title.text = "选择所在地区"

        position_recycler.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        mAdapter = PositionRecyclerViewAdapter(R.layout.select_recycler_item, areaNameList)
        position_recycler.adapter = mAdapter
        mAdapter?.setOnPositionRecyclerItem(this)

        if (indexAdapter == null) {
            indexAdapter = IndexAdapter(list, this) //最好适配器new成变量,以后就不用改了
        }

        lv_main.adapter = indexAdapter

        iv_words.setOnIndexChangeListener {
            updateWord(it)
            updateListView(it) //A～Z字母
        }

    }

    private fun updateWord(word: String) {
        tv_word.visibility = View.VISIBLE//显示
        tv_word.text = word
        handler.removeCallbacksAndMessages(null) //先把每次的消息移除
        handler.postDelayed(Runnable {
            //因为handler在主线程中运行，Runnable方法也是运行在主线程
            //（打日志System.out.println是判断Runnable在哪个线程中运行，出现main证明就是在主线程中运行）
            println(Thread.currentThread().name + "-------------------")
            tv_word.visibility = View.GONE //3秒后隐藏
        }, 100) //500毫秒后隐藏
    }

    private fun updateListView(word: String) {
        for (i in list.indices) {
            val listWord: String = list[i].spell //YANGGUANGFU-(转成)->Y
            if (word == listWord) {
                //i是ListView中的位置
                lv_main.setSelection(i) //定位到ListVeiw中的某个位置
                return
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadingDialog.showLoading()
        PositionPresenter(this)
        mPresenter?.doPosition()
    }

    override fun onClick() {
        super.onClick()
        title_bar_back.setOnClickListener { finish() }
        //点击选中地址
        indexAdapter?.setOnClicklistItem(object : IndexAdapter.OnClickSaveItem {
            override fun onClickSave(
                areaName: String, spell: String, areaCode: Int, lon: Double, lat: Double
            ) {
                val chooseLocationResponse =
                    ChooseLocationResponse(areaName, spell, areaCode, lon, lat)
                CacheUtils.writeJson(
                    this@PositionActivity, gson.toJson(chooseLocationResponse),
                    Constant.JSON_CHOOSELOCATION, false
                )
                areaNameList.add(chooseLocationResponse)
                mAdapter?.notifyDataSetChanged()
                mPresenter?.doAccessArea(areaCode)//请求市
            }
        })
    }

    //省请求成功
    override fun onPositionSuccess(data: List<PositionResponse>?) {
        areaNameList.clear()
        list.clear()
        for (item in data!!) {
            val chooseLocationResponse =
                ChooseLocationResponse(
                    item.areaName!!, item.spell!!, item.areaCode!!, item.lon, item.lat
                )
            list.add(chooseLocationResponse)
        }

        indexAdapter?.notifyDataSetChanged()
        loadingDialog.dismiss()
    }

    //省请求失败
    override fun onPositionError() {
        loadingDialog.dismiss()
    }

    //获取下级地区信息（市、区、县）（code、name、gps、spell信息）成功
    override fun onAccessAreaSuccess(data: List<PositionResponse>?) {
        list.clear()
        for (item in data!!) {
            val chooseLocationResponse =
                ChooseLocationResponse(
                    item.areaName!!, item.spell!!, item.areaCode!!, item.lon, item.lat
                )
            list.add(chooseLocationResponse)
        }
        indexAdapter?.notifyDataSetChanged()
        loadingDialog.dismiss()
    }

    //空数据
    override fun onAccessAreaSuccess() {
        val intExtra = intent.getIntExtra("ForResult", -1)
        if (intExtra == 1) {
            val intent = intent
            val bundle = Bundle()
            if (areaNameList.size == 3) {
                bundle.putString("province", areaNameList[0].areaName)
                bundle.putString("region", areaNameList[2].areaName)
            }
            intent.putExtras(bundle)
            setResult(1, intent)
        }
        finish()
    }

    //获取下级地区信息（市、区、县）（code、name、gps、spell信息）失败
    override fun onAccessAreaError() {
        loadingDialog.dismiss()
    }

    //省的点击事件
    override fun onClickItemPositionRecycler(item: ChooseLocationResponse, position: Int) {
        this.position = position
        when (position) {
            0 -> {
                if (areaNameList.size == 3) {
                    areaNameList.removeAt(2)
                    mAdapter?.notifyDataSetChanged()
                } else if (areaNameList.size == 2) {
                    areaNameList.removeAt(1)
                    mAdapter?.notifyDataSetChanged()
                    this.region= areaNameList[1].areaName
                }else {
                    this.province = areaNameList[0].areaName
                }
                mPresenter?.doPosition()
            }
        }
    }

    class IndexAdapter(
        var persons: ArrayList<ChooseLocationResponse>?, var activity: PositionActivity?
    ) : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var convertView = View.inflate(activity, R.layout.position_item_layout, null)
            val viewHolder = ViewHolder()
            viewHolder.tv_item_word = convertView.findViewById<View>(R.id.tv_item_word) as TextView
            viewHolder.tv_item_name = convertView.findViewById<View>(R.id.tv_item_name) as TextView
            viewHolder.linear_item_name =
                convertView.findViewById<View>(R.id.linear_item_name) as LinearLayout
            convertView.tag = viewHolder
            val name: String? = persons?.get(position)?.areaName //阿福
            val word: String? = persons?.get(position)?.spell
            viewHolder.tv_item_word!!.text = word
            viewHolder.tv_item_name!!.text = name

            if (position == 0) {  //若每种信息字母的第一行显示
                viewHolder.tv_item_word!!.visibility = View.VISIBLE //显示
            } else {
                //得到前一个位置对应的字母，如果当前的字母和上一个相同，隐藏TextView；否则就显示
                val preWord: String? = persons?.get(position - 1)?.spell //得到上一个字母A~Z
                if (word == preWord) {  //若word和preWord相同
                    viewHolder.tv_item_word!!.visibility = View.GONE //隐藏
                } else {
                    viewHolder.tv_item_word!!.visibility = View.VISIBLE //显示
                }
            }

            //获取条目数据
            viewHolder.tv_item_name?.setOnClickListener {
                onClickSaveItem?.onClickSave(
                    persons?.get(position)?.areaName!!,
                    persons?.get(position)?.spell!!,
                    persons?.get(position)?.areaCode!!,
                    persons?.get(position)?.lon!!,
                    persons?.get(position)?.lat!!
                )
            }
            return convertView
        }

        override fun getItem(position: Int): Any? {
            return null
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return persons!!.size
        }

        //接口回掉item 点击事件
        interface OnClickSaveItem {
            fun onClickSave(
                areaName: String, spell: String, areaCode: Int, lon: Double, lat: Double
            )
        }

        var onClickSaveItem: OnClickSaveItem? = null

        fun setOnClicklistItem(onCall: OnClickSaveItem) {
            this.onClickSaveItem = onCall
        }
    }

    //优化
    internal class ViewHolder {
        var tv_item_word: TextView? = null
        var tv_item_name: TextView? = null
        var linear_item_name: LinearLayout? = null
    }

    companion object {
        @JvmStatic
        fun start(context: Context?) {
            val intent = Intent(context, PositionActivity::class.java)
            context?.startActivity(intent)
        }

        fun startActivityForResult(context: Activity?) {
            val intent = Intent(context, PositionActivity::class.java)
            intent.putExtra("ForResult", 1)
            context?.startActivityForResult(intent, 0)
        }
    }

}