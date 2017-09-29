package com.epicodus.symphoniccomposers.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.epicodus.symphoniccomposers.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ComposerListActivity extends AppCompatActivity {

    @Bind(R.id.countryTextView) TextView mCountryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composers);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String country = intent.getStringExtra("country");
        String message = "";
        if (country.contains("All")){
            message = "Here are all symphonic composers in our list";
        } else {
            message = "Here are all the %s composers";
        }
        mCountryTextView.setText(String.format(message, country));


    }


}
