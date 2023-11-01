package com.qw.adse.utils;

import me.goldze.mvvmhabit.utils.SPUtils;

public class LocalData {



    public static void saveMainName(String MainName) {
        SPUtils.getInstance().put("MainName", MainName);
    }


    public static String getMainName() {
        return SPUtils.getInstance().getString("MainName");
    }


    public static void saveTheme(String Theme) {
        SPUtils.getInstance().put("Theme", Theme);
    }


    public static String getTheme() {
        return SPUtils.getInstance().getString("Theme");
    }

}
