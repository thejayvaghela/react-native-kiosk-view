package com.kioskview;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Build;
import android.view.View;
import android.view.Window;

import androidx.annotation.RequiresApi;

public class Immersive {
  private static boolean active = false;
  
  public static boolean getActive() {
    return active;
  }

  public static void setActive(boolean value) {
    active = value;
  }
  
  private static final int UI_FLAG_HIDE_NAV_BAR = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
      | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
      | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
      | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
      | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
      | View.SYSTEM_UI_FLAG_FULLSCREEN
      | View.SYSTEM_UI_FLAG_IMMERSIVE;

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public static void fullscreen(Window window, Activity activity) {
    window.getDecorView().setSystemUiVisibility(UI_FLAG_HIDE_NAV_BAR);
    activity.startLockTask();
    setActive(true);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public static void exitFullscreen(Window window, Activity activity) {
    if (window.getDecorView() != null) {
      View decorView = window.getDecorView();
      int uiOptions = View.SYSTEM_UI_FLAG_VISIBLE;
      decorView.setSystemUiVisibility(uiOptions);
      activity.stopLockTask();
      setActive(false);
    }
  }

  public static void moveToFront(int taskId, ActivityManager activityManager) {
    if (activityManager != null) {
      activityManager.moveTaskToFront(taskId, 0);
    }
  }
}
