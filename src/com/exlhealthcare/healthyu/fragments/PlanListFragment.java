package com.exlhealthcare.healthyu.fragments;

import org.json.JSONArray;
import org.json.JSONException;

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

public class PlanListFragment extends ListFragment implements ApiCaller {

    private ListView planList;
    private BaseListAdapter planListAdapter;
    private PlanListInterface planListInterface;
    private JSONArray carePlans;

    public PlanListFragment(JSONArray carePlans) {
        this.carePlans = carePlans;
    }

    @Override
    public View onCreateView(LayoutInflater pInflater, ViewGroup pContainer,
        Bundle pSavedInstanceState) {
        View rootView = pInflater.inflate(R.layout.base_list_fragment,
            pContainer, false);
        planList = (ListView) rootView.findViewById(android.R.id.list);
        String[] carePlanNames = new String[carePlans.length()];
        for (int i = 0; i < carePlans.length(); i++) {
            try {
                carePlanNames[i] = carePlans.getJSONObject(i).getString("name");
            } catch (JSONException pException) {
                pException.printStackTrace();
            }
        }
        planListAdapter = new BaseListAdapter(getActivity()
            .getApplicationContext(), carePlanNames);
        planList.setAdapter(planListAdapter);
        return rootView;
    }

    @Override
    public void onListItemClick(ListView pL, View pV, int pPosition, long pId) {
        super.onListItemClick(pL, pV, pPosition, pId);
        String req = "";
        try {
            req = getString(R.string.rest_url) + "Issues/"
                + carePlans.getJSONObject(pPosition).getString("internalId");
        } catch (JSONException pException) {
            pException.printStackTrace();
        }
        new ApiCall(this).execute(req);
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
        public void onSelectPlan(JSONArray issues);
    }

    @Override
    public void apply(JSONArray programs) {
        planListInterface.onSelectPlan(programs);
    }
}
