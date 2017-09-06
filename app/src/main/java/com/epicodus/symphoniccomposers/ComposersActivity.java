package com.epicodus.symphoniccomposers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ComposersActivity extends AppCompatActivity {
    private TextView mCountryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composers);
        mCountryTextView = (TextView) findViewById(R.id.countryTextView);
        Intent intent = getIntent();
        String country = intent.getStringExtra("country");
        mCountryTextView.setText("Here are all the composers from: " + country);
    }
}
