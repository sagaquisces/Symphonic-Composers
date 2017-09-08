package com.epicodus.symphoniccomposers;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by Guest on 9/8/17.
 */

public class MyComposersArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mComposers;
    private String[] mSymphonies;

    public MyComposersArrayAdapter(Context mContext, int resource, String[] mComposers, String[] mSymphonies) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mComposers = mComposers;
        this.mSymphonies = mSymphonies;
    }

    @Override
    public Object getItem(int position) {
        String composer = mComposers[position];
        String symphonies = mSymphonies[position];
        return String.format("%s \nwrote %s symphony(ies).", composer, symphonies);
    }

    @Override
    public int getCount() {
        return mComposers.length;
    }
}
