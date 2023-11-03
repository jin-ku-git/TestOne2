package com.qw.adse.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Looper;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qw.adse.MainActivity;
import com.qw.adse.R;
import com.qw.adse.base.BaseActivity;
import com.qw.adse.databinding.ActivityWelcomeBinding;
import com.qw.adse.ui.xieyi.XieYiActivity;
import com.qw.adse.utils.SharedPreferencesUtil;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity {

    ActivityWelcomeBinding binding;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        boolean isFirstOpen = SharedPreferencesUtil.getBoolean(this, SharedPreferencesUtil.FIRST_OPEN, true);
        if(isFirstOpen){

            showSetDeBugDialog();

            return;
        }

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                Looper.prepare();


                isDate();
                Looper.loop();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 1000);

    }

    private void isDate() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
    }

    Dialog dialog;
    private void showSetDeBugDialog() {
//        AlertDialog.Builder setDeBugDialog = new AlertDialog.Builder(getBaseContext());
        dialog = new Dialog(this, R.style.BottomDialog);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int widths = size.x;
        int height = size.y;


        View dialogView = LayoutInflater.from(this).inflate(R.layout.widget_user_dialog, null);

        dialog.setContentView(dialogView);
        ViewGroup.LayoutParams layoutParams = dialogView.getLayoutParams();
//        layoutParams.width =(int)(widths*1);
//        layoutParams.height=(int)((widths*1));

        dialogView.setLayoutParams(layoutParams);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.setCancelable(false);
        dialog.show();


        TextView tv_dialog_no =  dialogView.findViewById(R.id.tv_dialog_no);
        TextView tv_dialog_ok =  dialogView.findViewById(R.id.tv_dialog_ok);
        AppCompatTextView tv_sprint_content =  dialogView.findViewById(R.id.tv_sprint_content);

        String str ="    Obrigado pelo apoio! A Empresa atribui grande importância à proteção de suas informações pessoais e privacidade. Para melhor proteger seus direitos e interesses pessoais, certifique-se de ler nossos produtos cuidadosamente antes de usá-los 'Política de privacidade' e 'Contrato do Usuário' Em particular, todos os termos:\n" +
                "    1.Nossas regras e condições para coletar, armazenar, usar, fornecer/proteger suas informações pessoais, bem como seus direitos de usuário;\n" +
                "    2. Concordar com nossas cláusulas de limitação de responsabilidade e isenção de responsabilidade;\n" +
                "    3.Outros termos importantes marcados em cores ou negrito。\n" +
                "    Ao clicar em \"Concordar e Continuar\", significa que você leu e concordou com todo o conteúdo do contrato acima. para usar nossos produtos e serviços";
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        ssb.append(str);
        final int start = str.indexOf("'");
        ssb.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                intent=new Intent();
                intent.setClass(WelcomeActivity.this, XieYiActivity.class);
                intent.putExtra("type","2");
                startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.colors_main));
                ds.setUnderlineText(false);
            }
        }, start, start + 25, 0);

        int end = str.lastIndexOf("'");
        ssb.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {

                intent=new Intent();
                intent.setClass(WelcomeActivity.this, XieYiActivity.class);
                intent.putExtra("type","1");
                startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.colors_main));
                ds.setUnderlineText(false);
            }
        }, end - 21, end, 0);

        tv_sprint_content.setMovementMethod(LinkMovementMethod.getInstance());
        tv_sprint_content.setText(ssb, TextView.BufferType.SPANNABLE);



        tv_dialog_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getBaseContext(), MainActivity.class);

                startActivity(intent);
                finish();
            }
        });
        tv_dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), MainActivity.class);

                startActivity(intent);

                finish();
            }
        });
    }

}