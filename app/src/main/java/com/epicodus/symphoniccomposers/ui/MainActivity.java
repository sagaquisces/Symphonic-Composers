package com.epicodus.symphoniccomposers.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.epicodus.symphoniccomposers.CustomOnItemSelectedListener;
import com.epicodus.symphoniccomposers.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.findComposersButton) Button mFindComposersButton;
//    @Bind(R.id.countryEditText) EditText mCountryEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.countriesSpinner) Spinner mCountriesSpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);



        mCountriesSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countries_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        mCountriesSpinner.setAdapter(adapter);


        mFindComposersButton.setOnClickListener(this);


        // Create an ArrayAdapter using the string array and a default spinner layout


        Typeface danceFont = Typeface.createFromAsset(getAssets(), "fonts/dancing-script.otf");
        mAppNameTextView.setTypeface(danceFont);
    }


    @Override
    public void onClick(View v) {
        if(v == mFindComposersButton) {
            String country = String.valueOf(mCountriesSpinner.getSelectedItem());
            Intent intent = new Intent(MainActivity.this, ComposersActivity.class);
            intent.putExtra("country", country);
            startActivity(intent);
        }
    }
}
