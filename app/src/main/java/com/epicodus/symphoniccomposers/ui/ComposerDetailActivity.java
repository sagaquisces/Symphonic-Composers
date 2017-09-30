package com.epicodus.symphoniccomposers.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.epicodus.symphoniccomposers.Constants;
import com.epicodus.symphoniccomposers.R;
import com.epicodus.symphoniccomposers.adapters.ComposerPagerAdapter;
import com.epicodus.symphoniccomposers.models.SymphonyComposer;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ComposerDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private ComposerPagerAdapter adapterViewPager;
    ArrayList<SymphonyComposer> mComposers = new ArrayList<>();
    private String mSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composer_detail);
        ButterKnife.bind(this);

        mComposers = Parcels.unwrap(getIntent().getParcelableExtra(Constants.EXTRA_KEY_COMPOSERS));
        int startingPosition = getIntent().getIntExtra(Constants.EXTRA_KEY_POSITION, 0);
        mSource = getIntent().getStringExtra(Constants.KEY_SOURCE);

        adapterViewPager = new ComposerPagerAdapter(getSupportFragmentManager(), mComposers, mSource);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
