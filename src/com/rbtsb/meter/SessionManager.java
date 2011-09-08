/**
 * HRPMobile Application.
 */
package com.rbtsb.meter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

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
			InputStream instream = response.getEntity().getContent();
			String result= convertStreamToString(instream);
			instream.close();
			jsonResponse = result;
		}	
//		jsonResponse = TextHelper.GetText(response);
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
	

	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}




	
}
