package com.exlhealthcare.healthyu.adapters;

import java.util.Arrays;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.exlhealthcare.healthyu.R;

public class BaseListAdapter extends ArrayAdapter<String> {

    private Context context;

    private String[] values;

    public BaseListAdapter(Context context, String[] values) {
        super(context, R.layout.program_list_item_view, Arrays.asList(values));
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int pPosition, View pConvertView, ViewGroup pParent) {
        LayoutInflater inflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listItemView = inflater.inflate(R.layout.program_list_item_view,
            pParent, false);
        TextView tv = (TextView) listItemView.findViewById(R.id.program_name);
        tv.setText(values[pPosition]);
        return listItemView;
    }
}