package com.epicodus.symphoniccomposers.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.epicodus.symphoniccomposers.R;

import org.w3c.dom.Text;

public class ComposerDetailActivity extends AppCompatActivity {
    private TextView mComposerDetailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composer_detail);

        mComposerDetailTextView = (TextView) findViewById(R.id.composerDetailTextView);

        Intent intent = getIntent();
        String composer = intent.getStringExtra("composer");
        mComposerDetailTextView.setText(composer);
    }
}
