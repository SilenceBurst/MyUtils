package com.sign.myutils;

import android.animation.Animator;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * 圆形展开\关闭动画工具类
 */
public class CircleAnimateUtils {
    public static void handleAnimate(final View animateView) {
        //隐藏
        if (animateView.getVisibility() == View.VISIBLE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                /**
                 * createCircularReveal 方法参数
                 * view 执行动画的view
                 * centerX 圆心横坐标
                 * centerY 圆心纵坐标
                 * startRadius 动画开始时圆的半径
                 * endRadius 动画结束时圆的半径
                 */
                final Animator animatorHide = ViewAnimationUtils.createCircularReveal(animateView,
                        0,
                        0,
                        //确定圆的半径（算长宽的斜边长，这样半径不会太短也不会很长效果比较舒服）
                        (float) Math.hypot(animateView.getWidth(), animateView.getHeight()),
                        0);
                animatorHide.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animateView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animatorHide.setDuration(300);
                animatorHide.start();
            } else {
                animateView.setVisibility(View.GONE);
            }
            animateView.setEnabled(false);
        }
        //显示
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                final Animator animator = ViewAnimationUtils.createCircularReveal(animateView,
                        0,
                        0,
                        0,
                        (float) Math.hypot(animateView.getWidth(), animateView.getHeight()));
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        animateView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animator.setDuration(300);
                animator.start();
            } else {
                animateView.setVisibility(View.VISIBLE);
            }
            animateView.setEnabled(true);
        }
    }
}
