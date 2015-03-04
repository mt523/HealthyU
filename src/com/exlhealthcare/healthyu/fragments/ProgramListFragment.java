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

public class ProgramListFragment extends ListFragment {

	private ListView programList;
	private BaseListAdapter programListAdapter;
	private ProgramListInterface programListInterface;

	public ProgramListFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater pInflater, ViewGroup pContainer,
			Bundle pSavedInstanceState) {
		View rootView = pInflater.inflate(R.layout.program_list_fragment,
				pContainer, false);
		programList = (ListView) rootView.findViewById(android.R.id.list);
		programListAdapter = new BaseListAdapter(getActivity()
				.getApplicationContext(), new String[] { "Diabetes Management",
				"Insomnia", "Medication Therapy Management",
				"Smoking Cessation", "Weight Management" });
		programList.setAdapter(programListAdapter);
		return rootView;
	}

	@Override
	public void onListItemClick(ListView pL, View pV, int pPosition, long pId) {
		super.onListItemClick(pL, pV, pPosition, pId);
		programListInterface.onSelectItem(pPosition);
	}

	@Override
	public void onResume() {
		super.onResume();
		((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(
				R.string.label_programs);
	}

	public void setProgramListInterface(
			ProgramListInterface programListInterface) {
		this.programListInterface = programListInterface;
	}

	public interface ProgramListInterface {
		public void onSelectItem(int index);
	}
}
