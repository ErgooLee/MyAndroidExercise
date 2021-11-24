package com.example.test.view

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.test.DialogActivity
import com.example.test.R
import com.example.test.utils.Colors
import com.example.test.utils.Drawables
import com.example.test.utils.dp
import kotlinx.android.synthetic.main.activity_test_view.*

class TestView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_view)

        println("#FE908765=" + Colors.intColor2String(Color.parseColor("#FE908765")))
        println("#FFFFFFFF=" + Colors.intColor2String(Color.parseColor("#FFFFFFFF")))
        println("#00000000=" + Colors.intColor2String(Color.parseColor("#00000000")))
        println("#FFFFFF=" + Colors.intColor2String(Color.parseColor("#FFFFFF")))
        println("#123456=" + Colors.intColor2String(Color.parseColor("#123456")))


        progressImage.setOnClickListener {
            progressImage.setProgressWithAnim(50)
        }

        changeColor.setOnClickListener {
            progressImage.changeForegroundColor(Color.BLACK)
        }

        iv.setOnClickListener {
            iv.setImageDrawable(
                Drawables.tintDrawable(
                    this,
                    ContextCompat.getDrawable(this, R.drawable.ad_tag_icon),
                    Color.RED
                )
            )
        }

        anim1.setOnClickListener {
            val length = 100.dp
            //Property Animation
            //View Animation(Tween Animation(绘图位置变了，点击区域未变), Frame Animation)
            //改动属性有限
//            if (it.x + length < Resources.getSystem().displayMetrics.widthPixels - it.width) {
//                it.animate().translationX(it.x + length).setDuration(1000).start()
//            } else {
//                it.animate().translationX(it.x - length).setDuration(1000).start()
//            }


//            val keyFrame1 = Keyframe.ofFloat(0f, 0f)
//            val keyframe2 = Keyframe.ofFloat(0.2f, 0.4f * length)
//            val keyframe3 = Keyframe.ofFloat(0.8f, 0.6f * length)
//            val keyframe4 = Keyframe.ofFloat(1f, length)
//            val keyframeHolder = PropertyValuesHolder.ofKeyframe("translationX",
//                    keyFrame1,
//                    keyframe2,
//                    keyframe3,
//                    keyframe4
//                )
//            ObjectAnimator.ofPropertyValuesHolder(anim1, keyframeHolder)
//                .setDuration(2000)
//                .start()

//            val anim = ObjectAnimator.ofFloat(
//                anim1,
//                "translationX",
//                length
//            )
//            anim.start()


            val animator = ObjectAnimator.ofFloat()
            //位移动画 起点终点都在页面中
            animator.interpolator = AccelerateDecelerateInterpolator()
            // 出场动画 终点不在页面中
            animator.interpolator = AccelerateInterpolator()
            // 入场动画 起点不在页面中
            animator.interpolator = DecelerateInterpolator()

            //不常用 匀速场景不多
//            animator.interpolator = LinearInterpolator()


        }

        anim2.setOnClickListener {
            val radius = if (anim2.radius.toFloat() == 30.dp) {
                44.dp
            } else {
                30.dp
            }

            val animator = ObjectAnimator.ofFloat(anim2, "radius", radius)
            animator.duration = 500
            animator.start()
        }

        cameraView.setOnClickListener {
//            val topAnim = ObjectAnimator.ofFloat(cameraView, "topFlip", -60f)
//            topAnim.duration = 1000
//            topAnim.startDelay = 200
//
//            val bottomFlip = ObjectAnimator.ofFloat(cameraView, "bottomFlip", 60f)
//            bottomFlip.duration = 1000
//            bottomFlip.startDelay = 200
//
//
//            val flipRotation = ObjectAnimator.ofFloat(cameraView, "flipRotation", 270f)
//            flipRotation.duration = 1000
//            flipRotation.startDelay = 200
//
//
//            val set = AnimatorSet()
//            set.playSequentially(bottomFlip, flipRotation, topAnim)
//            set.start()

            val bottomHolder = PropertyValuesHolder.ofFloat("bottomFlip", 60f)
            val flipHolder = PropertyValuesHolder.ofFloat("flipRotation", 270f)
            val topHolder = PropertyValuesHolder.ofFloat("topFlip", -60f)
            val holderAnim = ObjectAnimator.ofPropertyValuesHolder(
                cameraView,
                bottomHolder,
                flipHolder,
                topHolder
            )

            holderAnim.duration = 2000
            holderAnim.start()
        }

        pointView.setOnClickListener {
            val animator = ObjectAnimator.ofObject(
                pointView, "point", PointView.PointFEvaluator(),
                PointF(pointView.point.x + 20.dp, 10.dp)
            )
            animator.duration = 1000
            animator.start()
        }

        province.setOnClickListener {
            val animator = ObjectAnimator.ofObject(
                province,
                "province",
                ProvinceView.ProvinceEvaluator(),
                "澳门特别行政区"
            )
            animator.duration = 10000
            animator.start()
        }

        multiply.setBackgroundDrawable(MashDrawable())
//        materialText.postDelayed(
//            { materialText.useFloatingLabel = false },
//            10000)

        click_dlg.setOnClickListener {
//            AlertDialog.Builder(this@TestView)
//                .setTitle("Delete entry")
//                .setMessage("Are you sure you want to delete this entry?") // Specifying a listener allows you to take an action before dismissing the dialog.
//                // The dialog is automatically dismissed when a dialog button is clicked.
//                .setPositiveButton(android.R.string.yes
//                ) { dialog, which -> } // A null listener allows the button to dismiss the dialog and take no further action.
//                .setNegativeButton(android.R.string.no, null)
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .show()
            Intent(this, DialogActivity::class.java).apply {
                startActivity(this)
            }

        }

    }

    override fun onStart() {
        super.onStart()
        Log.d("chao", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("chao", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("chao", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("chao", "onStop")
    }

}
