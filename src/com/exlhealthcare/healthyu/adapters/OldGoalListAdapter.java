package com.exlhealthcare.healthyu.adapters;

import java.util.Arrays;
import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.exlhealthcare.healthyu.R;

public class OldGoalListAdapter extends ArrayAdapter<String> {

    @SuppressWarnings("unused")
    private String[] values;
    private Context context;

    public OldGoalListAdapter(Context context, String[] values) {
        super(context, R.layout.goal_list_item_view, Arrays.asList(values));
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.goal_list_item_view,
                parent, false);
            viewHolder = new ViewHolderItem();
            viewHolder.goalIcon = (ImageView) convertView
                .findViewById(R.id.goal_icon);
            viewHolder.goalName = (TextView) convertView
                .findViewById(R.id.goal_name);
            viewHolder.progressBar = (ProgressBar) convertView
                .findViewById(R.id.progress_bar);
            viewHolder.percentComplete = (TextView) convertView
                .findViewById(R.id.percent_complete);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderItem) convertView.getTag();
        }

        // Temporary fudge values for goals
        int value = new Random().nextInt(101);

        Drawable d = context.getResources().getDrawable(R.drawable.white_rect);
        d.setColorFilter(getColor(value), Mode.MULTIPLY);
        viewHolder.percentComplete.setBackground(d);
        viewHolder.percentComplete.setText(Integer.toString(value));
        viewHolder.percentComplete.setPaintFlags(viewHolder.percentComplete
            .getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
        viewHolder.progressBar.setProgress(value);
        return convertView;
    }

    private int getColor(int n) {
        float hue = (n / 100f) * 120f;
        return Color.HSVToColor(new float[] { hue, 1f, 0.8f });
    }

    class ViewHolderItem {
        ImageView goalIcon;
        TextView goalName;
        ProgressBar progressBar;
        TextView percentComplete;
    }
}
