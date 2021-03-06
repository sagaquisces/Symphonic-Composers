package com.epicodus.symphoniccomposers;

import android.support.test.rule.ActivityTestRule;

import com.epicodus.symphoniccomposers.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Guest on 9/8/17.
 */

public class MainActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validateEditText() {
        onView(withId(R.id.countryEditText)).perform(typeText("Germany"))
                .check(matches(withText("Germany")));
    }

    @Test
    public void countryIsSentToComposersActivity() {
        String country = "Germany";
        onView(withId(R.id.countryEditText)).perform(typeText(country));
        onView(withId(R.id.findComposersButton)).perform(click());
        onView(withId(R.id.countryTextView)).check(matches(withText("Here are all the composers from: " + country)));
    }
}
