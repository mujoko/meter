/**
 * HRPMobile Application.
 */
package com.rbtsb.meter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;


/**
 * This activity is used to show the In-progress HRCN List page.
 * @author asad
 */
public class AlarmListActivity extends ListActivity   {
	/** progressDialog */
	private ProgressDialog progressDialog = null;
	/** objects */
	List<Alarm> objects = new ArrayList<Alarm>();
	/** hrcnListAdapter */
	InprogressHRCNListAdapter hrcnListAdapter;
	/** logoutIntent */
	private Intent logoutIntent = null;
//	/** pirIntent */
//    private Intent pirIntent = null;
    
    /**
     * onCreate method
     * @param savedInstanceState.
     */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set content view
		setContentView(R.layout.hrcn_list);
		hrcnListAdapter = new InprogressHRCNListAdapter(this, R.layout.hrcn_list_row, objects);
		setListAdapter(hrcnListAdapter);
		
		progressDialog = ProgressDialog.show(this, "Working..", "Syncronizing inprogress HRCNs, Please wait", true, true);
		
		progressDialog.setOnDismissListener(new OnDismissListener() {
//			@Override
			public void onDismiss(DialogInterface dialog) {
				populateHRCNList();
			}
		});		
		
		new Thread() {
			@Override
			public void run() { 
				try {
					getInprogressHRCNList();
					progressDialog.dismiss();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	/**
	 * sync method.
	 */
	private void sync() {
		progressDialog = ProgressDialog.show(this, "Working..", "Syncronizing inprogress HRCNs, Please wait", true, true);
		new Thread() {
			@Override
			public void run() {
				try {
					getInprogressHRCNList();
					progressDialog.dismiss();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
		
	/**
	 * getInprogressHRCNList method.
	 */	
	private void getInprogressHRCNList() {
		try {
			Log.d("Alarm List","About to list HRCN");
			final String HRCN_URL = AppUtilies.getServiceURL()
			+ "/app/alarms/list?start=0&limit=25";
	
			SessionManager sessionManager = new SessionManager();
			String jsonResponse = sessionManager.processGetRequest(HRCN_URL, null);
			Log.d("Alarm List","json response : " + jsonResponse);
			
			List<Alarm> resultList =  sessionManager.
			generateInprogressHRCNList(jsonResponse);
			
			Set<Alarm> uniqueSet = new LinkedHashSet<Alarm>(resultList);
			objects.clear();
			for (Alarm vObject : uniqueSet) {
				objects.add(vObject);
			}

		} catch (Exception e) {
			
		}
	}
	
	/**
	 * populateHRCNList method.
	 */
	private void populateHRCNList() {
		hrcnListAdapter.notifyDataSetChanged();
		toggleViews();
	}
	
	/**
	 * toggleViews method.
	 */
	public void toggleViews() {
		getListView().setVisibility(View.VISIBLE);
	}
	/**
	 * onCreateOptionsMenu method
	 * @param menu.
	 * @return boolean.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.i("APP", "onCreateOptionsMenu");
    	menu.add(Menu.NONE, MenuConstants.HRCN_LIST_SYNC, Menu.NONE, R.string.sync)
    	.setIcon(R.drawable.apple_sync_icon)
    	.setAlphabeticShortcut('s');
    	menu.add(Menu.NONE, MenuConstants.EXIT_MENU_ID, Menu.NONE, R.string.logout)
		.setIcon(R.drawable.exit_red)
		.setAlphabeticShortcut('l');
    	return super.onCreateOptionsMenu(menu);
	}
	/**
	 * onOptionsItemSelected method
	 * @param item.
	 * @return boolean.
	 */	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.d(AppConstants.APPLICATION_TAG, "Menu Select item: " + item.getItemId());
		switch (item.getItemId()) {
		case MenuConstants.EXIT_MENU_ID:
			logout();
			return (true);
		case MenuConstants.HRCN_LIST_SYNC:
			sync();
			return (true);
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * logout method.
	 */
	private void logout() {
		if (logoutIntent == null ) {
			logoutFromHRP();
			logoutIntent = new Intent(this, LoginActivity.class);
		}
		startActivity(logoutIntent);
		finish();
	}
	/**
	 * logoutFromHRP method.
	 */
	private void logoutFromHRP() {
		try {
		Log.d("Logout","About to logout from HRP");
		final String LOGOUT_URL = AppUtilies.getServiceURL() + "/j_spring_security_logout";
		SessionManager sessionManager = new SessionManager();
		sessionManager.processPostRequest(LOGOUT_URL, null);
//			showAlertDialog("Successfully logout from HRP system", R.drawable.ic_dialog_info_c);
		
	} catch (Exception e) {
		
	}
}
	/**
	 * onOptionsItemSelected method
	 * @param item.
	 * @return boolean.
	 */	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		Log.d(AppConstants.APPLICATION_TAG, "Context Menu Select item: " + item.getItemId());
		switch (item.getItemId()) {
		case MenuConstants.WO_DEAILS_ACT_PAIRING:
			logout();
			return (true);
		case MenuConstants.HRCN_LIST_SYNC:
			sync();
			return (true);
		}
		return super.onContextItemSelected(item);
	}
	/**
	 * onCreateContextMenu method
	 * @param menu.
	 * @param v.
	 * @param menuInfo.
	 */	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		Log.i("APP", "onCreateContextMenu");
		super.onCreateContextMenu(menu, v, menuInfo);
    	menu.setHeaderTitle("Selected To Do Item");
    	menu.add("View Details");
    	menu.add("Change PIR");
	}

}
