/**
 * HRP Mobile Application.
 */
package com.rbtsb.meter;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
/**
 * This activity is used to show login page.
 * @author asad
 */
public class LoginActivity extends Activity {
	/** alertBuilder */
	Builder alertBuilder = null;
	/** progressDialog */
	ProgressDialog progressDialog;
	/** loginButton */
	private Button loginButton;
	/** loginUsername */
	private EditText loginUsername;
	/** loginPassword */
	private EditText loginPassword;
	/** setttingIntent */
	private Intent setttingIntent = null;
	/** aboutIntent */
	private Intent aboutIntent = null;
	/** response */
	String response = null;

	/** 
	 * Called when the activity is first created.
	 * @param savedInstanceState. 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hrp_login);

		setupLogin();
	}
	/** 
	 * setupLogin method. 
	 */
	private void setupLogin() {

		loginUsername = (EditText)findViewById(R.id.login_username);
		loginPassword = (EditText)findViewById(R.id.login_password);

		loginButton = (Button)findViewById(R.id.loginButton);

		loginButton.setOnTouchListener(new OnTouchListener() {

			//			@Override
			public boolean onTouch(View view, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					loginButton.requestFocus();
					authenticateWithServer();
					return true;
				}
				return false;
			}
		});
	}
	/** 
	 * authenticateWithHRP method. 
	 */	
	private void authenticateWithServer() {
		final String strUsername = loginUsername.getText().toString();
		final String strPassword = loginPassword.getText().toString();
		final String strImeiNumber = "";

		if(strUsername != null  && strUsername.length() == 0) {
			showAlertDialog("Please provide username", R.drawable.stop);
			loginUsername.requestFocus();
			return;
		}

		if(strPassword != null  && strPassword.length() == 0) {
			showAlertDialog("Please provide Password", R.drawable.stop);
			loginPassword.requestFocus();
			return;
		}

		progressDialog = ProgressDialog.show(this, "Working..", "Getting data from server, please wait", true, true);
		progressDialog.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				showAuthenticationResult();
			}

		});

		new Thread() {
			@Override
			public void run() {
				Looper.prepare();
				response = authenticateWithServerSecurity(strUsername, strPassword, strImeiNumber);
				progressDialog.dismiss();
			}
		}.start();

	}

	/** 
	 * showAuthenticationResult method. 
	 */		
	public void showAuthenticationResult(){
		try{
			if (response != null) {
				Log.i("D=====================",response);
				JSONObject jsonObject = new JSONObject(response);
				boolean success = false;
				String reason = "";
				success = jsonObject.getBoolean("success");

				if (!success) {
					// get the reason
					jsonObject = new JSONObject(jsonObject.getString("errors"));
					reason = jsonObject.getString("reason");
					showAlertDialog(reason, R.drawable.stop);

				} else {

					showAlertDialog("Your authenticated with system", R.drawable.ic_dialog_info_c);
					
					
					Intent intent = new Intent(LoginActivity.this, AlarmListActivity.class);
					startActivity(intent);
				}
			} else {
				showAlertDialog("Invalid Server Name/IP Address or Port Nuber. Please re-check your settings.", R.drawable.stop);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/** 
	 * authenticateWithHRPSecurity method. 
	 * @param strUsername.
	 * @param strPassword.
	 * @param strImeiNumber.
	 * @return jsonResponse.
	 */		
	private String authenticateWithServerSecurity(final String strUsername, 
			final String strPassword, final String strImeiNumber) {
		String jsonResponse = null;
		try {
			Log.d("Authentication","About authenticate");
			final String LOGIN_URL = AppUtilies.getServiceURL() + "/j_spring_security_check";
			// setup http request to HRP
			ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("j_username", strUsername));
			params.add(new BasicNameValuePair("j_password", strPassword));
			SessionManager sessionManager = new SessionManager();
			Log.i("Login","Login json response1 : " + LOGIN_URL);
			jsonResponse = sessionManager.processPostRequest(LOGIN_URL, params);
			AppConstants.loggedInUser = strUsername;
			Log.i("Login","Login json response : " + jsonResponse);


		} catch (Exception e) {

		}
		return jsonResponse;
	}

	/** 
	 * showAlertDialog method. 
	 * @param errorMessage.
	 * @param icon.
	 */		
	private void showAlertDialog(String errorMessage, int icon) {
		if(alertBuilder == null) {
			alertBuilder = new AlertDialog.Builder(this);
		}
		alertBuilder.setTitle(R.string.app_name);  
		alertBuilder.setMessage(errorMessage); 
		alertBuilder.setPositiveButton("ok", null); 
		if(icon != -1 ) 
			alertBuilder.setIcon(icon);
		alertBuilder.show();
	}
	/**
	 * onCreateOptionsMenu method
	 * @param menu.
	 * @return boolean.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, MenuConstants.SETTING_MENU_ID, Menu.NONE, R.string.settings)
		.setIcon(R.drawable.settings)
		.setAlphabeticShortcut('s');
		menu.add(Menu.NONE, MenuConstants.ABOUT_MENU_ID, Menu.NONE, R.string.about)
		.setIcon(R.drawable.about)
		.setAlphabeticShortcut('a');
		menu.add(Menu.NONE, MenuConstants.EXIT_MENU_ID, Menu.NONE, R.string.exit)
		.setIcon(R.drawable.exit)
		.setAlphabeticShortcut('e');
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * onOptionsItemSelected method
	 * @param item.
	 * @return boolean.
	 */		
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MenuConstants.SETTING_MENU_ID:
			preferences();
			return (true);
		case MenuConstants.ABOUT_MENU_ID:
			about();
			return (true);
		case MenuConstants.EXIT_MENU_ID:
			finish();
			return (true);
		}
		return (super.onOptionsItemSelected(item));
	}


	/**
	 * preferences method.
	 */
	private void preferences() {
		//		if (setttingIntent == null ) {
		//			setttingIntent = new Intent(this, PreferencesActivity.class);
		//		}
		//		startActivity(setttingIntent);
	}

	/**
	 * about method.
	 */
	private void about() {
		//		if (aboutIntent == null ) {
		//			aboutIntent = new Intent(this, AboutActivity.class);
		//		}
		//		startActivity(aboutIntent);
	}	
}
