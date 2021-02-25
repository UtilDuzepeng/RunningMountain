package com.miaofen.xiaoying.activity.personal

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.activity.materia.MaterialsActivity
import com.miaofen.xiaoying.base.mvp.BaseMvpActivity
import com.miaofen.xiaoying.common.data.bean.response.DetailedResponse
import com.miaofen.xiaoying.common.data.bean.response.ExternalDisplayResponse
import com.miaofen.xiaoying.common.data.bean.response.PersonalHomPagerResponse
import com.miaofen.xiaoying.utils.ToastUtils
import com.miaofen.xiaoying.utils.getMinuteTime
import com.miaofen.xiaoying.utils.getStringDateShort
import com.miaofen.xiaoying.view.FlowViewGroup
import com.miaofen.xiaoying.view.LoadingView
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.activity_personal_hom_pager.*
import kotlinx.android.synthetic.main.personal_hander.*

/**
 * 个人主页(三种状态都在此页面)
 */
class PersonalHomPagerActivity : BaseMvpActivity<PersonalHomPagerContract.Presenter>(),
    PersonalHomPagerContract.View, OnRefreshLoadMoreListener {//, TabLayout.OnTabSelectedListener

    private var longUserId: Long = -1

    private var list = ArrayList<ExternalDisplayResponse.ContentBean>()

    private var mAdapter: PersonalHomPagerRecyclerAdapter? = null

    private var inflate: View? = null

    private var tv_detailed_gender: TextView? = null
    private var tv_detailed_birthday: TextView? = null
    private var tv_detailed_register: TextView? = null
    private var tv_detailed_region: TextView? = null
    private var tv_detailed_active: TextView? = null
    private var tv_city_he_visited: TextView? = null
    private var tv_tas_footprint: TextView? = null
    private var tv_detailed_image: ImageView? = null
    private var flowlayout_city: FlowViewGroup? = null
    private var flowlayout_footprint: FlowViewGroup? = null
    private var region = StringBuffer()
    private var detailedActive = StringBuffer()

    private var state: Int = -1
    private var totalElements: Int = -1

    private val loadingDialog: LoadingView by lazy {
        LoadingView(this).apply {
            setTipMsg("正在加载")
        }
    }

    override fun returnLayoutId() = R.layout.activity_personal_hom_pager

    override fun initView() {
        super.initView()
        this.longUserId = intent.getLongExtra(USERID, -1)
        inflate = layoutInflater.inflate(R.layout.detailed_data_hand, null)
        tv_detailed_gender = inflate?.findViewById<TextView>(R.id.tv_detailed_gender)
        tv_city_he_visited = inflate?.findViewById<TextView>(R.id.tv_city_he_visited)
        tv_tas_footprint = inflate?.findViewById<TextView>(R.id.tv_tas_footprint)
        tv_detailed_birthday = inflate?.findViewById<TextView>(R.id.tv_detailed_birthday)
        tv_detailed_register = inflate?.findViewById<TextView>(R.id.tv_detailed_register)
        tv_detailed_region = inflate?.findViewById<TextView>(R.id.tv_detailed_region)
        tv_detailed_active = inflate?.findViewById<TextView>(R.id.tv_detailed_active)
        tv_detailed_image = inflate?.findViewById<ImageView>(R.id.tv_detailed_image)
        flowlayout_city = inflate?.findViewById<FlowViewGroup>(R.id.flowlayout_city)
        flowlayout_footprint = inflate?.findViewById<FlowViewGroup>(R.id.flowlayout_footprint)
        personal_recycler.layoutManager = LinearLayoutManager(this)
        personal_smart.setOnRefreshLoadMoreListener(this)
        personal_smart.setDisableContentWhenRefresh(true) //在刷新的时候禁止列表的操作
        personal_smart.setDisableContentWhenLoading(true) //在加载的时候禁止列表的操作
        personal_smart.setEnableLoadMore(false)
        personal_smart.setEnableRefresh(true)
    }


    override fun initData() {
        super.initData()
        loadingDialog.showLoading()
        PersonalHomPagerPresenter(this)
        mPresenter?.doPersonalHomPager(longUserId)
        mAdapter = PersonalHomPagerRecyclerAdapter(R.layout.collection_list_item, list, this)
        personal_recycler.adapter = mAdapter
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick() {
        super.onClick()
        personal_bar_back.setOnClickListener { finish() }//关闭
        tv_data.setOnClickListener {
            state = 1
            list.clear()
            mAdapter?.removeAllFooterView()
            mAdapter?.removeAllHeaderView()
            mAdapter?.addHeaderView(inflate)
            mPresenter?.doDetailsData(longUserId)
            mAdapter?.notifyDataSetChanged()
            tv_plan.textSize = 16f
            tv_plan.setTextColor(getColor(R.color.A686E7A))
            view_plan.visibility = View.INVISIBLE
            tv_data.textSize = 18f
            tv_data.setTextColor(getColor(R.color.A212A3D))
            view_data.visibility = View.VISIBLE
        }//点击资料

        tv_plan.setOnClickListener {
            state = 2
            mPresenter?.doExternalDisplay(longUserId, 1, 10)
            mAdapter?.removeAllHeaderView()
            mAdapter?.notifyDataSetChanged()
            tv_data.textSize = 16f
            tv_data.setTextColor(getColor(R.color.A212A3D))
            view_data.visibility = View.INVISIBLE
            tv_plan.textSize = 18f
            tv_plan.setTextColor(getColor(R.color.A686E7A))
            view_plan.visibility = View.VISIBLE
        }//点击计划

        tv_editing_materials.setOnClickListener {
            MaterialsActivity.start(this)
        }//编辑资料
    }

    //用户对外基本资料请求成功
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onPersonalHomPagerSuccess(data: PersonalHomPagerResponse?) {
        //是否展示编辑资料
        if (data?.canEditProfile!!) {//展示
            tv_personal_follow.visibility = View.GONE
            tv_personal_chat.visibility = View.GONE
            tv_editing_materials.visibility = View.VISIBLE
            tv_editing_materials.setOnClickListener { //编辑资料
                MaterialsActivity.start(this)
            }
        } else {//不展示
            tv_editing_materials.visibility = View.GONE
            tv_personal_follow.visibility = View.VISIBLE
            tv_personal_chat.visibility = View.VISIBLE

            if (data.follow!!) {//已关注
                tv_personal_follow.text = "已关注"
                tv_personal_follow.setBackgroundResource(R.drawable.grey_circle_back)
                tv_personal_follow.setOnClickListener {//关注
                    loadingDialog.showLoading()
                    mPresenter?.doCancelAttentio(data.userId)
                }
            } else {//未关注
                tv_personal_follow.text = "关注"
                tv_personal_follow.setBackgroundResource(R.drawable.pink_circle_back)
                tv_personal_follow.setOnClickListener {//关注
                    loadingDialog.showLoading()
                    mPresenter?.doFocusOnUsers(data.userId)
                }
            }
            tv_personal_chat.setOnClickListener {//聊天
                ToastUtils.showToast("聊天")
            }
        }
        //名称
        tv_personal_nickName.text = data.nickName
        //摩托车型号
        if (data.motorcycle != null) {
            tv_personal_motorcycle.visibility = View.VISIBLE
            tv_personal_motorcycle.text = data.motorcycle
        } else {
            tv_personal_motorcycle.visibility = View.GONE
        }
        //个性签名
        if (data.personalSignature != null) {
            tv_personal_personalSignature.visibility = View.VISIBLE
            tv_personal_personalSignature.text = data.personalSignature
        } else {
            tv_personal_personalSignature.visibility = View.GONE
        }
        //粉丝数
        tv_peronal_fansNumber.text = "${data.fansNumber}"
        //关注数
        tv_peronal_followsNumber.text = "${data.followsNumber}"
        //发布计划数量
        tv_peronal_planCount.text = "${data.planCount}"
        //头像
        Glide.with(this).load(data.avatarUrl)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .fallback(R.drawable.zhanweitu)
            .error(R.drawable.error_zhanweitu)
            .into(image_head_portrait)
        //是否展示编辑资料
        if (data.canEditProfile!!) { //展示资料
            mAdapter?.removeAllHeaderView()
            mAdapter?.addHeaderView(inflate)
            mPresenter?.doDetailsData(longUserId)
            view_plan.visibility = View.GONE
            tv_data.visibility = View.VISIBLE
            view_data.visibility = View.VISIBLE
            tv_plan.textSize = 16F
            tv_plan.setTextColor(getColor(R.color.A686E7A))
            mAdapter?.notifyDataSetChanged()
        } else {//不展示资料
            mAdapter?.removeHeaderView(inflate)
            mPresenter?.doExternalDisplay(longUserId, 1, 10)
            tv_data.visibility = View.GONE
            view_data.visibility = View.GONE
            tv_plan.textSize = 18F
            tv_plan.setTextColor(getColor(R.color.A212A3D))
            view_plan.visibility = View.VISIBLE
            mAdapter?.notifyDataSetChanged()
        }
    }

    //用户对外基本资料请求失败
    override fun onPersonalHomPagerError() {
        loadingDialog.dismiss()
    }

    /*-----------用户详细资料请求-------------*/
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDetailsDataSuccess(data: DetailedResponse?) {
        //设置性别
        when (data?.userInfo?.gender) {
            0 -> {
                tv_detailed_gender?.text = "保密"
            }
            1 -> {
                tv_detailed_gender?.text = "男"
            }
            2 -> {
                tv_detailed_gender?.text = "女"
            }
        }
        //设置生日
        if (data?.userInfo?.birthdayTime != null) {
            tv_detailed_birthday?.text = data.userInfo?.birthdayTime
        } else {
            tv_detailed_birthday?.text = ""
        }
        //注册时间
        if (data?.userInfo?.createTime != null) {
            tv_detailed_register?.text = "${getStringDateShort(data.userInfo?.createTime!!)}"
        } else {
            tv_detailed_register?.text = ""
        }
        //地区
        region.delete(0, region.length)
        if (data?.userInfo?.province != null) {
            region.append(data.userInfo?.province)
        }
        if (data?.userInfo?.city != null) {
            region.append(" · ")
            region.append(data.userInfo?.city)
        }
        tv_detailed_region?.text = region
        //活跃距离 tv_detailed_active
        detailedActive.delete(0, detailedActive.length)
        if (data?.userInfo?.updateTime != null) {
            detailedActive.append("${getMinuteTime(data.userInfo?.updateTime!!)}分钟前")
        }
        if (data?.userInfo?.distance != null) {
            detailedActive.append("${data.userInfo?.distance!!}km")
        }
        tv_detailed_active?.text = detailedActive
        //是否认证
        if (data?.authenticationList?.size!! > 0) {
            if (data.authenticationList!![0].authentication!!) {
                tv_detailed_image?.setImageDrawable(getDrawable(R.drawable.renzheng_already))
            } else {
                tv_detailed_image?.setImageDrawable(getDrawable(R.drawable.renzheng_not))
            }
        }

        //去过的城市
        if (data.haveBeenToCity?.size!! > 0) {
            tv_city_he_visited?.visibility = View.VISIBLE
            flowlayout_city?.visibility = View.VISIBLE
            flowlayout_city?.removeAllViews()
            for (name in data.haveBeenToCity!!) {
                val textView = LayoutInflater.from(this)
                    .inflate(R.layout.item_flow, flowlayout_city, false) as TextView
                textView.text = name
                flowlayout_city?.addView(textView)
            }
        } else {
            tv_city_he_visited?.visibility = View.GONE
            flowlayout_city?.visibility = View.GONE
        }

        //他的足迹
        if (data.haveBeenToScenicSpot?.size!! > 0) {
            tv_tas_footprint?.visibility = View.VISIBLE
            flowlayout_footprint?.visibility = View.VISIBLE
            flowlayout_footprint?.removeAllViews()
            for (name in data.haveBeenToScenicSpot!!) {
                val textView = LayoutInflater.from(this)
                    .inflate(R.layout.item_flow, flowlayout_footprint, false) as TextView
                textView.text = name
                flowlayout_footprint?.addView(textView)
            }
        } else {
            tv_tas_footprint?.visibility = View.GONE
            flowlayout_footprint?.visibility = View.GONE
        }
        loadingDialog.showSuccess()
        loadingDialog.dismiss()
        personal_smart.finishRefresh()
    }

    override fun onDetailsDataError() {
        loadingDialog.showFail()
        loadingDialog.dismiss()
        personal_smart.finishRefresh()
    }

    //对外展示旅行计划成功
    override fun onExternalDisplaySuccess(data: ExternalDisplayResponse?) {
        this.totalElements = data?.totalElements!!
        list.clear()
        if (data.content?.size!! > 0) {
            for (item in data.content!!) {
                list.add(item)
            }
        }
        else {
            mAdapter?.setFooterView(getEmptyView(R.layout.no_data_available_layout))
        }
        mAdapter?.notifyDataSetChanged()
        loadingDialog.showSuccess()
        loadingDialog.dismiss()
        personal_smart.finishRefresh()
        personal_smart.finishLoadMore()
    }

    //对外旅行计划失败
    override fun onExternalDisplayError() {
        loadingDialog.showFail()
        loadingDialog.dismiss()
    }

    //上拉刷新
    private var page: Int = 1
    override fun onLoadMore(refreshLayout: RefreshLayout) {
        page++
        mPresenter?.doExternalDisplay(longUserId, page, 10)
        if (totalElements <= 10) {
            personal_smart.setEnableLoadMore(false)
        } else {
            personal_smart.setEnableLoadMore(true)
        }
        personal_smart.finishLoadMore()
    }

    //下拉刷新
    override fun onRefresh(refreshLayout: RefreshLayout) {
        if (state == 1) {//资料
            mPresenter?.doPersonalHomPager(longUserId)//基本资料
//            mPresenter?.doDetailsData(longUserId)
            mAdapter?.notifyDataSetChanged()
        } else if (state == 2) {//计划
            mPresenter?.doExternalDisplay(longUserId, 1, 10)
            mAdapter?.notifyDataSetChanged()
        }
        personal_smart.finishRefresh()
    }

    /*------------关注------------*/
    override fun onFocusOnUsersSuccess(data: Boolean) {
        mPresenter?.doPersonalHomPager(longUserId)
    }

    override fun onFocusOnUsersError() {
        loadingDialog.dismiss()
    }

    /*----------取消关注--------------*/
    override fun onCancelAttentioSuccess(data: Boolean) {
        mPresenter?.doPersonalHomPager(longUserId)
    }

    override fun onCancelAttentioError() {
        loadingDialog.dismiss()
    }

    companion object {
        private const val USERID = "userId"
        fun start(context: Context?, userId: Long) {
            val intent = Intent(context, PersonalHomPagerActivity::class.java)
            intent.putExtra(USERID, userId)
            context?.startActivity(intent)
        }
    }
}