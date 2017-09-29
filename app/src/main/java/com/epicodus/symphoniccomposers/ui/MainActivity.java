package com.epicodus.symphoniccomposers.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private FirebaseAuth.AuthStateListener mAuthListener;

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
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
//                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                } else {

                }

            }
        };

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
        mAuth.addAuthStateListener(mAuthListener);
        if(currentUser == null){

            Intent startIntent = new Intent(MainActivity.this, StartActivity.class);
            startActivity(startIntent);
            finish();
        } else {

        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View v) {
        if(v == mFindComposersButton) {
            String country = String.valueOf(mCountriesSpinner.getSelectedItem());
            int selectedPosition = mCountriesSpinner.getSelectedItemPosition();
            mSelectedPosition = selectedPosition;
            if (mSelectedPosition == 0 || selectedPosition == 1) {
                country = "All";
                addToSharedPreferences(1);
            } else if (selectedPosition > 0) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void addToSharedPreferences(int selectedPosition) {
        mEditor.putInt(Constants.PREFERENCES_COUNTRY_POSITION, selectedPosition).apply();
    }
}


