package com.example.tipcalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener,
        SeekBar.OnSeekBarChangeListener{

    private TextView amount_text_view, total_view, tip_view, percent_view;
    private SeekBar percentage_seek_bar;
    private EditText amount_edit;

    private NumberFormat currencyFormat;
    private NumberFormat percentFormat;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init()
    {
        amount_edit = findViewById(R.id.amountEditText);
        amount_edit.addTextChangedListener(textWatcher);
        amount_text_view = findViewById(R.id.amountTextView);

        percentage_seek_bar = findViewById(R.id.percentSeekBar);
        percentage_seek_bar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) this);

        currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        percentFormat = NumberFormat.getPercentInstance();

        percent_view = findViewById(R.id.percentTextView);
        percent_view.setText(percentFormat.format(0.15));

        total_view = findViewById(R.id.totalTextView);
        tip_view = findViewById(R.id.tipTextView);



    }


    TextWatcher textWatcher = new TextWatcher() {

        @Override

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            double amount_entered = Double.parseDouble(String.valueOf(amount_edit.getText()));


            amount_text_view.setText((currencyFormat.format(amount_entered)));

            double current_percentage = percentage_seek_bar.getProgress();
            double tip = (current_percentage/100)*amount_entered;

            tip_view.setText(currencyFormat.format(tip));
            total_view.setText(currencyFormat.format(tip + amount_entered));
        }

        @Override

        public void afterTextChanged(Editable editable) {

        }

    };

    // Text change listener
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    // On click listener
    @Override
    public void onClick(View view) {

    }

    //Seek bar functionality
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        double val = progress/100.0;

        percent_view.setText(percentFormat.format(val));
        double amount_entered = Double.parseDouble(String.valueOf(amount_edit.getText()));


        amount_text_view.setText((currencyFormat.format(amount_entered)));

        double tip = val*amount_entered;

        tip_view.setText(currencyFormat.format(tip));
        double total = tip + amount_entered;
        total_view.setText(currencyFormat.format(total));

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}