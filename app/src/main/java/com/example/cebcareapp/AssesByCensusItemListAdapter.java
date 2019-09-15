package com.example.cebcareapp;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AssesByCensusItemListAdapter extends ArrayAdapter<AssessByCensusItem> {

    private List<AssessByCensusItem> items ;
    TextView amount;
    TextView units;
    ImageButton qtyInc, qtyDec, hrsInc, hrsDec ;
    Animation fabOpen, fabClose, rotForward, rotBackward ;
//    boolean adjusterIsOpen = false ;
    Context context ;

    private static final float UNIT_PRICE = (float)36.58 ; // This is the unit price for electricity

    public AssesByCensusItemListAdapter(@NonNull Context context, int resource, @NonNull List<AssessByCensusItem> objects) {
        super(context, resource, objects);
        items = objects ;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View theListItemView, @NonNull ViewGroup parent) {

        if(theListItemView == null){
            theListItemView = LayoutInflater.from(getContext()).inflate(R.layout.asses_by_census_list_item, parent, false);
        }

        // Set the background color
        if(position %2 == 1){
            theListItemView.setBackgroundColor(Color.parseColor("#E1EEFF"));
        }
        else{
            theListItemView.setBackgroundColor(Color.parseColor("#BEE3FF"));
        }

        // Acquire the views
        AssessByCensusItem item = items.get(position);
        TextView qtyText = theListItemView.findViewById(R.id.abc_qty_text);
        TextView hrsText = theListItemView.findViewById(R.id.abc_hrs_text);
        TextView appName = theListItemView.findViewById(R.id.abc_appliance);
        CheckBox appSelect = theListItemView.findViewById(R.id.abcItemCheckBox);
        qtyInc = theListItemView.findViewById(R.id.abc_qty_inc);
        qtyDec = theListItemView.findViewById(R.id.abc_qty_dec);
        hrsInc = theListItemView.findViewById(R.id.abc_hrs_inc);
        hrsDec = theListItemView.findViewById(R.id.abc_hrs_dec);

        // Set Values
        qtyText.setText(Integer.toString(item.getQuantity()));
        hrsText.setText(Float.toString(item.getHoursADay()));
        appName.setText(item.getAppliance());
        appSelect.setChecked(item.isSelected());

        // Click events for Text Views
        // Update the Amount of units and price
        final AssessByCensusItem currentItem = items.get(position);
        final CheckBox selectAppliance = theListItemView.findViewById(R.id.abcItemCheckBox);
        final TextView quantity = theListItemView.findViewById(R.id.abc_qty_text);
        final TextView hours = theListItemView.findViewById(R.id.abc_hrs_text);

        // blank onClickListeners for error handling
        quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        hours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // Checkbox listener Event
        selectAppliance.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!selectAppliance.isChecked()){
                    currentItem.setSelected(false);
                    currentItem.setQuantity(0);
                    currentItem.setHoursADay(0.0f);

                    quantity.setText("0");
                    hours.setText("0.0");

                    updatePriceAndUnitsNew();
                }else if(selectAppliance.isChecked()){
                    currentItem.setSelected(true);
                }
            }
        });

        // Click events for Quantity increase and decrease
        qtyInc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(selectAppliance.isChecked()){
                    currentItem.setSelected(true);
                    quantity.setText(changeValue(quantity.getText().toString(), true, 1, currentItem));
//                    updatePriceAndUnits(quantity.getText().toString(), hours.getText().toString(), currentItem.getUnitsPerHour(), UNIT_PRICE, true, 1) ;
                    updatePriceAndUnitsNew();
                }
            }
        });

        qtyDec.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(selectAppliance.isChecked()){
                    currentItem.setSelected(true);
                    quantity.setText(changeValue(quantity.getText().toString(), false, 1, currentItem));
//                    updatePriceAndUnits(quantity.getText().toString(), hours.getText().toString(), currentItem.getUnitsPerHour(), UNIT_PRICE, false, 1) ;
                    updatePriceAndUnitsNew();
                }
            }
        });

        // Click events for Hours increase and decrease
        hrsInc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(selectAppliance.isChecked()){
                    currentItem.setSelected(true);
                    hours.setText(changeValue(hours.getText().toString(), true, 2, currentItem));
//                    updatePriceAndUnits(hours.getText().toString(), hours.getText().toString(), currentItem.getUnitsPerHour(), UNIT_PRICE, true, 2) ;
                    updatePriceAndUnitsNew();
                }
            }
        });

        hrsDec.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(selectAppliance.isChecked()){
                    currentItem.setSelected(true);
                    hours.setText(changeValue(hours.getText().toString(), false, 2, currentItem));
//                    updatePriceAndUnits(hours.getText().toString(), hours.getText().toString(), currentItem.getUnitsPerHour(), UNIT_PRICE, false, 2) ;
                    updatePriceAndUnitsNew();
                }
            }
        });

        return theListItemView;
    }

    // Function to increase the value of a text view
    /* Parameters
        1. The input string (String)
        2. Increase or decrease mode (Boolean) - true for increase, false for decrease
        3. Type (int) - 1 for quantity, 2 for hours
        3. Current Object of AssessByCensusItem
    */
    private String changeValue(String value, boolean mode, int type, AssessByCensusItem item){
        String outputValue = "0" ;
        int qantity ;
        float hours ;

        // Convert the String Value based on type
        if(type == 1){
            qantity = Integer.parseInt(value);
            if(mode){
                qantity++;
            }
            else {
                if(qantity != 0){
                    qantity--;
                }
            }
            item.setQuantity(qantity);
            outputValue = Integer.toString(qantity);
        }else{
            hours = Float.parseFloat(value);
            if(mode){
                hours = (hours + (float)0.5);
            }
            else {
                if(hours != 0){
                    hours = (hours - (float)0.5);
                }
            }
            item.setHoursADay(hours);
            outputValue = Float.toString(hours);
        }

        return outputValue ;
    }

    // Function to initiate amount and units Text views
    public void initiateTextViews(TextView amount, TextView units){
        this.amount = amount ;
        this.units = units ;
    }

    // Function to update the number of units and the unit price
    /*
    Parameters
        1. Quantity (String)
        2. Hours (String)
        3. Units per Hour (float)
        4. Unit Price (float)
        5. Increase or decrease mode (Boolean) - true for increase, false for decrease
        6. Type (int) - 1 for quantity, 2 for hours
     */
    private void updatePriceAndUnits(String quantity, String hours, float unitsPerHour, float unitPrice, boolean mode, int type){
        String currentUnits = units.getText().toString() ;

        double curUnits = Double.parseDouble(currentUnits) ;
        double numOfHours = Double.parseDouble(hours) ;
        double curUnitsForDisplay, numOfHoursForDesplay ;
        int curQuantity = Integer.parseInt(quantity) ;
        double curPrice = 0 ;

        if(mode){
            if(type == 1){
                curUnits = curUnits + (numOfHours * unitsPerHour) ;
            }else{
                curUnits = curUnits + (curQuantity * 0.5 * unitsPerHour) ;
            }
        }else{
            if(type == 1){
                curUnits = curUnits - (numOfHours * unitsPerHour) ;
            }else{
                curUnits = curUnits - (curQuantity * 0.5 * unitsPerHour) ;
            }
        }

        if(mode){
            curPrice = curPrice + (curUnits * unitPrice) ;
        }else{
            curPrice = curPrice - (curUnits * unitPrice) ;
        }

        // Round the values
        curUnitsForDisplay = (Math.round(curUnits*100))/100 ;
        numOfHoursForDesplay = (Math.round(curPrice*100))/100 ;

        units.setText(Double.toString(curUnitsForDisplay));
        amount.setText(Double.toString(numOfHoursForDesplay));
    }

    private void updatePriceAndUnitsNew(){

        int curQty = 0 ;
        float curHours = 0;
        float unitsPerHour = 0 ;
        double curUnitsForDisplay, numOfHoursForDesplay ;
        double totalUnits = 0 ;
        double totalPrice = 0 ;


        // Iterate through the whole collection
        for (AssessByCensusItem i : items ) {
            if(i.isSelected()){
                curQty = i.getQuantity() ;
                curHours = i.getHoursADay() ;
                unitsPerHour = i.getUnitsPerHour() ;

                totalUnits += (curQty * curHours * unitsPerHour) ;
                totalPrice += UNIT_PRICE * totalUnits ;
            }

//            Log.d("CREATION", "Qty: " + Integer.toString(curQty) + ", Hours: " + Float.toString(curHours) + ", UnitsPerHour: " + Float.toString(unitsPerHour));
//            Log.d("CREATION", "Units: " + Double.toString(totalUnits) + ", Price: " + Double.toString(totalPrice));
        }

        // Update Results
        // Round the values
        curUnitsForDisplay = (Math.round(totalUnits*100))/100.0 ;
        numOfHoursForDesplay = (Math.round(totalPrice*100))/100.0 ;

        units.setText(Double.toString(curUnitsForDisplay));
        amount.setText(Double.toString(numOfHoursForDesplay));
    }

}
