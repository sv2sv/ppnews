package com.android.ppnews;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.ObbInfo;
import android.content.res.Resources;
import android.os.Process;
import android.support.v7.widget.TintTypedArray;
import android.util.Log;

import java.util.List;

/**
 * Created by wangyao on 23/2/17.
 */

public class PPDepend {
    protected static PPDepend impl;
    private static boolean setupIsDone;
    private Context appContext;
    private  static Object lock = new Object();

    public static int getColorResource(int grey_600) {
        return impl.getResources().getColor(grey_600);
    }

    public static Resources resources() {
        return impl.getResources();
    }

    public Resources getResources() {
        if(appContext==null){
            throw new NullPointerException("appContext is null");
        }
        return appContext().getResources();
    }



    public Context getAppContext() {
        return this.appContext;
    }

    public static Context appContext() {
        return impl.getAppContext();
    }

    public static <T> T getFragment(PPActivity activity, int i) {
        if (null == activity) {
            throw new NullPointerException("activity is null ");
        }
        return (T) activity.getSupportFragmentManager().findFragmentById(i);
    }

    public static void setup(Context context) {
        if(context==null){
            throw new NullPointerException("context can not be  null");
        }
        synchronized (lock) {
            if (setupIsDone) {
                return;
            }
            Log.i("Setup", String.valueOf(new Object[0]));
            impl = new PPDepend();
            impl.appContext = context.getApplicationContext();
         /*   if (NSApplication.isRunningInFeedbackProcess(context)) {
                impl.setupInternalFeedback();
            } else {
                impl.setupInternal();
            }*/
            setupIsDone = true;
            Log.i("Setup complete.", String.valueOf(new Object[0]));
            //whenSetup.set(null);
        }
    }

    public static String getCurrentProcessName() {
        ActivityManager activityManager = (ActivityManager) impl.appContext.getSystemService(Context.ACTIVITY_SERVICE);
        int myPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == myPid) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        return "";
    }

    public static boolean isSetupDone() {
        return setupIsDone;
    }
}