package com.qw.adse.utils;

import android.content.Context;
import android.content.SharedPreferences;



public class SharedPreferencesUtil {
    private static final String FILE_NAME = "welcomePage";
    public static final String FIRST_OPEN = "first_open";

    public static Boolean getBoolean(Context context, String strKey,
                                     Boolean strDefault){

        SharedPreferences sharedPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE
        );

        Boolean result = sharedPreferences.getBoolean(strKey, strDefault);

        return result;
    }

    public static Boolean getBoolean(Context context, String strKey) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        Boolean result = setPreferences.getBoolean(strKey, false);
        return result;
    }

    public static void setBoolean(Context context, String strKey,
                                  Boolean strData){
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE
        );

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(strKey, strData);
        editor.commit();
    }

    public static String getString(Context context, String strKey) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        String result = setPreferences.getString(strKey, "");
        return result;
    }

    public static String getString(Context context, String strKey,
                                   String strDefault) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        String result = setPreferences.getString(strKey, strDefault);
        return result;
    }

    public static void setString(Context context, String strKey, String strData) {
        SharedPreferences activityPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = activityPreferences.edit();
        editor.putString(strKey, strData);
        editor.commit();
    }

    public static int getInt(Context context, String strKey) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        int result = setPreferences.getInt(strKey, -1);
        return result;
    }

    public static int getInt(Context context, String strKey, int strDefault) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        int result = setPreferences.getInt(strKey, strDefault);
        return result;
    }

    public static void setInt(Context context, String strKey, int strData) {
        SharedPreferences activityPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = activityPreferences.edit();
        editor.putInt(strKey, strData);
        editor.commit();
    }

    public static long getLong(Context context, String strKey) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        long result = setPreferences.getLong(strKey, -1);
        return result;
    }

    public static long getLong(Context context, String strKey, long strDefault) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        long result = setPreferences.getLong(strKey, strDefault);
        return result;
    }

    public static void setLong(Context context, String strKey, long strData) {
        SharedPreferences activityPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = activityPreferences.edit();
        editor.putLong(strKey, strData);
        editor.commit();
    }
}
