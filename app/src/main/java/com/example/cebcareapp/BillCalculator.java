package com.example.cebcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BillCalculator extends AppCompatActivity implements View.OnClickListener {

    TextView tariff, selfGeneration, units, startDate, endDate, wayOfBillCalculation;
    RadioGroup radioGroup;
    RadioButton numOfDays, pickDates;
    TableRow datePickerRow;
    EditText unitsInput, numberOfDaysInput;
    DatePickerDialog picker;
    private int fixedCharges = 500;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_calculator);

        Toolbar toolbar = findViewById(R.id.billPaymentToolbar);
        toolbar.setTitle("Bill Calculator");
        setSupportActionBar(toolbar);

        tariff = findViewById(R.id.tariffTextView);
        selfGeneration = findViewById(R.id.selfGenerationTextView);
        units = findViewById(R.id.unitsTextView);

        radioGroup = findViewById(R.id.billCalculatorRadioGroup);
        numOfDays = findViewById(R.id.billCalculatorNumOfDaysRadioBtn);
        pickDates = findViewById(R.id.billCalculatorPickDatesRadioBtn);
        datePickerRow = findViewById(R.id.billCalculatorDatePickerTableRow);

        unitsInput = findViewById(R.id.billCalculatorNumberOfUnitsEditText);
        numberOfDaysInput = findViewById(R.id.billCalculatorNumOfDaysEditText);

        startDate = findViewById(R.id.billCalculatorStartDatePickerTextView);
        endDate = findViewById(R.id.billCalculatorEndDatePickerTextView);

        wayOfBillCalculation = findViewById(R.id.wayOfCalculationTextView);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (numOfDays.isChecked()) {
                    datePickerRow.setVisibility(View.GONE);
                    numberOfDaysInput.setVisibility(View.VISIBLE);
                } else if (pickDates.isChecked()) {
                    datePickerRow.setVisibility(View.VISIBLE);
                    numberOfDaysInput.setVisibility(View.GONE);
                }
            }
        });


        tariff.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;


                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (tariff.getRight() - tariff.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "This is your electricity bill type",
                                Toast.LENGTH_SHORT);
                        toast.show();
                        return true;
                    }
                }
                return false;
            }
        });

        selfGeneration.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;


                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (selfGeneration.getRight() - selfGeneration.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Whether you have produced electricity with Solar Panels",
                                Toast.LENGTH_SHORT);
                        toast.show();
                        return true;
                    }
                }
                return false;
            }
        });

        units.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;


                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (units.getRight() - units.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Number of units consumed.",
                                Toast.LENGTH_SHORT);
                        toast.show();
                        return true;
                    }
                }
                return false;
            }
        });


        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(BillCalculator.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                startDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(BillCalculator.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                endDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });


    }

    private long calNumberOfdays(String start, String end) {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        long diff = 0;
        try {
            Date date1 = myFormat.parse(start);
            Date date2 = myFormat.parse(end);
            diff = date2.getTime() - date1.getTime();

            System.out.println("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff;
    }

    private String getBillCalculationPattern(String numOfUnits) {
        double total;
        int units = Integer.parseInt(numOfUnits);
        if (units < 60) {
            total = 7.85 * units;
            return "7.85 X " + units + "\t\t\t = Rs." + total;
        } else if (units < 90) {
            double t1 = 7.85 * 60;
            double more = units - 60;
            double t2 = more * 10;
            total = t1 + t2;

            return "7.85 X 60 = Rs." + t1 + "\n10.0 X " + more + " \t\t= Rs." + total;
        } else {
            double t1 = 7.85 * 60;
            double t2 = 10 * 30;
            double more = units - 90;
            double t3 = more * 27.75;

            total = t1 + t2 + t3;

            return "7.85 X 60 = Rs." + t1 + "\n10.0 X 30 = Rs. " + t2 + "\n27.75 X " + more + "\t\t = Rs. " + total;
        }
    }

    public Boolean isUnitsValid(EditText text) {
        return !TextUtils.isEmpty(text.getText().toString());
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.billCalculatorStartDatePickerTextView) {

        } else if (view.getId() == R.id.billCalculatorEndDatePickerTextView) {

        } else if (view.getId() == R.id.calculateBillCalBtn) {
            wayOfBillCalculation.setText(getBillCalculationPattern(unitsInput.getText().toString()));
        }
    }
}
