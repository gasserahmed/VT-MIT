package edu.vt.cs.cs5254.dreamcatcher

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BaseDreamCatcherCompleteTest {

    @get:Rule
    var myActivityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun init() {
        Intents.init()
    }

    @Before
    fun clearDatabase() {
        DreamRepository.get().apply {
            deleteAllDreamsInDatabase()
            deleteAllDreamEntriesInDatabase()
        }
    }

    @After
    fun teardown() {
        Intents.release()
    }

    // ==========================================================
    // Base Tests
    // ==========================================================

    @Test
    fun base_appContextGivesCorrectPackageName() {
        val appContext: Context = androidx.test.core.app.ApplicationProvider.getApplicationContext()
        Assert.assertEquals("edu.vt.cs.cs5254.dreamcatcher", appContext.packageName)
    }

    @Test
    fun base_createDream_CheckTitleFulfilledDeferred() {
        // create dream "My Dream" and select fulfilled
        // check title / fulfilled / deferred
        onView(withId(R.id.add_dream)).perform(click())
        onView(withId(R.id.dream_title_text)).perform(replaceText("My Dream"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.dream_fulfilled_checkbox)).perform(click())
        onView(withId(R.id.dream_title_text)).check(matches(withText("My Dream")))
        onView(withId(R.id.dream_fulfilled_checkbox)).check(matches(isChecked()))
        onView(withId(R.id.dream_deferred_checkbox)).check(matches(not(isEnabled())))
    }

    @Test
    fun base_createDream_CheckEntries() {
        // create dream "My Dream" and select fulfilled
        // check conceived entry and fulfilled entry
        onView(withId(R.id.add_dream)).perform(click())
        onView(withId(R.id.dream_title_text)).perform(replaceText("My Dream"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.dream_fulfilled_checkbox)).perform(click())
        onView(withId(R.id.dream_entry_recycler_view))
            .check(
                matches(
                    atPosition(
                        0, hasDescendant(
                            anyOf(
                                withText(containsString("Conceived")),
                                withText(containsString("conceived")),
                                withText(containsString("CONCEIVED"))
                            )
                        )
                    )
                )
            )
        onView(withId(R.id.dream_entry_recycler_view))
            .check(
                matches(
                    atPosition(
                        1, hasDescendant(
                            anyOf(
                                withText(containsString("Fulfilled")),
                                withText(containsString("fulfilled")),
                                withText(containsString("FULFILLED"))
                            )
                        )
                    )
                )
            )
    }

    @Test
    fun base_createDream_CheckListView() {
        // create dream "My Dream" and select fulfilled
        // check list view title and fulfilled icon
        onView(withId(R.id.add_dream)).perform(click())
        onView(withId(R.id.dream_title_text)).perform(replaceText("My Dream"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.dream_fulfilled_checkbox)).perform(click())
        Espresso.pressBack()
        onView(withId(R.id.dream_recycler_view))
            .check(
                matches(
                    atPosition(
                        0,
                        hasDescendant(withText("My Dream"))
                    )
                )
            )
        onView(withId(R.id.dream_recycler_view))
            .check(
                matches(
                    atPosition(
                        0,
                        hasDescendant(withTagValue(`is`(R.drawable.dream_fulfilled_icon)))
                    )
                )
            )
    }

    @Test
    fun base_createAndShareDream_CheckText() {
        // create dream "My Dream" and select fulfilled
        // select share dream
        // check intent for correct action, subject, and text

        onView(withId(R.id.add_dream)).perform(click())
        onView(withId(R.id.dream_title_text)).perform(replaceText("My Dream"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.dream_fulfilled_checkbox)).perform(click())

        intending(not(isInternal()))
            .respondWith(ActivityResult(Activity.RESULT_OK, null))

        onView(withId(R.id.share_dream)).perform(click())

        intended(
            allOf(
                hasAction(Intent.ACTION_CHOOSER),
                hasExtra(
                    `is`(Intent.EXTRA_INTENT),
                    allOf(
                        hasAction(Intent.ACTION_SEND),
                        hasExtraWithKey(Intent.EXTRA_SUBJECT),
                        hasExtraWithKey(Intent.EXTRA_TEXT),
                        hasExtra(
                            `is`(Intent.EXTRA_TEXT),
                            anyOf(
                                containsString("Fulfilled"),
                                containsString("fulfilled"),
                                containsString("FULFILLED")
                            )
                        )
                    )
                )
            )
        )
    }

    @Test
    fun base_createDreamAndTakePhoto_CheckImageView() {
        // create dream "My Dream" and select fulfilled
        // select take dream photo
        // check intents for correct action and output-uri
        onView(withId(R.id.add_dream)).perform(click())
        onView(withId(R.id.dream_title_text)).perform(replaceText("My Dream"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.dream_fulfilled_checkbox)).perform(click())
        intending(not(isInternal()))
            .respondWith(ActivityResult(Activity.RESULT_OK, null))
        onView(withId(R.id.take_dream_photo)).perform(click())
        intended(hasAction(MediaStore.ACTION_IMAGE_CAPTURE))
    }

    @Test
    fun base_createDreamAndReflections_CheckReflections() {
        // create dream "My Dream"
        // create "Reflection 1" and "Reflection 2"
        // select fulfilled
        // check reflections
        onView(withId(R.id.add_dream)).perform(click())
        onView(withId(R.id.dream_title_text)).perform(replaceText("My Dream"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.add_reflection_button)).perform(click())
        onView(withId(R.id.reflection_text)).perform(replaceText("Reflection 1"))
        onView(withText(android.R.string.ok)).perform(click())
        onView(withId(R.id.add_reflection_button)).perform(click())
        onView(withId(R.id.reflection_text)).perform(replaceText("Reflection 2"))
        onView(withText(android.R.string.ok)).perform(click())
        onView(withId(R.id.dream_fulfilled_checkbox)).perform(click())
        onView(withId(R.id.dream_entry_recycler_view))
            .check(
                matches(
                    atPosition(
                        1, hasDescendant(
                            withText(containsString("Reflection 1"))
                        )
                    )
                )
            )
        onView(withId(R.id.dream_entry_recycler_view))
            .check(
                matches(
                    atPosition(
                        2, hasDescendant(
                            withText(containsString("Reflection 2"))
                        )
                    )
                )
            )
    }

    @Test
    fun base_createDreamAndReflectionsDeleteReflection_CheckEntries() {
        // create dream "My Dream"
        // create "Reflection 1" and "Reflection 2"
        // select fulfilled
        // swipe to delete reflection 2
        // check entries
        onView(withId(R.id.add_dream)).perform(click())
        onView(withId(R.id.dream_title_text)).perform(replaceText("My Dream"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.add_reflection_button)).perform(click())
        onView(withId(R.id.reflection_text)).perform(replaceText("Reflection 1"))
        onView(withText(android.R.string.ok)).perform(click())
        onView(withId(R.id.add_reflection_button)).perform(click())
        onView(withId(R.id.reflection_text)).perform(replaceText("Reflection 2"))
        onView(withText(android.R.string.ok)).perform(click())
        onView(withId(R.id.dream_fulfilled_checkbox)).perform(click())

        // swipe left to delete
        onView(withId(R.id.dream_entry_recycler_view)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                swipeLeft()
            )
        )
        onView(withId(R.id.dream_entry_recycler_view))
            .check(
                matches(
                    atPosition(
                        1, hasDescendant(
                            withText(containsString("Reflection 1"))
                        )
                    )
                )
            )
        onView(withId(R.id.dream_entry_recycler_view))
            .check(
                matches(
                    atPosition(
                        2, hasDescendant(
                            anyOf(
                                withText(containsString("Fulfilled")),
                                withText(containsString("fulfilled")),
                                withText(containsString("FULFILLED"))
                            )
                        )
                    )
                )
            )
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