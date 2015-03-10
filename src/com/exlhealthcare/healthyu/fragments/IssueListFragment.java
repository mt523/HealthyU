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

public class IssueListFragment extends ListFragment implements ApiCaller {
    private ListView issueList;
    private BaseListAdapter issueListAdapter;
    private IssueListInterface issueListInterface;
    private JSONArray issues;

    public IssueListFragment(JSONArray issues) {
        this.issues = issues;        
    }

    @Override
    public View onCreateView(LayoutInflater pInflater, ViewGroup pContainer,
        Bundle pSavedInstanceState) {
        View rootView = pInflater.inflate(R.layout.base_list_fragment,
            pContainer, false);
        issueList = (ListView) rootView.findViewById(android.R.id.list);
        String[] issueNames = new String[issues.length()];
        for (int i = 0; i < issueNames.length; i++) {
            try {
                issueNames[i] = issues.getJSONObject(i)
                    .getJSONObject("caseIssue").getString("description");
            } catch (JSONException pException) {
                pException.printStackTrace();
            }
        }
        issueListAdapter = new BaseListAdapter(getActivity()
            .getApplicationContext(), issueNames);
        issueList.setAdapter(issueListAdapter);
        return rootView;
    }

    @Override
    public void onListItemClick(ListView pL, View pV, int pPosition, long pId) {
        super.onListItemClick(pL, pV, pPosition, pId);
        String req = "";
        try {
            req = getString(R.string.rest_url) + "Goals/"
                + issues.getJSONObject(pPosition).getString("internalId");
        } catch (JSONException pException) {
            pException.printStackTrace();
        }
        new ApiCall(this).execute(req);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(
            R.string.label_issues);
    }

    public void setIssueListInterface(
        IssueListInterface issueListInterface) {
        this.issueListInterface = issueListInterface;
    }

    @Override
    public void apply(JSONArray goals) {
        issueListInterface.onSelectIssue(goals);

    }

    public interface IssueListInterface {
        public void onSelectIssue(JSONArray goals);
    }

}
