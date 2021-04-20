package it.feio.android.omninotes.matchers

import android.view.View
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

open class CategoryNameMatcher private constructor(
        private val expectedCategoryName: String
) : TypeSafeMatcher<View>(View::class.java){

    companion object {
        fun withCategoryName(categoryName: String) = CategoryNameMatcher(categoryName)
    }

    override fun describeTo(description: Description?) {
        TODO("Not yet implemented")
    }

    override fun matchesSafely(item: View?): Boolean {
        TODO("Not yet implemented")
    }

}