/**
 * HRPMobile Application.
 */
package com.rbtsb.meter;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * This adapter is used to make rows for HRCNListActivity page.
 * @author asad
 */
public class InprogressAlarmListAdapter extends ArrayAdapter<Alarm> {
	/** earlierRow */
	private View earlierRow = null;
	/** pirIntent */
	Intent pirIntent = null;
	/** context */
	private Activity context;
	/** objects */
	private List<Alarm> objects;
	/** textViewResourceId */
	private int textViewResourceId;
	/** LinearLayout */
	protected Object LinearLayout;
	/** shipmentCertListIntent */
	Intent shipmentCertListIntent = null;
    /** 
     * InprogressHRCNListAdapter constructor.
     *@param context.
     *@param textViewResourceId.
     *@param objects.
     */
	public InprogressAlarmListAdapter(Activity context, int textViewResourceId, List<Alarm> objects) {
		super(context, textViewResourceId, objects);
			this.context = context;
			this.objects = objects;
			this.textViewResourceId = textViewResourceId;
	}
    /** 
     * getView method.
     *@param position.
     *@param convertView.
     *@param parent.
     *@return View.
     */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Log.d("ROW", "Geeting row: " + position);
		LayoutInflater layoutInflater = context.getLayoutInflater();
		View row = layoutInflater.inflate(textViewResourceId, null);
//		final InprogressHRCNVO valueObject = objects.get(position);
		Alarm valueObject = objects.get(position);
		TextView hRCNId = (TextView)row.findViewById(R.id.hrcnId);
		hRCNId.setText("HRCN Id: " + valueObject.getId());
		TextView hrcnContainerNumber = (TextView)row.findViewById(R.id.hrcnContainerNumber);
		hrcnContainerNumber.setText("Container No.: " + valueObject.getErrorCode());
		TextView hrcnInspectionDate = (TextView)row.findViewById(R.id.hrcnInspectionDate);
		hrcnInspectionDate.setText("Date: " + valueObject.getOccuredOn());			
		
		TextView hrcnVoyageNumber = (TextView)row.findViewById(R.id.hrcnVoyageNumber);
		hrcnVoyageNumber.setText("Voyage No.: " + valueObject.getUser());
		
		TextView hrcnTempSetting = (TextView)row.findViewById(R.id.hrcnTempSetting);
		hrcnTempSetting.setText("Temprature Setting: " + valueObject.getLevels());
		
		Button viewDetailsButton = (Button)row.findViewById(R.id.btnViewDetails);
		viewDetailsButton.setContentDescription(valueObject.getId());
		viewDetailsButton.setOnClickListener(new OnClickListener() {
			
//			@Override
			public void onClick(View buttonView) {
				viewDetails(buttonView.getContentDescription().toString());
			}
			
		});
		
		Button shipmentCertsButton = (Button)row.findViewById(R.id.btnShipmentCerts);
		shipmentCertsButton.setContentDescription(valueObject.getId());
		shipmentCertsButton.setOnClickListener(new OnClickListener() {
			
//			@Override
			public void onClick(View buttonView) {
				showShipmentCerts(buttonView.getContentDescription().toString());
			}
			
		});
		
		row.setOnClickListener(new OnClickListener() {
//			@Override
			public void onClick(View row) {
				Log.i("KEY", "setOnClickListener -> onClick");
				
				LinearLayout layout = (LinearLayout)row.findViewById(R.id.expanded_layout);
				if(layout.getVisibility() == View.GONE) {
					Log.i("TOGGLE", "upper loop");
					layout.setVisibility(View.VISIBLE);
				}
				else {
					Log.i("TOGGLE", "Lower loop");
					layout.setVisibility(View.GONE);
				}

				if(earlierRow != null) {
					Log.i("TOGGLE", "I am in inside second loop");
					if( earlierRow != row) {
						Log.i("TOGGLE", earlierRow.getTag() + "/" + row.getTag());
						LinearLayout layoutEarlier = (LinearLayout)earlierRow.findViewById(R.id.expanded_layout);
						if(layoutEarlier.getVisibility() == View.VISIBLE)
							layoutEarlier.setVisibility(View.GONE);
					} 
				} else {
					Log.i("TOGGLE", "First time");
					earlierRow = row;
				}
				
				earlierRow = row;
			}
		});
	
		row.setOnKeyListener(new OnKeyListener() {
//			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				Log.i("KEY", "setOnKeyListener -> onKey");
				return false;
			}
			
		});
		
		//return super.getView(position, convertView, parent);
		return row;
	}
    /** 
     * viewDetails method.
     *@param hrcnId.
     */
	private void viewDetails(String hrcnId){
		
//        	  Bundle bundle = new Bundle();
//        	  Log.d("InprogressHRCNListAdapter", "View/Update PIR >>> Passing tempId >>>> " + hrcnId); 
//        	  bundle.putString("hrcnId", hrcnId);
////				pirIntent = new Intent(context, PIRActivity.class);
//        	  pirIntent = new Intent(context, PIRTabsActivity.class);
//        	  pirIntent.putExtras(bundle);
//			
//			context.startActivity(pirIntent);
	}
    /** 
     * showShipmentCerts method.
     *@param hrcnId.
     */
	private void showShipmentCerts(String hrcnId){
		
//  	  Bundle bundle = new Bundle();
//  	  Log.d("InprogressHRCNListAdapter", "Shipment Certificates >>> Passing tempId >>>> " + hrcnId); 
//  	  bundle.putString("hrcnId", hrcnId);
//  	  shipmentCertListIntent = new Intent(context, ShipmentCertListActivity.class);
//  	  shipmentCertListIntent.putExtras(bundle);
//  	  context.startActivity(shipmentCertListIntent);
}
    /** 
     * getItem method.
     *@param position.
     *@return Alarm.
     */	
	@Override
	public Alarm getItem(int position) {
		return super.getItem(position);
	}

}
