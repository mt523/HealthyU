package com.exlhealthcare.healthyu;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.exlhealthcare.healthyu.fragments.GoalListFragment;
import com.exlhealthcare.healthyu.fragments.GoalListFragment.GoalListInterface;
import com.exlhealthcare.healthyu.fragments.LoginFragment;
import com.exlhealthcare.healthyu.fragments.LoginFragment.LoginInterface;
import com.exlhealthcare.healthyu.fragments.PlanListFragment;
import com.exlhealthcare.healthyu.fragments.PlanListFragment.PlanListInterface;
import com.exlhealthcare.healthyu.fragments.ProgramListFragment;
import com.exlhealthcare.healthyu.fragments.ProgramListFragment.ProgramListInterface;
import com.exlheathcare.healthyu.util.ApiCall.ApiCaller;
import com.landacorp.common.model.casemanagement.EpisodeSummary;

public class MainActivity extends ActionBarActivity implements
    OnBackStackChangedListener, ApiCaller, LoginInterface,
    ProgramListInterface, PlanListInterface, GoalListInterface {

    // Programs
    @SuppressWarnings("unused")
    private EpisodeSummary[] programs;

    // Fragments
    LoginFragment loginFragment;
    ProgramListFragment programListFragment;
    PlanListFragment planListFragment;
    GoalListFragment goalListFragment;

    // UI Components
    EditText user_id, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        loginFragment = new LoginFragment();
        loginFragment.setLoginInterface(this);
        getSupportFragmentManager().beginTransaction()
            .add(R.id.fragment_container, loginFragment).commit();
        // new ApiCall(this).execute(getString(R.string.rest_url));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLogin() {
        programListFragment = new ProgramListFragment();
        programListFragment.setProgramListInterface(this);
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, programListFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(null).commit();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onBackStackChanged() {
        boolean canback = getSupportFragmentManager().getBackStackEntryCount() > 0;
        getSupportActionBar().setDisplayHomeAsUpEnabled(canback);
    }

    @Override
    public boolean onSupportNavigateUp() {
        getSupportFragmentManager().popBackStack();
        return true;
    }

    @Override
    public void onSelectItem(int pIndex) {
        planListFragment = new PlanListFragment();
        planListFragment.setPlanListInterface(this);
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, planListFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(null).commit();
    }

    @Override
    public void onSelectProgram(int pIndex) {
        goalListFragment = new GoalListFragment();
        goalListFragment.setGoalListInterface(this);
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, goalListFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(null).commit();
    }

    @Override
    public void setPrograms(EpisodeSummary[] pPrograms) {
        programs = pPrograms;
    }

    @Override
    public void onSelectGoal(int pIndex) {
        Toast.makeText(MainActivity.this, "Selected goal: " + pIndex,
            Toast.LENGTH_SHORT).show();
    }
}
