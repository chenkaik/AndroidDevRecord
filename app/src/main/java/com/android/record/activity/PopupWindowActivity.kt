package com.android.record.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.record.R
import com.android.record.popupwindow.CommonPopupWindow
import com.android.record.popupwindow.PopupWindowAdapter
import com.android.record.utils.SizeUtil

class PopupWindowActivity : AppCompatActivity(), View.OnClickListener {

    // https://blog.csdn.net/xuzhb_blog/article/details/104321461?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522162840515116780255298768%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=162840515116780255298768&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-1-104321461.first_rank_v2_pc_rank_v29&utm_term=android+%E9%80%9A%E7%94%A8popupwindow&spm=1018.2226.3001.4187
    private var mPopupWindow: CommonPopupWindow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_window)
        findViewById<Button>(R.id.to_bottom_btn1).setOnClickListener(this)
        findViewById<Button>(R.id.to_bottom_btn2).setOnClickListener(this)
        findViewById<Button>(R.id.to_right_btn).setOnClickListener(this)
        findViewById<Button>(R.id.to_left_btn).setOnClickListener(this)
        findViewById<Button>(R.id.full_btn).setOnClickListener(this)
        findViewById<Button>(R.id.to_top_btn).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.to_bottom_btn1 -> {
                showToBottomWindow1(v)
            }
            R.id.to_bottom_btn2 -> {
                showToBottomWindow2(v)
            }
            R.id.to_right_btn -> {
                showToRightWindow(v)
            }
            R.id.to_left_btn -> {
                showToLeftWindow(v)
            }
            R.id.full_btn -> {
                showFullWindow(v)
            }
            R.id.to_top_btn -> {
                showToTopWindow(v)
            }
        }
    }

    //向下弹出
    private fun showToBottomWindow1(view: View) {
        if (mPopupWindow != null && mPopupWindow!!.isShowing) {
            return
        }
        mPopupWindow = CommonPopupWindow.Builder(this)
                .setContentView(R.layout.layout_popup_window_to_bottom1)
                .setViewParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
                .setOutsideTouchable(false)
//            .setAnimationStyle(R.style.AnimScaleTop)
//            .setBackGroundAlpha(0.6f)
                .setOnViewListener { holder, popupWindow ->
                    holder.setOnClickListener(R.id.cancel_btn) {
                        holder.setText(R.id.content_tv, "取消")
                        popupWindow.dismiss()
                    }
                    holder.setOnClickListener(R.id.confirm_btn) {
                        holder.setText(R.id.content_tv, "确定")
                        popupWindow.dismiss()
                    }
//                    holder.setOnClickListener(R.id.outside_view) {
//                        popupWindow.dismiss()
//                    }
                }
                .build()
        mPopupWindow!!.showAsDropDown(view)
    }

    //向下弹出
    private fun showToBottomWindow2(view: View) {
        if (mPopupWindow != null && mPopupWindow!!.isShowing) {
            return
        }
        mPopupWindow = CommonPopupWindow.Builder(this)
                .setContentView(R.layout.layout_popup_window_to_bottom2)
                .setViewParams(
                        view.width,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
                .setOutsideTouchable(false)
                .setAnimationStyle(R.style.AnimScaleTop)
                .setBackGroundAlpha(0.6f)
                .setOnViewListener { holder, popupWindow ->
                    val list = ArrayList<String>()
                    with(list) {
                        add("周一")
                        add("周二")
                        add("周三")
                        add("周四")
                        add("周五")
                        add("周六")
                        add("周日")
                    }
                    val adapter = PopupWindowAdapter(this, list)
                    val popupRv: RecyclerView = holder.getView(R.id.popup_rv)!!
                    popupRv.adapter = adapter
                    adapter.setOnItemClickListener { obj, position ->
//                        showToast("${position + 1}  $obj")
                        Toast.makeText(this, "${position + 1}  $obj", Toast.LENGTH_SHORT).show()
                        popupWindow.dismiss()
                    }
                }
                .build()
        mPopupWindow!!.showAsDropDown(view)
    }

    //向右弹出
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showToRightWindow(view: View) {
        if (mPopupWindow != null && mPopupWindow!!.isShowing) {
            return
        }
        mPopupWindow = ContextCompat.getDrawable(this, R.drawable.shape_corners_10_solid_000000)?.let {
            CommonPopupWindow.Builder(this)
                    .setContentView(R.layout.layout_popup_window_to_left_or_right)
                    .setViewParams(SizeUtil.dp2px(160f).toInt(), SizeUtil.dp2px(50f).toInt())
                    .setBackgroundDrawable(resources.getDrawable(R.drawable.shape_corners_10_solid_000000))
//                .setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.shape_corners_10_solid_000000))
                    .setAnimationStyle(R.style.AnimScaleLeft)
                    .setOnViewListener { holder, popupWindow ->
                        holder.setOnClickListener(R.id.praise_tv) {
                            Toast.makeText(this, "赞", Toast.LENGTH_SHORT).show()
                            popupWindow.dismiss()
                        }
                        holder.setOnClickListener(R.id.comment_tv) {
                            Toast.makeText(this, "评论", Toast.LENGTH_SHORT).show()
                            popupWindow.dismiss()
                        }
                    }.build()
        }
        mPopupWindow!!.showAsDropDown(view, view.width, -(view.height + (mPopupWindow!!.height - view.height) / 2))
    }

    //向左弹出
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showToLeftWindow(view: View) {
        if (mPopupWindow != null && mPopupWindow!!.isShowing) {
            return
        }
        mPopupWindow = CommonPopupWindow.Builder(this)
                .setContentView(R.layout.layout_popup_window_to_left_or_right)
                .setViewParams(SizeUtil.dp2px(160f).toInt(), SizeUtil.dp2px(50f).toInt())
                .setBackgroundDrawable(resources.getDrawable(R.drawable.shape_corners_10_solid_000000))
                .setAnimationStyle(R.style.AnimScaleRight)
                .setOnViewListener { holder, popupWindow ->
                    holder.setOnClickListener(R.id.praise_tv) {
                        Toast.makeText(this, "赞", Toast.LENGTH_SHORT).show()
                        popupWindow.dismiss()
                    }
                    holder.setOnClickListener(R.id.comment_tv) {
                        Toast.makeText(this, "评论", Toast.LENGTH_SHORT).show()
                        popupWindow.dismiss()
                    }
                }.build()
        mPopupWindow!!.showAsDropDown(
                view,
                -mPopupWindow!!.width,
                -(view.height + (mPopupWindow!!.height - view.height) / 2)
        )
    }

    //全屏弹出
    private fun showFullWindow(view: View) {
        if (mPopupWindow != null && mPopupWindow!!.isShowing) {
            return
        }
        mPopupWindow = CommonPopupWindow.Builder(this)
                .setContentView(R.layout.layout_popup_window_full)
                .setViewParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setBackGroundAlpha(0.5f)
                .setAnimationStyle(R.style.AnimTranslateBottom)
                .setOnViewListener { holder, popupWindow ->
                    holder.setOnClickListener(R.id.camera_tv) {
                        Toast.makeText(this, "拍照", Toast.LENGTH_SHORT).show()
                    }
                    holder.setOnClickListener(R.id.gallery_tv) {
                        Toast.makeText(this, "相册", Toast.LENGTH_SHORT).show()
                    }
                    holder.setOnClickListener(R.id.cancel_tv) {
                        Toast.makeText(this, "取消", Toast.LENGTH_SHORT).show()
                        popupWindow.dismiss()
                    }
                }.build()
        mPopupWindow!!.showAtLocation(findViewById(android.R.id.content), Gravity.BOTTOM, 0, 0)
    }

    //向上弹出
    private fun showToTopWindow(view: View) {
        if (mPopupWindow != null && mPopupWindow!!.isShowing) {
            return
        }
        mPopupWindow = CommonPopupWindow.Builder(this)
                .setContentView(R.layout.layout_popup_window_to_top)
                .setAnimationStyle(R.style.AnimScaleBottom)
                .setOnViewListener { holder, popupWindow ->
                    holder.setOnClickListener(R.id.reply_tv) {
                        Toast.makeText(this, "测试", Toast.LENGTH_SHORT).show()
                        popupWindow.dismiss()
                    }
                    holder.setOnClickListener(R.id.share_tv) {
                        Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show()
                        popupWindow.dismiss()
                    }
                    holder.setOnClickListener(R.id.report_tv) {
                        Toast.makeText(this, "举报", Toast.LENGTH_SHORT).show()
                        popupWindow.dismiss()
                    }
                    holder.setOnClickListener(R.id.copy_tv) {
                        Toast.makeText(this, "转发", Toast.LENGTH_SHORT).show()
                        popupWindow.dismiss()
                    }
                }.build()
        mPopupWindow!!.showAsDropDown(
                view,
                -(mPopupWindow!!.contentView.measuredWidth - view.width) / 2,
                -(mPopupWindow!!.contentView.measuredHeight + view.height)
        )
    }

}