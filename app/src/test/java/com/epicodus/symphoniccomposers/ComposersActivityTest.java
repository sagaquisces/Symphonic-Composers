package com.epicodus.symphoniccomposers;

import android.os.Build;
import android.widget.ListView;

import com.epicodus.symphoniccomposers.ui.ComposersActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Guest on 9/8/17.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)

public class ComposersActivityTest {
    private ComposersActivity activity;
    private ListView mComposerListView;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(ComposersActivity.class);
        mComposerListView = (ListView) activity.findViewById(R.id.listView);
    }

    @Test
    public void composerListViewPopulates() {
        assertNotNull(mComposerListView.getAdapter());
        assertEquals(mComposerListView.getAdapter().getCount(), 12);
    }
}
