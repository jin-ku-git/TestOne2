package com.qw.adse.ui.answer;

import android.animation.AnimatorSet;
import android.app.Dialog;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qw.adse.R;
import com.qw.adse.base.BaseActivity;
import com.qw.adse.databinding.ActivityAnswerBinding;
import com.qw.adse.utils.AnimationUtils;
import com.qw.adse.utils.CountDownTimerUtils;
import com.qw.adse.utils.StatusBarUtil;
import com.qw.adse.utils.interfaces.ImageStateInterface;

import java.util.Random;
import java.util.Timer;

public class AnswerActivity extends BaseActivity implements View.OnClickListener {

    ActivityAnswerBinding binding;

    Timer timer = null;

    AnimatorSet animatorSet = new AnimatorSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAnswerBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        StatusBarUtil.setTransparentForWindow(this);
        StatusBarUtil.setLightMode(this);
        initOnClick();
    }

    private void initOnClick() {
        binding.ivFanhui.setOnClickListener(this);
        binding.slScale.setOnClickListener(this);






        binding.ivAnswer.setOnTouchListener(new View.OnTouchListener() {

            private int TOUCH_MAX = 50;

            private int mLastMotionX;
            private int mLastMotionY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int x = (int) event.getX();
                int y = (int) event.getY();

                if(event.getAction()==MotionEvent.ACTION_DOWN){

                    AnimationUtils.startAnim(animatorSet,binding.ivAnswer);


                    handler.removeCallbacks(r);
                    mLastMotionX = x;
                    mLastMotionY = y;

                    handler.postDelayed(r, 3000);
                }

                if(event.getAction()==MotionEvent.ACTION_UP){

                    AnimationUtils.endAnim(animatorSet,binding.ivAnswer);

                    handler.removeCallbacks(r);
                }

                if(event.getAction()==MotionEvent.ACTION_MOVE){
                    if (Math.abs(mLastMotionX - x) > TOUCH_MAX
                            || Math.abs(mLastMotionY - y) > TOUCH_MAX) {

                        handler.removeCallbacks(r);
                    }
                }
                return false;
            }
            private Runnable r = new Runnable() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(0);
                }
            };
        });


    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what){
                case 0:


                    CountDownTimerUtils.getTimer(3,binding.tvTime,"",imageStateInterface);
                    break;
                default:break;
            }
        };
    };
    ImageStateInterface imageStateInterface =new ImageStateInterface() {
        @Override
        public void OnEnd() {
            showAnswerDialog();
        }
    };



    private void showAnswerDialog() {

        final Dialog dialog = new Dialog(this, R.style.BottomDialog);


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int widths = size.x;
        int height = size.y;

        View dialogView = LayoutInflater.from(this).inflate(R.layout.pop_answer_dialog, null);

        dialog.setContentView(dialogView);
        ViewGroup.LayoutParams layoutParams = dialogView.getLayoutParams();

//        layoutParams.width = (int) (widths*0.8);
//        layoutParams.height = (int) (height * 0.3);

        dialogView.setLayoutParams(layoutParams);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.setCancelable(true);
        dialog.show();


        TextView tv_answer= dialog.findViewById(R.id.tv_answer);
        TextView tv_again= dialog.findViewById(R.id.tv_again);
        LinearLayout ll_close=dialog.findViewById(R.id.ll_close);
        String[] strings={"Se você decidir, faça","É bom ter a consciência tranquila","É melhor pensar em 10.000 coisas do que fazer uma"};
        int min = 0;
        int max = 3;
        Random random = new Random();
        int num = random.nextInt(max)%(max-min+1) + min;
        tv_answer.setText(strings[num]);
        tv_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }


    @Override
    public void onClick(View v) {
        int view=v.getId();
        if (view==R.id.iv_fanhui){
            finish();
        }else if (view==R.id.sl_scale){

        }

    }
}