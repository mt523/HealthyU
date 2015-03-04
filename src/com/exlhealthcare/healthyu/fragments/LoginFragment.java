package com.exlhealthcare.healthyu.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v7.app.ActionBarActivity;
import com.exlhealthcare.healthyu.R;

public class LoginFragment extends Fragment {

	private Button login_button;
	protected LoginInterface loginInterface;

	public LoginFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater pInflater, ViewGroup pContainer,
			Bundle pSavedInstanceState) {
		View rootView = pInflater.inflate(R.layout.login_fragment, pContainer,
				false);
		login_button = (Button) rootView.findViewById(R.id.login_button);
		login_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View pV) {
				loginInterface.onLogin();
			}
		});
		return rootView;
	}

	public void setLoginInterface(LoginInterface loginInterface) {
		this.loginInterface = loginInterface;
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
}
