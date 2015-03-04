package com.exlhealthcare.healthyu;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.exlhealthcare.healthyu.fragments.LoginFragment;
import com.exlhealthcare.healthyu.fragments.LoginFragment.LoginInterface;
import com.exlhealthcare.healthyu.fragments.PlanListFragment;
import com.exlhealthcare.healthyu.fragments.PlanListFragment.PlanListInterface;
import com.exlhealthcare.healthyu.fragments.ProgramListFragment;
import com.exlhealthcare.healthyu.fragments.ProgramListFragment.ProgramListInterface;
import com.exlheathcare.healthyu.util.ApiCall;
import com.exlheathcare.healthyu.util.ApiCall.ApiCaller;

public class MainActivity extends ActionBarActivity implements
    OnBackStackChangedListener, LoginInterface, ProgramListInterface,
    PlanListInterface, ApiCaller {

    // Programs
    private String[] programs;

    // Fragments
    LoginFragment loginFragment;
    ProgramListFragment programListFragment;
    PlanListFragment planListFragment;

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
        // String restURL = "http://jsonplaceholder.typicode.com/posts/";
        new ApiCall(this).execute(getString(R.string.rest_url));

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
        shouldDisplayHomeUp();
    }

    private void shouldDisplayHomeUp() {
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
        Toast.makeText(MainActivity.this, "Selected plan: " + pIndex,
            Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPrograms(String[] pPrograms) {
        for (String s : pPrograms) {
            if (s != null) {
                Log.d(this.getClass().getSimpleName(), s);
            }
        }
        programs = pPrograms;
    }

}
