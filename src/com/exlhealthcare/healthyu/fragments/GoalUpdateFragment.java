package com.exlhealthcare.healthyu.fragments;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.exlhealthcare.healthyu.R;
import com.exlheathcare.healthyu.api.ApiPost;
import com.exlheathcare.healthyu.api.ApiPost.ApiPoster;

public class GoalUpdateFragment extends Fragment implements ApiPoster {

    private JSONObject goal;
    private TextView descriptionView;
    private TextView notesEditor;
    private Button btnSave;

    private String internalId;

    public GoalUpdateFragment(JSONObject goal) {
        this.goal = goal;
    }

    @Override
    public View onCreateView(LayoutInflater pInflater, ViewGroup pContainer,
        Bundle pSavedInstanceState) {
        View rootView = pInflater.inflate(R.layout.goal_update_fragment,
            pContainer, false);
        descriptionView = (TextView) rootView.findViewById(R.id.goal_view);
        notesEditor = (TextView) rootView.findViewById(R.id.notes_editor);
        btnSave = (Button) rootView.findViewById(R.id.btn_save);
        try {
            descriptionView.setText(goal.getJSONObject("caseGoal").getString(
                "description"));
            String notes = goal.getJSONObject("caseGoal").getString("notes");
            if (notes.equals("null")) {
                notes = "No notes available.";
            }
            notesEditor.setHint(notes);
        } catch (JSONException pException) {
            pException.printStackTrace();
        }
        try {
            internalId = goal.getString("internalId");
        } catch (JSONException pException) {
            pException.printStackTrace();
        }
        btnSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View pV) {
                String notes = notesEditor.getText().toString();
                String req = getString(R.string.rest_url) + "UpdateGoal";
                new ApiPost(GoalUpdateFragment.this).execute(req, internalId,
                    notes);
            }
        });
        return rootView;
    }

    public interface LoginInterface {
        public void onLogin();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(
            R.string.label_login);
    }

    @Override
    public void postPost(JSONObject result) {
        Log.d(GoalUpdateFragment.class.getSimpleName(), result.toString());
        getActivity().getSupportFragmentManager().popBackStack();
    }

}
