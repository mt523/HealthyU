package com.exlheathcare.healthyu.util;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;

import android.os.AsyncTask;
import android.util.Log;

import com.zackehh.andrest.AndrestClient;

public class ApiCall extends AsyncTask<String, Void, JSONArray> {

	private Exception e;
	private String url;
	private AndrestClient rest;
	private ObjectMapper mapper;
	private ApiCaller caller;

	public ApiCall(ApiCaller caller) {
		this.caller = caller;
		rest = new AndrestClient();
		mapper = new ObjectMapper();
	}

	@Override
	protected JSONArray doInBackground(String... params) {
		this.url = params[0];
		try {
			return rest.multiRequest(url, "GET", null); // Do request
		} catch (Exception e) {
			this.e = e; // Store error
		}
		return null;
	}

	@Override
	protected void onPostExecute(JSONArray data) {
		super.onPostExecute(data);
		String[] result = new String[data.length()];
		for (int i = 0; i < data.length(); i++) {
			try {
				Log.d(this.getClass().getSimpleName(), data.get(i).toString());
				result[i] = mapper.readValue(data.get(i).toString(),
						String.class);
			} catch (JSONException pException) {
				Log.e(this.getClass().getSimpleName(), pException.getMessage());
			} catch (JsonParseException pException) {
				pException.printStackTrace();
			} catch (JsonMappingException pException) {
				pException.printStackTrace();
			} catch (IOException pException) {
				pException.printStackTrace();
			}
		}
		// Display based on error existence
		if (e != null) {
			// Error message
			Log.e(this.getClass().getSimpleName(), e.getMessage());
		} else {
			// Success message
			// Log.d(this.getClass().getSimpleName(), data.toString());
			caller.setPrograms(result);
		}
	}

	public interface ApiCaller {
		public void setPrograms(String[] programs);
	}
}
