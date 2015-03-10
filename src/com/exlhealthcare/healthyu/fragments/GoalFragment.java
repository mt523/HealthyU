package com.exlhealthcare.healthyu.fragments;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.exlhealthcare.healthyu.R;

public class GoalFragment extends Fragment {

    private JSONObject goal;
    private GoalFragmentInterface goalFragmentInterface;
    private TextView descriptionView;
    private TextView notesView;
    private Button btnUpdateGoal;

    public GoalFragment(JSONObject goal) {
        this.goal = goal;
    }

    @Override
    public View onCreateView(LayoutInflater pInflater, ViewGroup pContainer,
        Bundle pSavedInstanceState) {
        View rootView = pInflater.inflate(R.layout.goal_fragment, pContainer,
            false);
        descriptionView = (TextView) rootView.findViewById(R.id.goal_view);
        notesView = (TextView) rootView.findViewById(R.id.notes_view);
        btnUpdateGoal = (Button) rootView.findViewById(R.id.btn_update_goal);
        try {
            descriptionView.setText(goal.getJSONObject("caseGoal").getString(
                "description"));
            String notes = goal.getString("notes");
            if (notes.equals("null")) {
                notes = "No notes available.";
            }
            notesView.setText(notes);
        } catch (JSONException pException) {
            pException.printStackTrace();
        }
        btnUpdateGoal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View pV) {
                goalFragmentInterface.onUpdateGoal(goal);
            }
        });
        return rootView;
    }

    public void setGoalFragmentInterface(
        GoalFragmentInterface goalFragmentInterface) {
        this.goalFragmentInterface = goalFragmentInterface;
    }

    public interface LoginInterface {
        public void onLogin();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(
            R.string.label_goal);
    }

    public interface GoalFragmentInterface {
        public void onUpdateGoal(JSONObject goal);
    }
}
