package com.exlheathcare.healthyu.api;

import org.json.JSONArray;

import android.os.AsyncTask;
import android.util.Log;

import com.zackehh.andrest.AndrestClient;

public class ApiCall extends AsyncTask<String, Void, JSONArray> {

    private Exception e;
    private String url;
    private AndrestClient rest;
    private ApiCaller caller;

    public ApiCall(ApiCaller caller) {
        this.caller = caller;
        rest = new AndrestClient();
    }

    @Override
    protected JSONArray doInBackground(String... params) {
        this.url = params[0];
        try {
            Log.d(ApiCall.class.getSimpleName(), "Calling REST URL: " + url);
            JSONArray arr = rest.multiRequest(url, "GET", null); // Do request
            return arr;
        } catch (Exception e) {
            this.e = e; // Store error
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONArray data) {
        super.onPostExecute(data);
        // Display based on error existence
        if (e != null) {
            // Error message
            Log.e(this.getClass().getSimpleName(), e.getMessage());
        } else {
            // Success message
            // Log.d(this.getClass().getSimpleName(), data.toString());
            caller.apply(data);
        }
    }

    public interface ApiCaller {
        public void apply(JSONArray programs);
    }
}
