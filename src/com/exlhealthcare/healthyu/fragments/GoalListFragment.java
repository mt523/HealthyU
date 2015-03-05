package com.exlhealthcare.healthyu.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.exlhealthcare.healthyu.R;
import com.exlhealthcare.healthyu.adapters.GoalListAdapter;

public class GoalListFragment extends ListFragment {

    private ListView goalList;
    private GoalListAdapter goalListAdapter;
    private GoalListInterface goalListInterface;

    public GoalListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater pInflater, ViewGroup pContainer,
        Bundle pSavedInstanceState) {
        View rootView = pInflater.inflate(R.layout.base_list_fragment,
            pContainer, false);
        goalList = (ListView) rootView.findViewById(android.R.id.list);
        goalListAdapter = new GoalListAdapter(getActivity()
            .getApplicationContext(), new String[] { "Record Glucose",
            "Log Meals", "Record Weight", "Brush Teeth" });
        goalList.setAdapter(goalListAdapter);
        return rootView;
    }

    @Override
    public void onListItemClick(ListView pL, View pV, int pPosition, long pId) {
        super.onListItemClick(pL, pV, pPosition, pId);
        goalListInterface.onSelectGoal(pPosition);
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

    public interface GoalListInterface {
        public void onSelectGoal(int index);
    }
}
