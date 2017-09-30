package com.epicodus.symphoniccomposers.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.epicodus.symphoniccomposers.Constants;
import com.epicodus.symphoniccomposers.R;
import com.epicodus.symphoniccomposers.models.SymphonyComposer;
import com.epicodus.symphoniccomposers.util.OnComposerSelectedListener;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ComposerListActivity extends AppCompatActivity implements OnComposerSelectedListener {

    @Bind(R.id.countryTextView) TextView mCountryTextView;
    private Integer mPosition;
    ArrayList<SymphonyComposer> mComposers;
    String mSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composers);

        ButterKnife.bind(this);

        if (savedInstanceState  != null) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mPosition = savedInstanceState.getInt(Constants.EXTRA_KEY_POSITION);
                mComposers = Parcels.unwrap(savedInstanceState.getParcelable(Constants.EXTRA_KEY_COMPOSERS));
                mSource = savedInstanceState.getString(Constants.KEY_SOURCE);

                if (mPosition != null && mComposers != null) {
                    Intent intent = new Intent(this, ComposerDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, mPosition);
                    intent.putExtra(Constants.EXTRA_KEY_COMPOSERS, Parcels.wrap(mComposers));
                    intent.putExtra(Constants.KEY_SOURCE, mSource);
                    startActivity(intent);
                }
            }
        }

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mPosition != null && mComposers != null) {
            outState.putInt(Constants.EXTRA_KEY_POSITION, mPosition);
            outState.putParcelable(Constants.EXTRA_KEY_COMPOSERS, Parcels.wrap(mComposers));
            outState.putString(Constants.KEY_SOURCE, mSource);
        }
    }


    @Override
    public void onComposerSelected(Integer position, ArrayList<SymphonyComposer> composers, String source) {
        mPosition = position;
        mComposers = composers;
        mSource = source;
    }

}
