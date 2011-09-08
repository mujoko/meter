/**
 * HRPMobile Application.
 */
package com.rbtsb.meter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.Preference.OnPreferenceChangeListener;
import android.util.Log;
/**
 * This activity is used to show main preferences or settings page.
 * @author asad
 */
public class PreferencesActivity extends PreferenceActivity implements
		OnSharedPreferenceChangeListener {

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		// TODO Auto-generated method stub
		
	}
//	/** prefAppServer */
//	private EditTextPreference prefAppServer;
//	/** prefAppServerPort */
//	private EditTextPreference prefAppServerPort;
//    /** Called when the activity is first created.
//     *@param savedInstanceState.
//     */
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		addPreferencesFromResource(R.xml.preferences);
//
//		prefAppServer = (EditTextPreference) findPreference(getString(R.string.prefAppServer));
//		prefAppServerPort = (EditTextPreference) findPreference(getString(R.string.prefAppServerPort));
//
//		// load default preferences
//		setDefaultPreferences();
//		Log.i(AppConstants.APPLICATION_TAG, "Server1: "
//				+ AppPreferences.prefAppServer);
//
//		prefAppServer
//				.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
//					public boolean onPreferenceChange(Preference preference,
//							Object newValue) {
//						return validatePreferences(preference, newValue);
//					}
//				});
//
//		prefAppServerPort
//				.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
//					public boolean onPreferenceChange(Preference preference,
//							Object newValue) {
//						return validatePreferences(preference, newValue);
//					}
//				});
//
//	}
//    /** setDefaultPreferences method.
//     */
//	private void setDefaultPreferences() {
//
//		AppUtilies.loadPreferences(this);
//		// set the preferences
//		prefAppServer.setSummary(AppPreferences.prefAppServer);
//		prefAppServerPort.setSummary(AppPreferences.prefAppServerPort);
//
//	}
//    /** validatePreferences method.
//     * @param preference.
//     * @param newValue.
//     * @return boolean.
//     */
//	private boolean validatePreferences(Preference preference, Object newValue) {
//		Log.i("APP_PREFS", "Preference : " + preference.getKey()
//				+ " - Chnaged to: " + newValue.toString());
//		Log.d(AppConstants.APPLICATION_TAG, "Saving preferences");
//		SharedPreferences.Editor editor = getSharedPreferences(
//				AppConstants.HRP_MOBILE_PREFERENCES, Activity.MODE_PRIVATE)
//				.edit();
//		editor.putString(preference.getKey(), newValue.toString());
//		editor.commit();
//
//		preference.setSummary(newValue.toString());
//		if (preference.getKey().equalsIgnoreCase(
//				getString(R.string.prefAppServer)))
//			AppPreferences.prefAppServer = newValue.toString();
//		if (preference.getKey().equalsIgnoreCase(
//				getString(R.string.prefAppServerPort)))
//			AppPreferences.prefAppServerPort = newValue.toString();
//
//		return true;
//	}
//    /** onSharedPreferenceChanged method.
//     * @param sharedPreferences.
//     * @param key.
//     */
//	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
//			String key) {
//		Log.i("APP_SHARED_PREFS", "onSharedPreferenceChanged on key: " + key);
//		// SharedPreferences.Editor editor = sharedPreferences.edit();
//	}

}