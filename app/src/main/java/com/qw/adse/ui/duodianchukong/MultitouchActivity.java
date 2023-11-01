package com.qw.adse.ui.duodianchukong;

import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;

import com.qw.adse.R;
import com.qw.adse.base.BaseActivity;
import com.qw.adse.databinding.ActivityMultitouchBinding;
import com.qw.adse.utils.Multitouch.MySurfaceView;
import com.qw.adse.utils.StatusBarUtil;

public class MultitouchActivity extends BaseActivity implements View.OnClickListener {

    ActivityMultitouchBinding binding;

    static float screenHeight;
    static float screenWidth;

    MySurfaceView mySurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMultitouchBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);


        StatusBarUtil.setTransparentForWindow(this);
        StatusBarUtil.setDarkMode(this);

        initOnClick();


        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        screenHeight = metrics.heightPixels;
        screenWidth = metrics.widthPixels;


         mySurfaceView = new MySurfaceView(this);

        binding.relativelayout.addView(mySurfaceView);


//        setContentView(mySurfaceView);

    }

    private void initOnClick() {
        binding.ivFanhui.setOnClickListener(this);
        binding.tvKaishi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int view= v.getId();
        if (view==R.id.iv_fanhui){
            finish();
        }else if (view==R.id.tv_kaishi){
            int id= mySurfaceView.setRefresh();
            if (id!=999){
                showDialog(id+"");
            }

        }

    }

    private void showDialog(String str) {

        final Dialog dialog = new Dialog(this, R.style.BottomDialog);


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int widths = size.x;
        int height = size.y;

        View dialogView = LayoutInflater.from(this).inflate(R.layout.pop_up_dialog, null);

        dialog.setContentView(dialogView);
        ViewGroup.LayoutParams layoutParams = dialogView.getLayoutParams();

        layoutParams.width = (int) (widths*0.8);
        layoutParams.height = (int) (height * 0.3);

        dialogView.setLayoutParams(layoutParams);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.setCancelable(true);
        dialog.show();


        TextView name= dialog.findViewById(R.id.name);
        LinearLayout ll_close=dialog.findViewById(R.id.ll_close);


        name.setText("parabéns"+str+"Número");
        ll_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }


}