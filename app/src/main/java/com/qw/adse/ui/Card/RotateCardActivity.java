package com.qw.adse.ui.Card;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.qw.adse.R;
import com.qw.adse.utils.kapai.Rotatable;
import com.qw.adse.utils.kapai.ViewHelper;



public class RotateCardActivity extends Activity implements View.OnClickListener {
    private static final String LOG_TAG = "RotateCardActivity";

    private RelativeLayout rlCardRoot;
    private ImageView imageViewBack;
    private ImageView imageViewFront;

    private void initView() {
        rlCardRoot = (RelativeLayout) findViewById(R.id.rl_card_root);
        imageViewBack = (ImageView) findViewById(R.id.imageView_back);
        imageViewFront = (ImageView) findViewById(R.id.imageView_front);
        imageViewBack.setOnClickListener(this);
        imageViewFront.setOnClickListener(this);
        setCameraDistance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_card);
        initView();
        initData();
    }

    public void initData() {


        Glide.with(this).load(R.drawable.black_kapai).into(imageViewBack);
        Glide.with(this).load(R.drawable.black_kapai).into(imageViewFront);


        imageViewBack.setVisibility(View.VISIBLE);
        imageViewFront.setVisibility(View.INVISIBLE);
    }



    public void cardTurnover() {
        if (View.VISIBLE == imageViewBack.getVisibility()) {
            ViewHelper.setRotationY(imageViewFront, 180f);
            Rotatable rotatable = new Rotatable.Builder(rlCardRoot)
                    .sides(R.id.imageView_back, R.id.imageView_front)
                    .direction(Rotatable.ROTATE_Y)
                    .rotationCount(1)
                    .build();
            rotatable.setTouchEnable(false);
            rotatable.rotate(Rotatable.ROTATE_Y, -180, 1500);
        } else if (View.VISIBLE == imageViewFront.getVisibility()) {
            Rotatable rotatable = new Rotatable.Builder(rlCardRoot)
                    .sides(R.id.imageView_back, R.id.imageView_front)
                    .direction(Rotatable.ROTATE_Y)
                    .rotationCount(1)
                    .build();
            rotatable.setTouchEnable(false);
            rotatable.rotate(Rotatable.ROTATE_Y, 0, 1500);
        }
    }

    private void setCameraDistance() {
        int distance = 10000;
        float scale = getResources().getDisplayMetrics().density * distance;
        rlCardRoot.setCameraDistance(scale);
    }

    @Override
    public void onClick(View v) {

        int view=v.getId();
        if (view==R.id.imageView_back){
            cardTurnover();
        }else if (view==R.id.imageView_front){
            cardTurnover();
        }

    }
}
