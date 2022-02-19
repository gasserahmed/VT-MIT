package edu.vt.cs.cs5254.dreamcatcher

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BaseDreamCatcherListDetailTest {

    @get:Rule
    var myActivityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    // ==========================================================
    // Please ensure your application passes these tests
    // before submitting your project
    // ==========================================================

    @Test
    fun base_appContextGivesCorrectPackageName() {
        val appContext: Context = androidx.test.core.app.ApplicationProvider.getApplicationContext()
        Assert.assertEquals("edu.vt.cs.cs5254.dreamcatcher", appContext.packageName)
    }

    @Test
    fun base_listViewHasDreamNamedDream0() {
        onView(withText("Dream #0"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun base_listViewDream1IsNamedDream1() {
        onView(withId(R.id.dream_recycler_view))
            .check(matches(
                BaseDreamCatcherListDetailTest.atPosition(
                    1,
                    hasDescendant(withText("Dream #1"))
                )
            ))
    }

    @Test
    fun base_listViewDream0HasNoIcon() {
        onView(withId(R.id.dream_recycler_view))
            .check(matches(
                BaseDreamCatcherListDetailTest.atPosition(
                    0,
                    hasDescendant(withTagValue(`is`(0)))
                )
            ))
    }

    @Test
    fun base_listViewDream1HasDeferredIcon() {
        onView(withId(R.id.dream_recycler_view))
            .check(matches(
                BaseDreamCatcherListDetailTest.atPosition(
                    1,
                    hasDescendant(withTagValue(`is`(R.drawable.dream_deferred_icon)))
                )
            ))
    }

    @Test
    fun base_detailViewDream2HasReflectionText() {
        onView(withId(R.id.dream_recycler_view))
            .perform(actionOnItemAtPosition<DreamListFragment.DreamHolder>(2, click()))
        onView(withId(R.id.dream_entry_1_button))
            .check(matches(withText(containsString("Dream Entry 2A"))))
    }

    @Test
    fun base_detailViewDream2HasTitleDream2() {
        onView(withId(R.id.dream_recycler_view))
            .perform(actionOnItemAtPosition<DreamListFragment.DreamHolder>(2, click()))
        onView(withId(R.id.dream_title_text))
            .check(matches(withText("Dream #2")))
    }

    @Test
    fun base_detailViewDream2Button3HasTextFulfilled() {
        onView(withId(R.id.dream_recycler_view))
            .perform(actionOnItemAtPosition<DreamListFragment.DreamHolder>(2, click()))
        onView(withId(R.id.dream_entry_3_button))
            .check(matches(anyOf(
                withText(containsString("Fulfilled")),
                withText(containsString("fulfilled")),
                withText(containsString("FULFILLED")))))
    }

    @Test
    fun base_detailViewDream2ClickFulfilledClickBack_ListViewDream2HasNoIcon() {
        onView(withId(R.id.dream_recycler_view))
            .perform(actionOnItemAtPosition<DreamListFragment.DreamHolder>(2, click()))
        onView(withId(R.id.dream_fulfilled_checkbox))
            .perform(click())
        Espresso.pressBack()
        onView(withId(R.id.dream_recycler_view))
            .check(matches(
                BaseDreamCatcherListDetailTest.atPosition(
                    2,
                    hasDescendant(withTagValue(`is`(0)))
                )
            ))
    }

    @Test
    fun base_detailViewDream0ClickFulfilledClickBack_ListViewDream2HasFulfilledIcon() {
        onView(withId(R.id.dream_recycler_view))
            .perform(actionOnItemAtPosition<DreamListFragment.DreamHolder>(0, click()))
        onView(withId(R.id.dream_fulfilled_checkbox))
            .perform(click())
        Espresso.pressBack()
        onView(withId(R.id.dream_recycler_view))
            .check(matches(
                BaseDreamCatcherListDetailTest.atPosition(
                    0,
                    hasDescendant(withTagValue(`is`(R.drawable.dream_fulfilled_icon)))
                )
            ))
    }

    companion object {
        private fun atPosition(
            @Suppress("SameParameterValue") position: Int,
            itemMatcher: Matcher<View?>
        ): Matcher<View?> {
            return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
                override fun describeTo(description: Description) {
                    description.appendText("has item at position $position: ")
                    itemMatcher.describeTo(description)
                }

                override fun matchesSafely(view: RecyclerView): Boolean {
                    val viewHolder = view.findViewHolderForAdapterPosition(position)
                        ?: // has no item on such position
                        return false
                    return itemMatcher.matches(viewHolder.itemView)
                }
            }
        }
    }
}