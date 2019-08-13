package com.manojbhadane;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.rule.ActivityTestRule;

import com.manojbhadane.ui.activity.main.MainActivity;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Objects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ListScreenUITestCases {

    @Rule
    public final ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
            MainActivity.class,
            true,
            true);

    @Test
    public void testCountryListVisible() {
        onView(withId(R.id.recyclerview))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    // test to show that data has been present in list and its scrollable
    @Test
    public void testCountryListScroll() {

        RecyclerView recyclerView = activityRule.getActivity().findViewById(R.id.recyclerview);
        int itemCount = Objects.requireNonNull(recyclerView.getAdapter()).getItemCount();

        onView(withId(R.id.recyclerview))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
                .perform(RecyclerViewActions.scrollToPosition(itemCount - 1));
    }

    // test for mError case, as test will fail when there is no data in list
    @Test
    public void testCountryListClick() {
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    // get list items count
    private int getListcount() {
        RecyclerView recyclerView = activityRule.getActivity().findViewById(R.id.recyclerview);
        return Objects.requireNonNull(recyclerView.getAdapter()).getItemCount();
    }
}
