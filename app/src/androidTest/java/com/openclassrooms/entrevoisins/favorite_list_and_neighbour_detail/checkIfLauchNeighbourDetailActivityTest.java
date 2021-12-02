package com.openclassrooms.entrevoisins.favorite_list_and_neighbour_detail;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertThat;


import static java.util.regex.Pattern.matches;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import junit.framework.TestCase;

import org.hamcrest.Matcher;
import org.hamcrest.core.IsNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;


@RunWith(AndroidJUnit4.class)
public class checkIfLauchNeighbourDetailActivityTest extends TestCase {

    private ListNeighbourActivity mActivity;
    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);



    @Before
    public void setUp() throws Exception {

        mActivity = mActivityRule.getActivity();
        assertThat(mActivity,notNullValue());
        mApiService = DI.getNewInstanceApiService();
        mNeighbours = mApiService.getNeighbours();


    }

    @Test
    public void CheckIfWeViewTheDetailPageAndIfTheDataOfTheNeighborIsCorrect() {
        // Given : We click on the element at position 0
        onView(allOf(withId(R.id.list_neighbours), hasFocus()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        // When : The NeighbourDetailsActivity is view
        // We have added in ID, the number of the neighbour to check if the detail page is active
        onView(withId(R.id.telephone)).check(ViewAssertions.matches(isDisplayed()));
        // Then :  We check if the number view on the NeighbourDetailsActivity is correct
        onView(ViewMatchers.withId(R.id.telephone)).check(ViewAssertions
                .matches(withText(mNeighbours.get(0).getPhoneNumber())));


    }

    @Test
    public void CheckIfTheFavoriteNeighbourIsInTheFavoriteList() {
        // Given: Check that the favorites list is empty
        //onView(withContentDescription("Favorites")).perform(click());
        //onView(allOf(withId(R.id.list_neighbours), hasFocus())).check(withItemCount(0));
        // When : Click on a neighbour and add to favorite,  initial position 4
       // onView(withContentDescription("My neighbours")).perform(click());
        onView(allOf(withId(R.id.list_neighbours), hasFocus())).perform(RecyclerViewActions
                .actionOnItemAtPosition(4, click()));
        onView(ViewMatchers.withId(R.id.favorite_button)).perform(click());
        // Then : Check if the favorites list has a new neighbour and if it is the one of position 4
        onView(ViewMatchers.withId(R.id.back_button)).perform(click());
        onView(withContentDescription("Favorites")).perform(click());
        onView(allOf(withId(R.id.list_neighbours), hasFocus())).check(ViewAssertions
                .matches(hasMinimumChildCount(1)));
        //onView(ViewMatchers.withId(R.id.item_list_name)).check(ViewAssertions
          // .matches(withText(mNeighbours.get(4).getName())));



    }
    @Test
    public void CheckIfRetun() {
        onView(allOf(withId(R.id.list_neighbours), hasFocus())).perform(RecyclerViewActions
                .actionOnItemAtPosition(0, click()));

        onView(ViewMatchers.withId(R.id.favorite_button)).perform(click());

        //pressBack();
        //onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());
        onView(ViewMatchers.withId(R.id.back_button)).perform(click());
        onView(withId(R.id.item_list_name)).check(ViewAssertions.matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.back_button)).perform(click());
      //


        }






    }


   // @After
   // public void tearDown() throws Exception {


   // }


