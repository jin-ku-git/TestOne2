package com.qw.adse;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.qw.adse.base.BaseActivity;
import com.qw.adse.ui.fragment.OneFragment;
import com.qw.adse.ui.fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

public class MainActivity extends BaseActivity {


    private List<Fragment> mFragments;

    PageNavigationView pager_bottom_tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.Base_Theme_TestOne);

        setContentView(R.layout.activity_main);

        pager_bottom_tab=$(R.id.pager_bottom_tab);

        initFragment();

        initBottomTab();
    }

    private void initFragment() {
        mFragments = new ArrayList<>();

        mFragments.add(new OneFragment());
        mFragments.add(new TwoFragment());


        commitAllowingStateLoss(0);

    }

    private void initBottomTab() {
        NavigationController navigationController = pager_bottom_tab.material()
                .addItem(R.mipmap.main_zhuanpan, "toca-discos", Color.parseColor("#FFDE43"))
                .addItem(R.mipmap.gengduo, "mais",Color.parseColor("#FFDE43"))
                .build();

        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {

                commitAllowingStateLoss(index);


            }

            @Override
            public void onRepeat(int index) {
            }
        });
    }


    private void AllFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < mFragments.size(); i++) {
            Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(i + "");
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
        }
        transaction.commitAllowingStateLoss();
    }
    private void commitAllowingStateLoss(int position) {
        AllFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(position + "");
        if (currentFragment != null) {
            transaction.show(currentFragment);
        } else {
            currentFragment = mFragments.get(position);
            transaction.add(R.id.frameLayout, currentFragment, position + "");
        }
        transaction.commitAllowingStateLoss();
    }

}