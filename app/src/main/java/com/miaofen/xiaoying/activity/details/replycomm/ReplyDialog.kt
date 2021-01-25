package com.miaofen.xiaoying.activity.details.replycomm

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.activity.details.commdia.CommentDialog
import com.miaofen.xiaoying.base.mvp.BaseMvpBottomDialogFragment
import com.miaofen.xiaoying.common.data.bean.response.SecondaryReplyResponse
import com.miaofen.xiaoying.utils.ToastUtils
import com.miaofen.xiaoying.utils.getCurrentTime
import com.miaofen.xiaoying.utils.hideInput
import com.miaofen.xiaoying.utils.showInput
import com.miaofen.xiaoying.view.LoadingView
import com.miaofen.xiaoying.view.RefreshLayout

/**
 * 项目名称：com.miaofen.xiaoying.activity.details.replycomm
 * 类描述：一级回复评论列表
 * 创建人：duzepeng
 * 创建时间：2021/1/8
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class ReplyDialog(var commentId: Long?, var planId: Int?, var activity: Activity?) :
    BaseMvpBottomDialogFragment<ReplyCommContract.Presenter>(), ReplyCommContract.View,
    CommentDialog.ShowInput, ReplyRecyclerAdapter.SecondaryReply {

    var list = ArrayList<SecondaryReplyResponse.SubPlanCommentListBean?>()

    var hashMap = HashMap<Long, SecondaryReplyResponse.SubPlanCommentListBean?>()

    var mAdapter: ReplyRecyclerAdapter? = null

    private var frView: View? = null

    private var refresh_reply: RefreshLayout? = null

    private var image_Number_Replies: ImageView? = null

    private var tv_Number_Replies: TextView? = null

    private var tv_reply: TextView? = null

    private var image_like: ImageView? = null

    private var image_forward: ImageView? = null

    private var bottomDialogFr: CommentDialog? = null//评论列表弹窗

    private var replyHeaderView: View? = null

    private var imageReplyHeader: ImageView? = null

    private var tvReplyName: TextView? = null

    private var tvReplyContent: TextView? = null

    private var tvReplyFollow: TextView? = null

    private var tvReplyCreateTime: TextView? = null

    private val loadingDialog: LoadingView by lazy {
        LoadingView(activity).apply {
            setTipMsg("正在加载")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        ReplyCommPresenter(this)

        // 去掉默认的标题   secondary_reply_bottom
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)//reply_dialog_layout
        frView = inflater.inflate(R.layout.reply_dialog_layout, null)
        refresh_reply = frView?.findViewById<RefreshLayout>(R.id.refresh_reply)
        image_Number_Replies = frView?.findViewById<ImageView>(R.id.image_Number_Replies)
        tv_Number_Replies = frView?.findViewById<TextView>(R.id.tv_Number_Replies)
        tv_reply = frView?.findViewById<TextView>(R.id.tv_reply)
        image_like = frView?.findViewById<ImageView>(R.id.image_like)
        image_forward = frView?.findViewById<ImageView>(R.id.image_forward)

        //列表头部布局
        replyHeaderView = inflater.inflate(R.layout.reply_header_view, null)
        imageReplyHeader = replyHeaderView?.findViewById<ImageView>(R.id.image_reply_header)
        tvReplyName = replyHeaderView?.findViewById<TextView>(R.id.tv_reply_name)
        tvReplyContent = replyHeaderView?.findViewById<TextView>(R.id.tv_reply_content)
        tvReplyFollow = replyHeaderView?.findViewById<TextView>(R.id.tv_reply_follow)
        tvReplyCreateTime = replyHeaderView?.findViewById<TextView>(R.id.tv_reply_createTime)

        mPresenter?.doReplyComm(commentId!!)
        return frView
    }

    override fun initView() {
        super.initView()
        refresh_reply?.setEnableRefresh(false)
        refresh_reply?.setEnableLoadMore(false)
        mAdapter = ReplyRecyclerAdapter(R.layout.one_reply_item, list, activity,hashMap)
        mAdapter?.setSecondaryReply(this)
        refresh_reply?.recyclerView?.adapter = mAdapter
//        mAdapter?.emptyView =
//            LayoutInflater.from(activity).inflate(R.layout.no_data_available_layout, null, false)

        image_Number_Replies?.setOnClickListener {
            onClickReply?.onNumberReplies()//关闭
        }

        tv_reply?.setOnClickListener {
            //唤起评论
            if (bottomDialogFr == null && activity != null) {
                bottomDialogFr = CommentDialog(activity!!, commentId!!, planId)
                bottomDialogFr?.setShowInput(this)
            }
            bottomDialogFr?.show(fragmentManager!!, "DF")
        }

        image_forward?.setOnClickListener { //转发
            ToastUtils.showToast("转发")
        }

    }

    //列表请求成功
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onReplyCommSuccess(data: SecondaryReplyResponse?) {
        //this.topCommentId = data?.topPlanComment?.commentId!!
        mAdapter?.topCommentId = data?.topPlanComment?.commentId!!

        tv_Number_Replies?.text = "${data?.subPlanCommentList?.size}条回复"
        //标准圆形图片。
        Glide.with(context!!).load(data.topPlanComment?.userInfo?.avatarUrl)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(imageReplyHeader!!)
        //名称
        tvReplyName?.text = data.topPlanComment?.userInfo?.nickName
        //内容
        tvReplyContent?.text = data.topPlanComment?.content
        //显示是否关注
        when (data.topPlanComment?.follow) {
            0 -> {//未关注
                tvReplyFollow?.text = "关注"
                tvReplyFollow?.setOnClickListener {
                    ToastUtils.showToast("关注")
                }
            }
            1 -> {//已关注
                tvReplyFollow?.text = "已关注"
                tvReplyFollow?.setOnClickListener {
                    ToastUtils.showToast("取消关注")
                }
            }
            2 -> {//自己
                tvReplyFollow?.visibility = View.INVISIBLE
            }
            //创建时间
        }
        //创建时间
        if (data.topPlanComment?.createTime != null) {
            tvReplyCreateTime?.text = getCurrentTime(data.topPlanComment?.createTime!!)
        }

        //是否点赞
        if (data.topPlanComment?.star!!) {
            image_like?.setImageDrawable(activity?.getDrawable(R.drawable.dianzan_icon))
            image_like?.setOnClickListener {
                loadingDialog.showLoading()
                mPresenter?.doUnStar(data.topPlanComment?.commentId)
            }
        } else {
            image_like?.setImageDrawable(activity?.getDrawable(R.drawable.dianzan_line))
            image_like?.setOnClickListener {
                loadingDialog.showLoading()
                mPresenter?.doFabulous(data.topPlanComment?.commentId)
            }
        }


        mAdapter?.setHeaderView(replyHeaderView)
        list.clear()
        hashMap.clear()
        for (item in data.subPlanCommentList!!) {
            list.add(item)
            hashMap[item.commentId!!] = item
        }
        mAdapter?.notifyDataSetChanged()
    }

    //列表请求失败
    override fun onReplyCommError() {

    }

    interface OnClickReply {
        fun onNumberReplies()//关闭
    }

    private var onClickReply: OnClickReply? = null

    fun setOnClickReply(onClickReply: OnClickReply?) {
        this.onClickReply = onClickReply
    }

    override fun editTextShowInput(editText: EditText?) {
        showInput(editText!!)
    }

    override fun editTextHintInput() {
        loadingDialog.setTipMsg("正在加载")
        loadingDialog.showFail()
    }

    override fun loadAnimation(editText: EditText?) {
        hideInput(editText, activity)
        bottomDialogFr?.dismiss()
        mPresenter?.doReplyComm(commentId!!)
        loadingDialog.dismiss()
    }

    override fun hideAnimation() {
        loadingDialog.dismiss()
    }

    override fun closeCommentDialog(editText: EditText?) {
        hideInput(editText, activity)
        bottomDialogFr?.dismiss()
    }

    //二级回复
    override fun onReplySecondLevel(commentId: Long) {
        //唤起评论
        if (bottomDialogFr == null && activity != null) {
            bottomDialogFr = CommentDialog(activity!!, commentId, planId)
            bottomDialogFr?.setShowInput(this)
        }
        bottomDialogFr?.show(fragmentManager!!, "DF")
    }

    //删除评论
    override fun onReplyDeleteComments(commentId: Long) {
        loadingDialog.showLoading()
        mPresenter?.doDeleteComment(commentId)
    }

    //删除评论成功
    override fun onDeleteCommentSuccess(data: String?) {
        mPresenter?.doReplyComm(commentId!!)
        loadingDialog.dismiss()
    }

    //删除失败
    override fun onDeleteCommentError() {
        loadingDialog.dismiss()
    }

    //点赞
    override fun onClickFabulous(commentId: Long?) {
        loadingDialog.showLoading()
        mPresenter?.doFabulous(commentId)
    }

    //点赞成功
    override fun onFabulousSuccess(data: String?) {
        mPresenter?.doReplyComm(commentId!!)
        loadingDialog.dismiss()
    }

    //点赞失败
    override fun onFabulousError() {
        loadingDialog.dismiss()
    }

    //取消点赞
    override fun onClickUnStar(commentId: Long?) {
        loadingDialog.showLoading()
        mPresenter?.doUnStar(commentId)
    }

    //取消点赞成功
    override fun onUnStarSuccess(data: String?) {
        mPresenter?.doReplyComm(commentId!!)
        loadingDialog.dismiss()
    }

    //取消点赞失败
    override fun onUnStarError() {
        loadingDialog.dismiss()
    }

    //取消关注成功
    override fun onCancelAttentionSuccess(data: Boolean?) {

    }

    //取消关注失败
    override fun onCancelAttentionError() {

    }


}