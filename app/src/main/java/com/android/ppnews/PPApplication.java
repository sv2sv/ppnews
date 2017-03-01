package com.android.ppnews;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.Stack;

/**
 * Created by wangyao on 20/2/17.
 */

public class PPApplication extends Application {
    private static String TAG = "PPApplication";
    private Stack<Activity> activityStack = new Stack<Activity>();
    private SharedPreferences sp;

    @Override
    public void onCreate() {
        super.onCreate();

        PPDepend.setup(this);
    }


    public void addActivity(Activity activity) {
        if (null == activityStack) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (null == activityStack) {
            activityStack = new Stack<Activity>();
        }
        activityStack.remove(activity);
    }

    public Activity getCurrentActivity() {
        if (null == activityStack) {
            activityStack = new Stack<Activity>();
            return null;
        }
        return activityStack.lastElement();
    }

    public int getCountActivities() {
        if (null == activityStack) {
            activityStack = new Stack<Activity>();
            return 0;
        }
        return activityStack.size();
    }

    public void finishAllActivities() {
        if (null == activityStack) {
            activityStack = new Stack<Activity>();
            return;
        }
        activityStack.clear();
    }

    public void exit() {
        Log.i(TAG, "exit");
        finishAllActivities();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }




    public SharedPreferences getSp() {
        if (null == sp) {
            sp = this.getSharedPreferences("app", Context.MODE_PRIVATE);
        }
        return sp;
    }
}
