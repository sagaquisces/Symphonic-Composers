package com.epicodus.symphoniccomposers;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Guest on 9/8/17.
 */

public class RestaurantsActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<ComposersActivity> activityTestRule = new ActivityTestRule<>(ComposersActivity.class);

    @Test
    public void listItemClickDisplaysToastWithCorrectComposer() {
        View activityDecorView = activityTestRule.getActivity().getWindow().getDecorView();
        String composerName = "Ludwig van Beethoven";
        onData(anything())
                .inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .perform(click());
        onView(withText(composerName)).inRoot(withDecorView(not(activityDecorView)))
                .check(matches(withText(composerName)));
    }

}
