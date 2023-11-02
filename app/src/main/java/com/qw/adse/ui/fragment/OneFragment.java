package com.qw.adse.ui.fragment;

import static android.app.Activity.RESULT_OK;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.qw.adse.R;
import com.qw.adse.app.Constant;
import com.qw.adse.base.BaseFragment;
import com.qw.adse.databinding.FragmentOneBinding;
import com.qw.adse.ui.addString.AddStringActivity;
import com.qw.adse.utils.zhuanpan.LuckPanAnimEndCallBack;

import java.util.ArrayList;
import java.util.List;



public class OneFragment extends BaseFragment implements View.OnClickListener{


    String[] mItems=new String[7];;
    boolean isEnd = true;
    private FragmentOneBinding binding;

    ActivityResultLauncher launcher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentOneBinding.inflate(inflater, container, false);

        binding.imgStart.setOnClickListener(this::onClick);
        binding.tvXx.setOnClickListener(this::onClick);


        if ("".equals(Constant.MAIN_NAME)){
            binding.tvName.setText("Que frutas comer hoje？");
            mItems[0]="maçã";
            mItems[1]="banana";
            mItems[2]="pitaia";
            mItems[3]="Cerejas";
            mItems[4]="Melancia";
            mItems[5]="quivi";
            mItems[6]="Kivi";

            List<String> list =new ArrayList<>();
            for (int i = 0; i < mItems.length; i++) {
                list.add(mItems[i]);
            }
            Constant.MAIN_THEME=list;
            Constant.MAIN_NAME="Que frutas comer hoje？";
            binding.luckPans.setItems(mItems);
        }else {
            binding.tvName.setText(Constant.MAIN_NAME);
            mItems=new String[Constant.MAIN_THEME.size()];
            for (int i = 0; i < Constant.MAIN_THEME.size(); i++) {
                mItems[i]=Constant.MAIN_THEME.get(i);
            }
            binding.luckPans.setItems(mItems);
        }



        binding.luckPans.setLuckPanAnimEndCallBack(new LuckPanAnimEndCallBack() {
            @Override
            public void onAnimEnd(String str) {

                isEnd=true;

                showDialog(str);
            }
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {

                    if (result.getData()!=null){

                        String name =result.getData().getStringExtra("name");
                        binding.tvName.setText(name);

                        List<String> mList=result.getData().getStringArrayListExtra("data");
                        mItems = new String[mList.size()];
                        for (int i = 0; i < mList.size(); i++) {
                            mItems[i] =mList.get(i);
                        }
                        binding.luckPans.RefreshItems(mItems);

                        Constant.MAIN_THEME=mList;
                        Constant.MAIN_NAME=name;



                    }

                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        int view=v.getId();
        if (view==R.id.img_start){
            isEnd=false;
            binding.luckPans.startAnim();
        }else if (view==R.id.tv_xx){
            if (isEnd==true){

                String name=binding.tvName.getText().toString();

                Intent intent = new Intent(getContext(), AddStringActivity.class);
                intent.putExtra("data",mItems);
                intent.putExtra("name",name);
                launcher.launch(intent);

            }
        }
    }


    private void showDialog(String str) {

        final Dialog dialog = new Dialog(getContext(), R.style.BottomDialog);


        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int widths = size.x;
        int height = size.y;

        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.pop_up_dialog, null);

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


        name.setText(str);
        ll_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }
}