package com.epicodus.symphoniccomposers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button mFindComposersButton;
    private EditText mCountryEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCountryEditText = (EditText) findViewById(R.id.countryEditText);
        mFindComposersButton = (Button) findViewById(R.id.findComposersButton);
        mFindComposersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country = mCountryEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, ComposersActivity.class);
                intent.putExtra("country", country);
                startActivity(intent);
            }
        });
    }
}
