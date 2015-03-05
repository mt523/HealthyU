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
        super(context, R.layout.base_list_item_view, Arrays.asList(values));
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderItem viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.base_list_item_view,
                parent, false);
            viewHolder = new ViewHolderItem();
            viewHolder.textView = (TextView) convertView
                .findViewById(R.id.base_text_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderItem) convertView.getTag();
        }
        viewHolder.textView.setText(values[position]);
        return convertView;
    }

    class ViewHolderItem {
        TextView textView;
    }
}