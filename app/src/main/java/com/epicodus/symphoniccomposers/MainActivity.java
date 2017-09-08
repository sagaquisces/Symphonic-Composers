package com.epicodus.symphoniccomposers;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.findComposersButton) Button mFindComposersButton;
    @Bind(R.id.countryEditText) EditText mCountryEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mFindComposersButton.setOnClickListener(this);

        Typeface danceFont = Typeface.createFromAsset(getAssets(), "fonts/dancing-script.otf");
        mAppNameTextView.setTypeface(danceFont);
    }

    @Override
    public void onClick(View v) {
        if(v == mFindComposersButton) {
            String country = mCountryEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, ComposersActivity.class);
            intent.putExtra("country", country);
            startActivity(intent);
        }
    }
}
