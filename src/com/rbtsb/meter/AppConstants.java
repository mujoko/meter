/**
 * HRPMobile Application.
 */
package com.rbtsb.meter;
/**
 * This constants class is used to define application wide fixed resources.
 * @author asad
 */
public class AppConstants {
	
	// Preferences
	/** HRP_MOBILE_PREFERENCES */
	public static final String HRP_MOBILE_PREFERENCES = "com.rbtsb.meter.LoginActivity";
//	/** HRP_MOBILE_PREFERENCES_MAP */
	//public static final HashMap<String, String>HRP_MOBILE_PREFERENCES_MAP = new HashMap<String, String>();
	/** HRP_CONTEXT_URL */
	public static final String HRP_CONTEXT_URL = "/meter";
	/** APPLICATION_TAG */
	public static final String APPLICATION_TAG = "MeterMobile";
	/** loggedInUser */
	public static String loggedInUser = "";
	/** HRP_TAB_WIDTH */
	public static final int HRP_TAB_WIDTH = 110;
	/** HRP_TAB_HEIGHT */
	public static final int HRP_TAB_HEIGHT = 30; //150 with tab icons
	
	// InprogressHRCNVO data fields
	/** HRCN_ID */
	public static final String HRCN_ID = "id";
	/** CONTAINER_NO_FIELD */
	public static final String CONTAINER_NO_FIELD = "containerNumber";
	/** VOYAGE_NO_FIELD */
	public static final String VOYAGE_NO_FIELD = "voyageNumber";
	/** TEMPERATURE_FIELD */
	public static final String TEMPERATURE_FIELD = "temperature";
	/** CREATED_ON_DATE_FIELD */
	public static final String CREATED_ON_DATE_FIELD = "createdOnString";
	/** CREATED_TIME_FIELD */
	public static final String CREATED_TIME_FIELD = "createdTime";
	
	// PIRDetail HRCNId field for Master Tab (for fetching PIR detail)
	/** PIR_ID */
	public static final String PIR_ID = "id";
	
	// PIR fields for Basic Info. Tab
	/** INSPECTION_NO_FIELD */
	public static final String INSPECTION_NO_FIELD = "inspectionNo";
	/** INSPECTION_DATE_FIELD */
	public static final String INSPECTION_DATE_FIELD = "inspectionDate";
	/** START_TIME_FIELD */
	public static final String START_TIME_FIELD = "inspectionTimeStart";
	/** END_TIME_FIELD */
	public static final String END_TIME_FIELD = "inspectionTimeEnd";
	
	// PIR fields for Officers Tab
	/** OFFICER_1_FIELD */
	public static final String OFFICER_1_FIELD = "officer1";
	/** OFFICER_2_FIELD */
	public static final String OFFICER_2_FIELD = "officer2";
	/** OFFICER_3_FIELD */
	public static final String OFFICER_3_FIELD = "officer3";
	/** OFFICER_4_FIELD */
	public static final String OFFICER_4_FIELD = "officer4";
	
	// PIR fields for Procedures Tab
	/** PROC_SEAL_NO_FIELD */
	public static final String PROC_SEAL_NO_FIELD = "procSealNo";
	/** PROC_SEAL_STATUS_FIELD */
	public static final String PROC_SEAL_STATUS_FIELD = "procSealStatus";
	/** PROC_TEMP_SET_FIELD */
	public static final String PROC_TEMP_SET_FIELD = "procTempSettings";
	/** PROC_REMARKS_FIELD */
	public static final String PROC_REMARKS_FIELD = "procRemarks";
	
	// PIR fields for Measurements Tab (same as Feet10-20 tab in HRP web)
	/** MEASUREMENT_RESULT_FIELD */
	public static final String MEASUREMENT_RESULT_FIELD = "feet10Result";
	/** MEASUREMENT_REMARKS_FIELD */
	public static final String MEASUREMENT_REMARKS_FIELD = "feet10Remarks";
	
	// HRCN fields for Decision Tab
	/** DECISION_REMARKS_FIELD */
	public static final String DECISION_REMARKS_FIELD = "remarks";
	/** DECISION_DATE_FIELD */
	public static final String DECISION_DATE_FIELD = "decisionDate";
	/** DECISION_STATUS_FIELD */
	public static final String DECISION_STATUS_FIELD = "decisionStatus";
	
	// ShipmentCertVO data fields
	/** SHIPMENT_CERT_ID */
	public static final String SHIPMENT_CERT_ID = "id";
	/** CERT_TYPE_FIELD */
	public static final String CERT_TYPE_FIELD = "type";
	/** CERTIFIER_FIELD */
	public static final String CERTIFIER_FIELD = "organization";
	/** CERTIFIER_NAME_FIELD */
	public static final String CERTIFIER_NAME_FIELD = "name";
	/** PRODUCT_ITEM_FIELD */
	public static final String PRODUCT_ITEM_FIELD = "productItem";
	/** PRODUCT_ITEM_DESCRIPTION_FIELD */
	public static final String PRODUCT_ITEM_DESCRIPTION_FIELD = "description";
	/** EXPIRY_DATE_FIELD */
	public static final String EXPIRY_DATE_FIELD = "expiryDate";
	
	
}
