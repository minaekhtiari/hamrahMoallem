package classes.models;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.List;

import javax.xml.validation.Validator;

import static android.content.Context.ACTIVITY_SERVICE;

public class AndroidUtils {

    private Context _context;

    public AndroidUtils(Context context) {
        _context = context;
    }

    public Integer getAndroidVersionCode() {

        return Build.VERSION.SDK_INT;

    }

    public String getAndroidVersionName() {
        return Build.VERSION.RELEASE;
    }

    public String getUUID() {
        String android_id = Settings.Secure.getString(_context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return android_id;
    }


    public String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public String getDpi() {
        return String.valueOf(_context.getResources().getDisplayMetrics().densityDpi);
    }

    public String getResolution() {

        DisplayMetrics display = _context.getResources().getDisplayMetrics();
        return String.format("%d*%d", display.heightPixels, display.widthPixels);


    }

    public String getModelName() {
        return Build.MODEL;
    }

    public String getBrandName() {
        return Build.BRAND;
    }

    public String getNetworkType() {

        if (!networkisConnected()) {

            return null;
        }

        ConnectivityManager cm = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null)  // connected to the internet
            return null;

        if(activeNetwork.getType()== ConnectivityManager.TYPE_WIFI){
            return "Wifi";

        }else {
            return activeNetwork.getSubtypeName();

        }
//            switch (networkType) {
//                case ConnectivityManager.:
//                    return "GPRS";
//                case TelephonyManager.NETWORK_TYPE_EDGE:
//                    return "EDGE";
//                case TelephonyManager.NETWORK_TYPE_IDEN:
//                    return "2G";
//                case TelephonyManager.NETWORK_TYPE_HSPAP:
//                    return "3G";
//                case TelephonyManager.NETWORK_TYPE_LTE:
//                    return "4G";
//                default:
//                    return "Wifi";
//            }

    }

    public String getNetworkSSID () {
//
//        final WifiManager wifiManager = (WifiManager) _context.getSystemService(Context.WIFI_SERVICE);
//        final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
//        if (connectionInfo != null && !Validator.stringIsNullOrEmpty(connectionInfo.getSSID())) {
//            return connectionInfo.getSSID().replace("\"", "");
//        }
       return null;

    }

    private boolean networkisConnected () {
        ConnectivityManager cm = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected()) {
            return false;
        }
        return true;
    }


    public String getHardware () {
        return Build.HARDWARE;
    }

    public String getCpu () {
        return String.format("%s|%d", System.getProperty("os.arch"), Runtime.getRuntime().availableProcessors());
    }

    public String getRam () {


        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) _context.getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        return String.valueOf(Math.round((mi.totalMem / 1048576L) / 1024));
    }


    public List<String> getInstalledAppsList () {
        final PackageManager pm = _context.getPackageManager();
        //get a list of installed apps.
        List<ApplicationInfo> applicationInfos = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        List<String> packages = new ArrayList<>();

        for (ApplicationInfo packageInfo : applicationInfos) {

            if ((packageInfo.flags & (ApplicationInfo.FLAG_UPDATED_SYSTEM_APP | ApplicationInfo.FLAG_SYSTEM)) > 0) {
                //system app
            } else {
                packages.add(packageInfo.packageName);
            }
        }
        return packages;
    }


    public String getSimOperator () {
        TelephonyManager manager = (TelephonyManager) _context.getSystemService(Context.TELEPHONY_SERVICE);
        String carrierName = manager.getNetworkOperatorName();
        if (carrierName == null)
            carrierName = "";
        return carrierName;
    }


}
