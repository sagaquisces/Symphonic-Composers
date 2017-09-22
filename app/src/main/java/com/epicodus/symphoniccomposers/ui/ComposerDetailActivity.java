package com.epicodus.symphoniccomposers.ui;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.epicodus.symphoniccomposers.R;
import com.epicodus.symphoniccomposers.adapters.ComposerPagerAdapter;
import com.epicodus.symphoniccomposers.models.SymphonyComposer;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ComposerDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private ComposerPagerAdapter adapterViewPager;
    ArrayList<SymphonyComposer> mComposers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composer_detail);
        ButterKnife.bind(this);

        mComposers = Parcels.unwrap(getIntent().getParcelableExtra("composers"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new ComposerPagerAdapter(getSupportFragmentManager(), mComposers);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
