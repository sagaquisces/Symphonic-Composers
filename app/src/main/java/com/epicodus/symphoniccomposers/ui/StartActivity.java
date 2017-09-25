package com.epicodus.symphoniccomposers.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.symphoniccomposers.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.startRegisterButton) Button mRegBtn;
    @Bind(R.id.startLoginButton) Button mLoginBtn;
    @Bind(R.id.startNameTextView) TextView mStartNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ButterKnife.bind(this);

        Typeface danceFont = Typeface.createFromAsset(getAssets(), "fonts/dancing-script.otf");
        mStartNameTextView.setTypeface(danceFont);

        mRegBtn.setOnClickListener(this);
        mLoginBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mRegBtn) {
            Intent reg_intent = new Intent(StartActivity.this, RegisterActivity.class);
            startActivity(reg_intent);
        }

        if (v == mLoginBtn) {
            Intent login_intent = new Intent(StartActivity.this, LoginActivity.class);
            startActivity(login_intent);
        }
    }
}
