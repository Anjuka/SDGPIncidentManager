package com.example.anjukakoralage.sdgpresit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by AnjukaKoralage on 7/29/2017.
 */

public class CustomAdapterViewAll extends ArrayAdapter<Incident> implements View.OnClickListener{
    private ArrayList<Incident> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView ID;
        TextView Date;
        TextView Note;
        TextView incidentType;
        TextView reportName;
        TextView responsePerson;
        TextView time;
        TextView uid;
    }

    public CustomAdapterViewAll(ArrayList<Incident> data, Context context) {
        super(context, R.layout.viewitem, data);
        this.dataSet = data;
        this.mContext=context;
    }


    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object = getItem(position);
        Incident dataModel=(Incident) object;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Incident dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        CustomAdapterViewAll.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new CustomAdapterViewAll.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.viewitem, parent, false);
            viewHolder.ID = (TextView) convertView.findViewById(R.id.textViewID);
            viewHolder.Date = (TextView) convertView.findViewById(R.id.textViewdate);
            viewHolder.reportName = (TextView) convertView.findViewById(R.id.textviewrepoter);
            viewHolder.responsePerson = (TextView) convertView.findViewById(R.id.textViewresponser);
            viewHolder.incidentType = (TextView) convertView.findViewById(R.id.textViewIncident);
            viewHolder.time = (TextView) convertView.findViewById(R.id.textViewTime);
            viewHolder.Note = (TextView) convertView.findViewById(R.id.textViewnote);
            viewHolder.uid = (TextView) convertView.findViewById(R.id.texthiddenID);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CustomAdapterViewAll.ViewHolder) convertView.getTag();
            result=convertView;
        }

        /*Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);*/
        lastPosition = position;


        viewHolder.ID.setText(dataModel.ID);
        viewHolder.reportName.setText(dataModel.ReporterName);
        viewHolder.responsePerson.setText(dataModel.ResponsPerson);
        viewHolder.time.setText(dataModel.Time);
        viewHolder.incidentType.setText(dataModel.IncidentType);
        viewHolder.Date.setText(dataModel.Date);
        viewHolder.Note.setText(dataModel.Note);
        viewHolder.uid.setText(dataModel.uid);
        // Return the completed view to render on screen
        return convertView;
    }
}


