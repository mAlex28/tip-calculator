package com.example.tipcalculatorv0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private TipCalculator tipCalc;
    public NumberFormat money = NumberFormat.getCurrencyInstance();
    private EditText billEditText;
    private EditText tipEditText;
    private EditText totalPerPersonEditText;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        tipCalc = new TipCalculator(0.17f, 100.0f, 100f);
        setContentView(R.layout.activity_main);

        billEditText = (EditText) findViewById(R.id.amount_bill);
        tipEditText = (EditText) findViewById(R.id.amount_tip_percent);
        totalPerPersonEditText = (EditText) findViewById(R.id.no_of_persons);

        TextChangeHandler tch = new TextChangeHandler();
        billEditText.addTextChangedListener(tch);
        tipEditText.addTextChangedListener(tch);
        totalPerPersonEditText.addTextChangedListener(tch);
    }

    //called when the user enters the values
    public void calculate(){
        String billString = billEditText.getText().toString();
        String tipString = tipEditText.getText().toString();
        String totalPerPersonString = totalPerPersonEditText.getText().toString();

        TextView tipTextView = (TextView) findViewById(R.id.amount_tip);
        TextView totalTextView = (TextView) findViewById(R.id.amount_total);
        TextView totalPerPersonTextView = (TextView) findViewById(R.id.total_per_person);

        try {
            //convert billString, tipString, totalPerPersonString to floats
            float billAmount = Float.parseFloat(billString);
            int tipPercent = Integer.parseInt(tipString);
            int totalPerPersonAmount = Integer.parseInt(totalPerPersonString);

            //update the model
            tipCalc.setBill(billAmount);
            tipCalc.setTip(0.01f * tipPercent);
            tipCalc.setNo_Of_People(totalPerPersonAmount);

            //ask model to calculate tip, total amounts and total per person
            float tip = tipCalc.tipAmount();
            float total = tipCalc.totalAmount();
            float totalPerPerson = tipCalc.totalPerPerson();

            //update the view with formatted tip, total amounts and total per person
            tipTextView.setText(money.format(tip));
            totalTextView.setText(money.format(total));
            totalPerPersonTextView.setText(money.format(totalPerPerson));
        } catch (NumberFormatException nfe) {
            //pop up an alert view

        }
    }

    private class TextChangeHandler implements TextWatcher {
        public void afterTextChanged(Editable e){
            calculate();
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after){
        }

        public void onTextChanged(CharSequence s, int start, int before, int after){
        }
    }
}