/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package au.com.tangke.tram.ui

import android.support.test.espresso.Espresso
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4


import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import au.com.tangke.tram.R
import au.com.tangke.tram.util.RecyclerViewIdlingResource

import au.com.tangke.tram.util.atPosition
import org.hamcrest.Matchers.containsString


/**
 * Tests for the notes screen, the main screen which contains a grid of all notes.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class TramFragmentTest {

    /**
     * [ActivityTestRule] is a JUnit [@Rule][Rule] to launch your activity under test.
     *
     *
     *
     * Rules are interceptors which are executed for each test method and are important building
     * blocks of Junit tests.
     */
    @get:Rule
    public var mTramActivityTestRule = ActivityTestRule<TramActivity>(TramActivity::class.java)


    @Test
    @Throws(Exception::class)
    fun clickRefreshButton_showTramList() {

        //click refreshButton button
        onView(withId(R.id.refreshButton)).perform(click())

        //wait until northRecyclerView has child items
        val idlingResource = RecyclerViewIdlingResource(mTramActivityTestRule.activity.findViewById(R.id.northRecyclerView))
        Espresso.registerIdlingResources(idlingResource);

        //check the first items include a text "minutes"
        onView(withId(R.id.northRecyclerView))
                .check(matches(atPosition(0, hasDescendant(withText(containsString("minutes"))))))

        Espresso.unregisterIdlingResources(idlingResource)

    }


}