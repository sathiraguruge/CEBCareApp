package com.example.cebcareapp;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PaymentHistory extends Fragment {

    private PaymentHistoryViewModel mViewModel;
    ListView paymentList;
    String[] dates = {"2019.02.17", "2019.04.05", "2019.05.15", "2019.06.01", "2019.07.02"};
    String[] description = {"Credit/Debit Card Payment", "Credit/Debit Card Payment", "Credit/Debit Card Payment", "Credit/Debit Card Payment", "Credit/Debit Card Payment"};
    String[] amount = {"Rs. 1000.00", "Rs.850", "Rs.850", "Rs.750", "Rs.1100"};
    int[] images = {R.drawable.visa_icon, R.drawable.visa_icon, R.drawable.visa_icon, R.drawable.visa_icon, R.drawable.visa_icon};


    public static PaymentHistory newInstance() {
        return new PaymentHistory();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.payment_history_fragment, container, false);
        paymentList = root.findViewById(R.id.listViewBillPaymentHistory);

        MyAdapter myAdapter = new MyAdapter(getActivity().getApplicationContext(), dates, description, amount, images);
        paymentList.setAdapter(myAdapter);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PaymentHistoryViewModel.class);
        // TODO: Use the ViewModel
    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String rDates[];
        String rDescription[];
        String rAmount[];
        int rImages[];

        MyAdapter(Context c, String dates[], String description[], String amount[], int img[]) {
            super(c, R.layout.payment_history_resource, R.id.payListTitle, dates);
            this.context = c;
            this.rDates = dates;
            this.rDescription = description;
            this.rAmount = amount;
            this.rImages = img;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);

            View row = layoutInflater.inflate(R.layout.payment_history_resource, parent, false);
            ImageView images = row.findViewById(R.id.imagePaymentList);
            TextView myDate = row.findViewById(R.id.payListTitle);
            TextView description = row.findViewById(R.id.payListDescription);
            TextView amount = row.findViewById(R.id.payListAmount);

            images.setImageResource(rImages[position]);
            myDate.setText(rDates[position]);
            amount.setText(rAmount[position]);
            description.setText(rDescription[position]);

            return row;
        }
    }

}
