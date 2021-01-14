package com.miaofen.xiaoying.activity.details.replycomm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.mvp.BaseMvpBottomDialogFragment

/**
 * 项目名称：com.miaofen.xiaoying.activity.details.replycomm
 * 类描述：一级回复评论列表
 * 创建人：duzepeng
 * 创建时间：2021/1/8
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class ReplyDialog(var commentId: Long?) :
    BaseMvpBottomDialogFragment<ReplyCommContract.Presenter>(), ReplyCommContract.View {

    private var frView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        ReplyCommPresenter(this)
        mPresenter?.doReplyComm(commentId!!)
        // 去掉默认的标题
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)//reply_dialog_layout
        frView = inflater.inflate(R.layout.dialog_comment_fr_bottom, null)
        return frView
    }

    //列表请求成功
    override fun onReplyCommSuccess(data: String?) {

    }

    //列表请求失败
    override fun onReplyCommError() {

    }


}