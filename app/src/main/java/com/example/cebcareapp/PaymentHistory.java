package com.example.cebcareapp;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.cebcareapp.Entity.Payment;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PaymentHistory extends Fragment {

    private PaymentHistoryViewModel mViewModel;
    ListView paymentList;
    String[] dates = {"2019.02.17", "2019.04.05", "2019.05.15", "2019.06.01", "2019.07.02"};
    String[] description = {"Credit/Debit Card Payment", "Credit/Debit Card Payment", "Credit/Debit Card Payment", "Credit/Debit Card Payment", "Credit/Debit Card Payment"};
    String[] amount = {"Rs. 1000.00", "Rs.850", "Rs.850", "Rs.750", "Rs.1100"};
    int[] images = {R.drawable.visa_icon, R.drawable.visa_icon, R.drawable.visa_icon, R.drawable.visa_icon, R.drawable.visa_icon};


    FirebaseDatabase database;
    DatabaseReference ref;

    RecyclerView recyclerView;

    ArrayList<Payment> paymentArrayList = new ArrayList<>();

    public static PaymentHistory newInstance() {
        return new PaymentHistory();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.payment_history_fragment, container, false);


        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("Payments");
        ref.keepSynced(true);


        recyclerView = root.findViewById(R.id.listViewBillPaymentHistory);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Payment> options =
                new FirebaseRecyclerOptions.Builder<Payment>()
                        .setQuery(ref, Payment.class)
                        .build();
        FirebaseRecyclerAdapter firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Payment, PaymentViewHolder>(options) {

            @Override
            public PaymentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.payment_history_resource, parent, false);

                return new PaymentViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull PaymentViewHolder holder, int position, @NonNull Payment model) {
                // Bind the image_details object to the BlogViewHolder
                // ...
                // holder.setDetails(getApplicationContext(), model.getName(), model.getDepartment(), model.getDescription());
                holder.setDetails(getActivity().getApplicationContext(), model.getDate(), model.getAccountNumber(), model.getName(), model.getEmail(), model.getAmount(), model.getPaymentMethod(), model.getImage());
            }
        };
        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PaymentHistoryViewModel.class);
        // TODO: Use the ViewModel


    }


    public static class PaymentViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView mDateView;
        TextView mPaymentMethod;
        TextView mAmount;
        ImageView mImage;

        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mView = itemView;

        }

        public void setDetails(Context context, String date, String accountNumber, String name, String email, String amount, String paymentMethod, String image) {
            TextView mDateView = mView.findViewById(R.id.payListTitle);
            TextView mPaymentMethod = mView.findViewById(R.id.payListDescription);
            TextView mAmount = mView.findViewById(R.id.payListAmount);
            ImageView mImage = mView.findViewById(R.id.imagePaymentList);

            mDateView.setText(date);
            mPaymentMethod.setText(paymentMethod);
            mAmount.setText(amount);
            Picasso.get().load(image).into(mImage);
        }
    }

}
