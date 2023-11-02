package com.qw.adse.ui.xieyi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.qw.adse.R;
import com.qw.adse.base.BaseActivity;
import com.qw.adse.databinding.ActivitySetUpBinding;
import com.qw.adse.databinding.ActivityXieYiBinding;

public class XieYiActivity extends BaseActivity {

    ActivityXieYiBinding binding;

    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityXieYiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent intent = getIntent();
        type=intent.getStringExtra("type");

        if ("1".equals(type)){
            binding.topName.setText("Contrato do Usuário");
            binding.webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
            binding.webView.loadUrl("file:///android_asset/yonghu.html");
        }else if ("2".equals(type)){
            binding.topName.setText("Acordo de Privacidade");
            binding.webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
            binding.webView.loadUrl("file:///android_asset/yinsi");
        }

        binding.webView.setWebChromeClient(new WebChromeClient());
        binding.webView.setWebViewClient(new WebViewClient());

        binding.webView.getSettings().setJavaScriptEnabled(true);

        binding.webView.getSettings().setSupportZoom(true);

        binding.ivFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}