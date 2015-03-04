package com.exlhealthcare.healthyu.adapters;

import java.util.Arrays;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.exlhealthcare.healthyu.R;

public class GoalListAdapter extends ArrayAdapter<String> {

    private String[] values;
    private Context context;

    public GoalListAdapter(Context context, String[] values) {
        super(context, R.layout.goal_list_item_view, Arrays.asList(values));
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int pPosition, View pConvertView, ViewGroup pParent) {     
        return super.getView(pPosition, pConvertView, pParent);
    }
}
