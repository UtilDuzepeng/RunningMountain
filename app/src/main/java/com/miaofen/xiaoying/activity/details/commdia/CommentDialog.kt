package com.miaofen.xiaoying.activity.details.commdia

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.mvp.BaseMvpBottomDialogFragment
import com.miaofen.xiaoying.utils.ToastUtils


/**
 * 项目名称：com.miaofen.xiaoying.activity.details.commdia
 * 类描述：评论底部弹窗
 * 创建人：duzepeng
 * 创建时间：2020/12/27
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class CommentDialog(var activity: Activity, var planId: Int?) :
    BaseMvpBottomDialogFragment<CommentContract.Presenter>(),
    CommentContract.View {
    private var frView: View? = null

    private var releaseText: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // 去掉默认的标题
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        frView = inflater.inflate(R.layout.dialog_comment_fr_bottom, null)
        CommentPresenter(this)
        releaseText = frView?.findViewById<EditText>(R.id.release_text)
        //发布点击事件
        frView?.findViewById<TextView>(R.id.tv_hairComment)
            ?.setOnClickListener {
                mPresenter?.doCommentDialog(planId!!, releaseText!!.text.toString())
                showInput?.editTextHintInput()
            }
        showInput?.editTextShowInput(releaseText)

        frView?.findViewById<ImageView>(R.id.image_close_comments)?.setOnClickListener {
            showInput?.closeCommentDialog(releaseText)
        }

        return frView
    }


    override fun onCommentSuccess(data: String?) {
        showInput?.loadAnimation(releaseText)
    }

    override fun onCommentError() {
        showInput?.hideAnimation()
    }

    interface ShowInput {
        fun editTextShowInput(editText: EditText?)
        fun editTextHintInput()
        fun loadAnimation(editText: EditText?)
        fun hideAnimation()
        fun closeCommentDialog(editText: EditText?)
    }

    private var showInput: ShowInput? = null

    fun setShowInput(showInput: ShowInput?) {
        this.showInput = showInput
    }


}