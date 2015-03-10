package com.exlheathcare.healthyu.api;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.zackehh.andrest.AndrestClient;

public class ApiPost extends AsyncTask<String, Void, JSONObject> {

    private Exception e;

    private String url;
    private String internalId;
    private String notes;

    private AndrestClient rest;
    private ApiPoster poster;

    public ApiPost(ApiPoster poster) {
        this.poster = poster;
        rest = new AndrestClient();
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        
        this.url = params[0];
        this.internalId = params[1];
        this.notes = params[2];
        
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("internalId", internalId);
        body.put("notes", notes);
        
        try {
            Log.d(ApiPost.class.getSimpleName(), "Calling REST URL: " + url);
            JSONObject result = rest.request(url, "POST", body);
            return result;
        } catch (Exception e) {
            this.e = e; // Store error
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        super.onPostExecute(result);
        // Display based on error existence
        if (e != null) {
            // Error message
            Log.e(this.getClass().getSimpleName(), e.getMessage());
        } else {
            // Success message
            // Log.d(this.getClass().getSimpleName(), data.toString());
            poster.postPost(result);
        }
    }

    public interface ApiPoster {
        public void postPost(JSONObject result);
    }
}
