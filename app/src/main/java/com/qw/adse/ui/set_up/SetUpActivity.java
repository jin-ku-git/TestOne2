package com.qw.adse.ui.set_up;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.qw.adse.R;
import com.qw.adse.base.BaseActivity;
import com.qw.adse.databinding.ActivitySetUpBinding;
import com.qw.adse.ui.xieyi.XieYiActivity;

public class SetUpActivity extends BaseActivity implements View.OnClickListener {

    ActivitySetUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.ivFanhui.setOnClickListener(this);
        binding.llYinsi.setOnClickListener(this);
        binding.llYonghu.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int view = v.getId();
        if (view==R.id.iv_fanhui){
            finish();
        }else if (view==R.id.ll_yonghu){
            Intent intent = new Intent(this, XieYiActivity.class);
            intent.putExtra("type","1");
            startActivity(intent);
        }else if (view==R.id.ll_yinsi){
            Intent intent = new Intent(this, XieYiActivity.class);
            intent.putExtra("type","2");
            startActivity(intent);
        }
    }
}