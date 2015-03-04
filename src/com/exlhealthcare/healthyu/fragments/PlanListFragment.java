package com.exlhealthcare.healthyu.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.exlhealthcare.healthyu.R;
import com.exlhealthcare.healthyu.adapters.BaseListAdapter;

public class PlanListFragment extends ListFragment {

    private ListView planList;
    private BaseListAdapter planListAdapter;
    private PlanListInterface planListInterface;

    public PlanListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater pInflater, ViewGroup pContainer,
        Bundle pSavedInstanceState) {
        View rootView = pInflater.inflate(R.layout.program_list_fragment,
            pContainer, false);
        planList = (ListView) rootView.findViewById(android.R.id.list);
        planListAdapter = new BaseListAdapter(getActivity()
            .getApplicationContext(), new String[] { "Plan 1", "Plan 2",
            "Plan 3", "Plan 4", "Plan 5", "Plan 6", "Plan 7", "Plan 8",
            "Plan 9", "Plan 10" });
        planList.setAdapter(planListAdapter);
        return rootView;
    }

    @Override
    public void onListItemClick(ListView pL, View pV, int pPosition, long pId) {
        super.onListItemClick(pL, pV, pPosition, pId);
        planListInterface.onSelectProgram(pPosition);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(
            R.string.label_plans);
    }

    public void setPlanListInterface(PlanListInterface planListInterface) {
        this.planListInterface = planListInterface;
    }

    public interface PlanListInterface {
        public void onSelectProgram(int index);
    }
}
