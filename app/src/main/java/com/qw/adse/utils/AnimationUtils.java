package com.qw.adse.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class AnimationUtils {



    public static void startAnim(AnimatorSet animatorSet,View view) {

        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(view.getHeight() / 2);
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view, "scaleX", 1, 1.1f).setDuration(100),
                ObjectAnimator.ofFloat(view, "scaleY", 1, 1.1f).setDuration(100));


        animatorSet.start();


    }


    public static void endAnim(AnimatorSet animatorSet,View view) {

        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(view.getHeight() / 2);
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view, "scaleX", 1, 1f).setDuration(100),
                ObjectAnimator.ofFloat(view, "scaleY", 1, 1f).setDuration(100));

        animatorSet.start();

        animatorSet.resume();
    }
}
