/**
 * HRPMobile Application.
 */
package com.rbtsb.meter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;
/**
 * This class is used to load the settings for the application.
 * @author asad
 */
public class AppUtilies {

	/**
	 * loadPreferences method.
	 * @param activity
	 */
	public static void loadPreferences(Activity activity) {
		
		SharedPreferences appSharedPrefs = activity.getSharedPreferences(
		AppConstants.HRP_MOBILE_PREFERENCES, Activity.MODE_PRIVATE);
		Log.i(AppConstants.APPLICATION_TAG, "App server1: " + appSharedPrefs.getString(activity.getString(R.string.prefAppServer), null));
		AppPreferences.prefAppServer = appSharedPrefs.getString(activity.getString(R.string.prefAppServer), AppPreferences.prefAppServer);
		AppPreferences.prefAppServerPort = appSharedPrefs.getString(activity.getString(R.string.prefAppServerPort), AppPreferences.prefAppServerPort);
		
		return;
	}
	
	/**
	 * showToast method.
	 * @param activity.
	 * @param text.
	 * @param duration.
	 */
	public static void showToast(Activity activity, String text, int duration) {
		Toast.makeText(activity, text, duration).show();
	}
	
	/**
	 * getServiceURL method.
	 * @param host.
	 * @param port.
	 * @return String.
	 */
	public static String getServiceURL() {
		return "http://" + AppPreferences.prefAppServer + ":" + AppPreferences.prefAppServerPort + AppConstants.HRP_CONTEXT_URL;
	}
}
