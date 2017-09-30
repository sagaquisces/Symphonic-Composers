package com.epicodus.symphoniccomposers.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.symphoniccomposers.models.SymphonyComposer;
import com.epicodus.symphoniccomposers.ui.ComposerDetailFragment;

import java.util.ArrayList;

/**
 * Created by Guest on 9/22/17.
 */

public class ComposerPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<SymphonyComposer> mComposers;

    public ComposerPagerAdapter(FragmentManager fm, ArrayList<SymphonyComposer> composers) {
        super(fm);
        mComposers = composers;
    }

    @Override
    public Fragment getItem(int position) {
        return ComposerDetailFragment.newInstance(mComposers, position);
    }

    @Override
    public int getCount() {
        return mComposers.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mComposers.get(position).getName();
    }
}
