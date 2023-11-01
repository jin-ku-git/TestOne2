package com.qw.adse.utils;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.qw.adse.utils.interfaces.ImageStateInterface;


public class CountDownTimerUtils {
    @SuppressLint("StaticFieldLeak")
    private static CountDownTimer timer;


    public static void getTimer(int second, final View view, final String defaultText, final ImageStateInterface imageStateInterface) {
        timer = new CountDownTimer(second * 1000 + 1500, 1000) {
            @SuppressLint("DefaultLocale")
            @Override
            public void onTick(long millisUntilFinished) {
                long remainderTime = millisUntilFinished / 1000 - 1;

                if (view instanceof TextView) {
                    ((TextView) view).setText(String.format("%d", remainderTime));
                }
                if (remainderTime == 0) {

                    onFinish();
                    cancel();
                }
            }

            @Override
            public void onFinish() {
                view.setClickable(true);
                if (view instanceof TextView) {
                    ((TextView) view).setText(defaultText);
                }
                imageStateInterface.OnEnd();
            }
        };

        timer.start();

        view.setClickable(false);
    }


    public static void cancelTimer() {
        if (timer != null) {
            timer.cancel();
            timer.onFinish();
        }
    }

}