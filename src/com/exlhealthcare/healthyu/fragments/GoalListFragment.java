package com.exlhealthcare.healthyu.fragments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.exlhealthcare.healthyu.R;
import com.exlhealthcare.healthyu.adapters.BaseListAdapter;
import com.exlheathcare.healthyu.api.ApiCall;
import com.exlheathcare.healthyu.api.ApiCall.ApiCaller;

public class GoalListFragment extends ListFragment implements ApiCaller {
    private ListView goalList;
    private BaseListAdapter goalListAdapter;
    private GoalListInterface goalListInterface;
    private JSONArray goals;

    public GoalListFragment(JSONArray goals) {
        this.goals = goals;
    }

    @Override
    public View onCreateView(LayoutInflater pInflater, ViewGroup pContainer,
        Bundle pSavedInstanceState) {
        View rootView = pInflater.inflate(R.layout.base_list_fragment,
            pContainer, false);
        goalList = (ListView) rootView.findViewById(android.R.id.list);
        String[] goalNames = new String[goals.length()];
        for (int i = 0; i < goalNames.length; i++) {
            try {
                goalNames[i] = goals.getJSONObject(i).getJSONObject("caseGoal")
                    .getString("description");
            } catch (JSONException pException) {
                pException.printStackTrace();
            }
        }
        goalListAdapter = new BaseListAdapter(getActivity()
            .getApplicationContext(), goalNames);
        goalList.setAdapter(goalListAdapter);
        return rootView;
    }

    @Override
    public void onListItemClick(ListView pL, View pV, int pPosition, long pId) {
        super.onListItemClick(pL, pV, pPosition, pId);
        String req = "";
        try {
            req = getString(R.string.rest_url) + "Goal/"
                + goals.getJSONObject(pPosition).getString("internalId");
        } catch (JSONException pException) {
            pException.printStackTrace();
        }
        new ApiCall(this).execute(req);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(
            R.string.label_goals);
    }

    public void setGoalListInterface(GoalListInterface goalListInterface) {
        this.goalListInterface = goalListInterface;
    }

    @Override
    public void apply(JSONArray goal) {
        try {
            goalListInterface.onSelectGoal(goal.getJSONObject(0));
        } catch (JSONException pException) {
            pException.printStackTrace();
        }

    }

    public interface GoalListInterface {
        public void onSelectGoal(JSONObject goal);
    }

}
