package com.epicodus.symphoniccomposers.util;

import com.epicodus.symphoniccomposers.models.SymphonyComposer;

import java.util.ArrayList;

/**
 * Created by michaeldunlap on 9/30/17.
 */

public interface OnComposerSelectedListener {
    public void onComposerSelected(Integer position, ArrayList<SymphonyComposer> symphonyComposers, String source);
}
