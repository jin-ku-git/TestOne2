package com.qw.adse.base;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.qw.adse.R;
import com.qw.adse.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;



public abstract class BaseActivity extends SwipeBackActivity {
    Logger logger=Logger.getLogger(BaseActivity.class.getSimpleName());
    public static ArrayList<Activity> mActivityList = new ArrayList<Activity>();
    protected BaseActivity mActivity;
    public BaseFragment curFragment;
    protected Context mContext;


    private SwipeBackLayout mSwipeBackLayout;


    @SuppressWarnings("unchecked")
    protected <T extends View> T $(@IdRes int id) {
        return (T) super.findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mContext = this;
        setTranslucent(this);

        ViewManager.getInstance().addActivity(this);


        setSwipeBackEnable(true);

        mSwipeBackLayout = getSwipeBackLayout();


        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);


        mSwipeBackLayout.setEdgeSize(150);


        StatusBarUtils.setRootViewFitsSystemWindows(this, true);

        if (!StatusBarUtils.setStatusBarDarkTheme(this, true)) {

            StatusBarUtils.setStatusBarColor(this, R.color.white);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(getWindow()
                    .getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            getWindow().getDecorView().setSystemUiVisibility(getWindow()
                    .getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }

        addActivity(mActivity);

    }

    public static void setTranslucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    protected void setNavigationColor(@ColorInt int color)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            mActivity.getWindow().setNavigationBarColor(color);
        }
    }

    public static void addActivity(Activity activity){
        //判断集合中是否已经添加，添加过的则不再添加
        if (!mActivityList.contains(activity)){
            mActivityList.add(activity);
        }
    }


    public static void removeActivity(Activity activity){
        mActivityList.remove(activity);
    }


    public static void finishAllActivity(){
        for (Activity activity : mActivityList){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



    protected void popFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment frag : fragments) {
            frag.onActivityResult(requestCode,resultCode,data);
        }

    }




    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "无法获取到版本号";
        }
    }




    public void showToast( String msg) {

        if (isFinish())
            return;
        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
    }



    public void showToast( int resId) {
        if (isFinish())
            return;
        Toast.makeText(BaseActivity.this, resId, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();


    }





    public boolean isFinish(){
        return isFinishing() || isDestroyed();
    }


}
