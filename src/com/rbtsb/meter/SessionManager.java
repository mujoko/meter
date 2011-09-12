/**
 * HRPMobile Application.
 */
package com.rbtsb.meter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
/**
 * This class is used to define session management / communication with server.
 * @author asad
 */
public class SessionManager {
	/** httpClient */
	public static DefaultHttpClient httpClient = null;
	/** 
	 * init method.
	 */
	public static void init(){
		if (httpClient == null) {
			httpClient = new DefaultHttpClient();
		}
	}

	/** 
	 * processGetRequest method.
	 * @param url.
	 * @param params.
	 * @return jsonResponse.
	 */
	public String processGetRequest(String url, ArrayList<NameValuePair> params){

		String jsonResponse = null;
		try{
			init();
			HttpGet httpGet = new HttpGet(url);
			if (params != null) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
			}
			httpGet.setHeader("user-agent", "HRP Mobile -  Google Android");	
			//execute get
			HttpResponse response = httpClient.execute(httpGet);
			//writeCookies(url);
			jsonResponse = TextHelper.GetText(response);
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}catch (ClientProtocolException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResponse;


	}
	/** 
	 * processPostRequest method.
	 * @param url.
	 * @param params.
	 * @return jsonResponse.
	 */
	public String processPostRequest(String url, ArrayList<NameValuePair> params){

		String jsonResponse = null;

		try {
			init();
			HttpPost httpPost = new HttpPost(url);
			if (params != null) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
				httpPost.setHeader("user-agent", "HRP Mobile -  Google Android");
				httpPost.setEntity(entity);	
			}
			//execute post
			HttpResponse response = httpClient.execute(httpPost);
			if (response.getEntity() != null) {
				jsonResponse = TextHelper.GetText(response);
			}	
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (ClientProtocolException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		Log.i("RESPON__SE",jsonResponse);


		return jsonResponse;
	}

	
	/**
	 * generateInprogressHRCNList method.
	 * @param jsonResponse.
	 * @return list.
	 */
	public ArrayList<Alarm> generateInprogressHRCNList(String jsonResponse){
	ArrayList<Alarm> result = new ArrayList<Alarm>();
	Alarm valueObject = null;
	if(jsonResponse == null || jsonResponse.length() < 5) {
		return null;
	}
	try {
		JSONObject jsonObject = new JSONObject(jsonResponse);
		JSONArray jsonArray = jsonObject.getJSONArray("results");
		Log.d("JsonArray = ", jsonArray.toString());
		for (int i=0; i<jsonArray.length();i++) {
			jsonObject = jsonArray.getJSONObject(i);
			Log.d("record-level JsonObject = ", jsonObject.toString());
			valueObject = new Alarm();
			valueObject.setId(jsonObject.getString(AppConstants.HRCN_ID));
			valueObject.setErrorCode(jsonObject.getString(AppConstants.MODULE));
			valueObject.setMessage(jsonObject.getString(AppConstants.VOYAGE_NO_FIELD));
			valueObject.setLevels("levels");
//			valueObject.setOccuredOn(jsonObject.getString("occuredOn"));

			result.add(valueObject);
		}

		
	} catch (JSONException e) {
		
		e.printStackTrace();
	}
	
	return result;
	}

}
