package com.qw.adse.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * author:chenjs
 */
public class PermissionUtil {
    private static final String TAG=PermissionUtil.class.getSimpleName();
    private static final boolean LOG_FLAG=true;


    private static final String[] Group_Calendar={
            Manifest.permission.READ_CALENDAR,Manifest.permission.WRITE_CALENDAR
    };

    private static final String[] Group_Camera={
            Manifest.permission.CAMERA
    };

    private static final String[] Group_Contacts={
            Manifest.permission.WRITE_CONTACTS,Manifest.permission.GET_ACCOUNTS,
            Manifest.permission.READ_CONTACTS
    };

    private static final String[] Group_Location={
            Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private static final String[] Group_Microphone={
            Manifest.permission.RECORD_AUDIO
    };

    private static final String[] Group_Phone={
            Manifest.permission.READ_PHONE_STATE,Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CALL_LOG,Manifest.permission.WRITE_CALL_LOG,
            Manifest.permission.ADD_VOICEMAIL,Manifest.permission.USE_SIP,
            Manifest.permission.PROCESS_OUTGOING_CALLS
    };

    private static final String[] Group_Sensors={
            Manifest.permission.BODY_SENSORS
    };

    private static final String[] Group_Sms={
            Manifest.permission.READ_SMS,Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS,Manifest.permission.RECEIVE_MMS,
            Manifest.permission.RECEIVE_WAP_PUSH
    };

    private static final String[] Group_Storage={
            Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static Map<String,String[]> m_PermissionGroupList=null;
    static{
        initMap();
    }


    public static void requestByGroupName(Activity context, String permissionGroupName,int 	requestCode,OnPermissionsListener listener){
        requestByGroupName(context, new String[]{permissionGroupName}, requestCode, listener);
    }


    public static void requestByGroupName(Activity context, String[] pgNameArray,int 	requestCode,OnPermissionsListener listener){
        showLog("requestByPermissionGroup");
        try{

            if(Build.VERSION.SDK_INT>=23 && pgNameArray!=null){
                String[] permissionsList=getAppPermissionsList(context);
                ArrayList<String> targetList=new ArrayList<>();
                if(permissionsList==null || permissionsList.length==0){
                    showLog("A lista de permissões get está vazia");
                    return;
                }

                for(String groupName:pgNameArray){
                    ArrayList<String> tmpPermissionList=isPermissionDeclared(permissionsList,groupName);
                    if(tmpPermissionList==null){
                        showLog("Não encontrado["+groupName+"]Permissões");
                        continue;
                    }

                    for(int i=0;i<tmpPermissionList.size();i++){

                        int nRet=ContextCompat.checkSelfPermission(context,tmpPermissionList.get(i));
                        if(nRet!= PackageManager.PERMISSION_GRANTED){
                            targetList.add(tmpPermissionList.get(i));
                        }
                    }
                }

                if(targetList.size()>0){
                    showLog("Faça as seguintes solicitações de permissão:"+targetList.toString());
                    String[] sList=targetList.toArray(new String[0]);
                    ActivityCompat.requestPermissions(context,sList,requestCode);
                }
                else{
                    showLog("Todas as permissões foram concedidas");
                    if(listener!=null){
                        listener.onPermissionsOwned();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param context
     * @param permission
     * @param requestCode
     * @param listener
     */
    public static void requestByPermissionName(Activity context, String permission,int 	requestCode,OnPermissionsListener listener){
        requestByPermissionName(context, new String[]{permission}, requestCode, listener);
    }


    public static void requestByPermissionName(Activity context, String[] permissionArray,int 	requestCode,OnPermissionsListener listener){
        showLog("requestPermissions");
        try{

            if(Build.VERSION.SDK_INT>=23 && permissionArray!=null){
                ArrayList<String> targetList=new ArrayList<>();
                for(String strPermission:permissionArray){

                    int nRet=ContextCompat.checkSelfPermission(context,strPermission);
                    if(nRet!= PackageManager.PERMISSION_GRANTED){
                        targetList.add(strPermission);
                    }
                }

                if(targetList.size()>0){
                    showLog("Faça as seguintes solicitações de permissão:"+targetList.toString());
                    String[] sList=targetList.toArray(new String[0]);
                    ActivityCompat.requestPermissions(context,sList,requestCode);
                }
                else{
                    showLog("Todas as permissões foram concedidas");
                    if(listener!=null){
                        listener.onPermissionsOwned();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void onRequestPermissionsResult(Activity context,String[] permissions, int[] 	grantResults,OnPermissionsListener listener,boolean controlFlag) {
        try{
            ArrayList<String> requestList=new ArrayList<>();
            ArrayList<String> banList=new ArrayList<>();
            for(int i=0;i<permissions.length;i++){
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    showLog("["+permissions[i]+"]A autorização de permissão é bem-sucedida");
                }
                else{
                    boolean nRet=ActivityCompat.shouldShowRequestPermissionRationale(context,permissions[i]);
                    //Log.i(TAG,"shouldShowRequestPermissionRationale nRet="+nRet);
                    if(nRet){
                        requestList.add(permissions[i]);
                    }
                    else{
                        banList.add(permissions[i]);
                    }
                }
            }

            do{

                if(banList.size()>0){
                    if(listener!=null){
                        listener.onPermissionsForbidden(permissions,grantResults,banList);
                    }
                    if(!controlFlag){
                        break;
                    }
                }
                if(requestList.size()>0){
                    if(listener!=null){
                        listener.onPermissionsDenied(permissions,grantResults,requestList);
                    }
                }
                if(banList.size()==0 && requestList.size()==0){
                    showLog("A autorização de permissão é bem-sucedida");
                    if(listener!=null){
                        listener.onPermissionsSucceed();
                    }
                }
            }while (false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static boolean checkPermission(Context context,String permission){
        try{

            if(Build.VERSION.SDK_INT>=23){
                int nRet= ContextCompat.checkSelfPermission(context,permission);
                showLog("checkSelfPermission nRet="+nRet);

                return nRet==PackageManager.PERMISSION_GRANTED? true : false;
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public static String[] getAppPermissionsList(Context context){
        try{
            PackageManager packageManager = context.getApplicationContext().getPackageManager();
            String packageName=context.getApplicationContext().getPackageName();
            String[] array = packageManager.getPackageInfo(packageName,PackageManager.GET_PERMISSIONS).requestedPermissions;
            return array;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static ArrayList<String> isPermissionDeclared(String[] permissionList, String permissionGroup){
        try{
            if(permissionList!=null && permissionGroup!=null){
                String[] pmGroup=m_PermissionGroupList.get(permissionGroup);
                if(pmGroup!=null){
                    ArrayList<String> arrayList=new ArrayList<>();

                    for(int i=0;i<pmGroup.length;i++){
                        String strPermission=pmGroup[i];
                        for(int j=0;j< permissionList.length;j++){
                            if(strPermission.equals(permissionList[j])){
                                arrayList.add(strPermission);
                                break;
                            }
                        }
                    }
                    if(arrayList.size()==0){
                        return null;
                    }
                    return arrayList;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static void initMap(){
        if(m_PermissionGroupList==null){
            m_PermissionGroupList=new HashMap<>();
            m_PermissionGroupList.put(Manifest.permission_group.CALENDAR,Group_Calendar);
            m_PermissionGroupList.put(Manifest.permission_group.CAMERA,Group_Camera);
            m_PermissionGroupList.put(Manifest.permission_group.CONTACTS,Group_Contacts);
            m_PermissionGroupList.put(Manifest.permission_group.LOCATION,Group_Location);
            m_PermissionGroupList.put(Manifest.permission_group.MICROPHONE,Group_Microphone);
            m_PermissionGroupList.put(Manifest.permission_group.PHONE,Group_Phone);
            m_PermissionGroupList.put(Manifest.permission_group.SENSORS,Group_Sensors);
            m_PermissionGroupList.put(Manifest.permission_group.SMS,Group_Sms);
            m_PermissionGroupList.put(Manifest.permission_group.STORAGE,Group_Storage);
        }
    }

    private static void showLog(String str){
        if(LOG_FLAG){
            Log.i(TAG,str);
        }
    }

    public interface OnPermissionsListener {

        void onPermissionsOwned();

        void onPermissionsForbidden(String[] permissions, int[] grantResults,ArrayList<String> pmList);

        void onPermissionsDenied(String[] permissions, int[] grantResults,ArrayList<String> pmList);

        void onPermissionsSucceed();
    }
}
