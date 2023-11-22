package com.kioskviewexample;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint;
import com.facebook.react.defaults.DefaultReactActivityDelegate;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.kioskview.Immersive;

public class MainActivity extends ReactActivity {

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  @Override
  protected String getMainComponentName() {
    return "KioskViewExample";
  }

  /**
   * Returns the instance of the {@link ReactActivityDelegate}. Here we use a util class {@link
   * DefaultReactActivityDelegate} which allows you to easily enable Fabric and Concurrent React
   * (aka React 18) with two boolean flags.
   */
  @Override
  protected ReactActivityDelegate createReactActivityDelegate() {
    return new DefaultReactActivityDelegate(
			this,
			getMainComponentName(),
			// If you opted-in for the New Architecture, we enable the Fabric Renderer.
			DefaultNewArchitectureEntryPoint.getFabricEnabled());
  }


  @Override
  public void onBackPressed() {
		if( !Immersive.getActive() ) super.onBackPressed();
		else Immersive.fullscreen(getWindow(), MainActivity.this);
  }

  @Override
  protected void onPause() {
		super.onPause();
		if(Immersive.getActive()) Immersive.moveToFront(getTaskId(),(ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
  }

  @Override
  protected void onResume() {
		super.onResume();
		if(Immersive.getActive()) Immersive.fullscreen(getWindow(), MainActivity.this);
  }

  @Override
  protected void onUserLeaveHint() {
		super.onUserLeaveHint();
		if(Immersive.getActive()) {
			Immersive.moveToFront(getTaskId(), (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
			Immersive.fullscreen(getWindow(), MainActivity.this);
		}
  }
}
