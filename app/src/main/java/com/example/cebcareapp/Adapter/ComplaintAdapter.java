package com.example.cebcareapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cebcareapp.Entity.Complaint;
import com.example.cebcareapp.R;

import java.util.List;

public class ComplaintAdapter extends ArrayAdapter<Complaint> {
    private Context context;
    private List<Complaint> complaints;

    public ComplaintAdapter(Context context, List<Complaint> complaints) {
        super(context, R.layout.complaint_detail_layout, complaints);
        this.context = context;
        this.complaints = complaints;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.complaint_detail_layout,parent, false);

        TextView TextViewAccount = view.findViewById(R.id.TextViewAccount);
        TextViewAccount.setText(complaints.get(position).getAccount());

        System.out.println(complaints.get(position).getID());

        TextView textViewDateAdded = view.findViewById(R.id.textViewDateAdded);
        textViewDateAdded.setText("Complained On : " + complaints.get(position).getDateAdded());

        TextView textViewComplaintType = view.findViewById(R.id.textViewComplaintType);
        textViewComplaintType.setText(complaints.get(position).getComplaintType());

        TextView textViewComplaint = view.findViewById(R.id.textViewComplaint);
        textViewComplaint.setText( complaints.get(position).getComplaint());

        return view;
    }
}
