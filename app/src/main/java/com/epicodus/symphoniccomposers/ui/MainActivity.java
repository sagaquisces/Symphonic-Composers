package com.epicodus.symphoniccomposers.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.epicodus.symphoniccomposers.Constants;
import com.epicodus.symphoniccomposers.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

//import com.epicodus.symphoniccomposers.CustomOnItemSelectedListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private int mSelectedPosition;

    private FirebaseAuth mAuth;

    @Bind(R.id.findComposersButton) Button mFindComposersButton;
    @Bind(R.id.savedComposerButton) Button mSavedComposerButton;
//    @Bind(R.id.countryEditText) EditText mCountryEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.countriesSpinner) Spinner mCountriesSpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();


//        mCountriesSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countries_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        mCountriesSpinner.setAdapter(adapter);


        // Create an ArrayAdapter using the string array and a default spinner layout


        Typeface danceFont = Typeface.createFromAsset(getAssets(), "fonts/dancing-script.otf");
        mAppNameTextView.setTypeface(danceFont);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        mSelectedPosition = mSharedPreferences.getInt(Constants.PREFERENCES_COUNTRY_POSITION, -1);

        if (mSelectedPosition > 0) {
            mCountriesSpinner.setSelection(mSelectedPosition);
        }

        mFindComposersButton.setOnClickListener(this);
        mSavedComposerButton.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null){

            Intent startIntent = new Intent(MainActivity.this, StartActivity.class);
            startActivity(startIntent);
            finish();
        }
    }


    @Override
    public void onClick(View v) {
        if(v == mFindComposersButton) {
            String country = String.valueOf(mCountriesSpinner.getSelectedItem());
            int selectedPosition = mCountriesSpinner.getSelectedItemPosition();
            if (mSelectedPosition == -1 || selectedPosition == 0) {
                country = "All";
                addToSharedPreferences(1);
            } else if (selectedPosition > 0) {
                mSelectedPosition = selectedPosition;
                addToSharedPreferences(mSelectedPosition);
            }

            Intent intent = new Intent(MainActivity.this, ComposerListActivity.class);
            intent.putExtra("country", country);
            startActivity(intent);


        }
        if (v == mSavedComposerButton) {
            Intent intent = new Intent(MainActivity.this, SavedComposerListActivity.class);
            startActivity(intent);
        }
    }

    private void addToSharedPreferences(int selectedPosition) {
        mEditor.putInt(Constants.PREFERENCES_COUNTRY_POSITION, selectedPosition).apply();
    }
}


