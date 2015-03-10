package com.exlhealthcare.healthyu;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.exlhealthcare.healthyu.fragments.GoalFragment;
import com.exlhealthcare.healthyu.fragments.GoalFragment.GoalFragmentInterface;
import com.exlhealthcare.healthyu.fragments.GoalListFragment;
import com.exlhealthcare.healthyu.fragments.GoalListFragment.GoalListInterface;
import com.exlhealthcare.healthyu.fragments.GoalUpdateFragment;
import com.exlhealthcare.healthyu.fragments.IssueListFragment;
import com.exlhealthcare.healthyu.fragments.IssueListFragment.IssueListInterface;
import com.exlhealthcare.healthyu.fragments.LoginFragment;
import com.exlhealthcare.healthyu.fragments.LoginFragment.LoginInterface;
import com.exlhealthcare.healthyu.fragments.PlanListFragment;
import com.exlhealthcare.healthyu.fragments.PlanListFragment.PlanListInterface;
import com.exlhealthcare.healthyu.fragments.ProgramListFragment;
import com.exlhealthcare.healthyu.fragments.ProgramListFragment.ProgramListInterface;
import com.exlheathcare.healthyu.api.ApiCall;
import com.exlheathcare.healthyu.api.ApiCall.ApiCaller;

public class MainActivity extends ActionBarActivity implements
    OnBackStackChangedListener, ApiCaller, LoginInterface,
    ProgramListInterface, PlanListInterface, IssueListInterface,
    GoalListInterface, GoalFragmentInterface {

    // Fragments
    LoginFragment loginFragment;

    ProgramListFragment programListFragment;
    PlanListFragment planListFragment;
    IssueListFragment issueListFragment;
    GoalListFragment goalListFragment;
    GoalFragment goalFragment;
    GoalUpdateFragment goalUpdateFragment;

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
        new ApiCall(this).execute(getString(R.string.rest_url) + "Programs/"
            + getString(R.string.program_id));
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
    public void onSelectProgram(JSONArray carePlans) {
        planListFragment = new PlanListFragment(carePlans);
        planListFragment.setPlanListInterface(this);
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, planListFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(null).commit();
    }

    @Override
    public void onSelectPlan(JSONArray issues) {
        issueListFragment = new IssueListFragment(issues);
        issueListFragment.setIssueListInterface(this);
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, issueListFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(null).commit();
    }

    @Override
    public void onSelectIssue(JSONArray goals) {
        goalListFragment = new GoalListFragment(goals);
        goalListFragment.setGoalListInterface(this);
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, goalListFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(null).commit();
    }

    @Override
    public void onSelectGoal(JSONObject goal) {
        GoalFragment goalFragment = new GoalFragment(goal);
        goalFragment.setGoalFragmentInterface(this);
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, goalFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(null).commit();
    }

    @Override
    public void apply(JSONArray programs) {
        programListFragment = new ProgramListFragment(programs);
        programListFragment.setProgramListInterface(this);
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, programListFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(null).commit();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onUpdateGoal(JSONObject goal) {
        goalUpdateFragment = new GoalUpdateFragment(goal);        
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, goalUpdateFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(null).commit();
    }

}
