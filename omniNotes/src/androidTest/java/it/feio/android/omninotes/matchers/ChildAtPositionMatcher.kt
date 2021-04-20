package it.feio.android.omninotes.matchers

import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

open class ChildAtPositionMatcher private constructor(
        private val parentMatcher: Matcher<View?>,
        private val position: Int
): TypeSafeMatcher<View>(){

    companion object {
//        fun childAtPosition()
    }

    override fun describeTo(description: Description?) {
        description?.appendText("Child at position $position in parent ")
        parentMatcher.describeTo(description)
    }

    override fun matchesSafely(view: View?): Boolean {
        val parent = view?.parent
             return (parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position))
    }

//    fun childAtPosition(
//            parentMatcher: Matcher<View?>, position: Int): Matcher<View?>? {
//        return object : TypeSafeMatcher<View>() {
//            override fun describeTo(description: Description) {
//                description.appendText("Child at position $position in parent ")
//                parentMatcher.describeTo(description)
//            }
//
//            override fun matchesSafely(view: View): Boolean {
//                val parent = view.parent
//                return (parent is ViewGroup && parentMatcher.matches(parent)
//                        && view == parent.getChildAt(position))
//            }
//        }
//    }

}