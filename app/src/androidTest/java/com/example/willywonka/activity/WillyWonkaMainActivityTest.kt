package com.example.willywonka.activity


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.willywonka.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class WillyWonkaMainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(WillyWonkaMainActivity::class.java)

    @Test
    fun willyWonkaMainActivityTest() {
        val recyclerView = onView(
            withId(R.id.rv_oompa_loompa_workers)
            )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(4, click()))

        val materialTextView = onView(
            allOf(
                withId(R.id.btn_desc), withText("DESCRIPTION"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.my_nav_host_fragment),
                        0
                    ),
                    16
                ),
                isDisplayed()
            )
        )
        materialTextView.perform(click())

        val cardView = onView(
            allOf(
                withId(R.id.cv_email),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.my_nav_host_fragment),
                        0
                    ),
                    17
                ),
                isDisplayed()
            )
        )
        cardView.perform(click())

        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Desplazarse hacia arriba"),
                childAtPosition(
                    allOf(
                        withId(R.id.toolbar),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
