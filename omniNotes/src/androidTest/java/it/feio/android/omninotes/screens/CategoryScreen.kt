package it.feio.android.omninotes.screens

import android.view.View
import android.view.ViewGroup
import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.kaspersky.kaspresso.screens.KScreen
import it.feio.android.omninotes.CategoryActivity
import it.feio.android.omninotes.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object CategoryScreen :KScreen<CategoryScreen>() {
    override val layoutId: Int?
        get() = R.layout.toolbar
    override val viewClass: Class<*>?
        get() = CategoryActivity::class.java

    val menuCategoryToolbarButton = KButton {
        withId(R.id.menu_category)
        withContentDescription(R.string.category)
        isDisplayed()
    }
    val drawerOpenButton = KButton {
        withContentDescription(R.string.drawer_open)
        withParent {
            withId(R.id.toolbar)
        }
    }
    val title = KTextView{
        withId(R.id.title)
        withText("Cat")
    }

    val detailTitle = KEditText {
        withId(R.id.detail_title)
        withParent {
            withId(R.id.title_wrapper)
            withParent {
                withId(R.id.detail_tile_card)
            }
        }
    }

    val addCategoryButtonOnMaterialDialog = KButton {
//        isRoot()
        withText(R.string.add_category)
    }

    val categoryTitleOnMaterialDialog = KEditText{
        withId(R.id.category_title)
    }

    val okButtonOnMaterialDialog = KButton{
        withId(R.id.save)
        withText("Ok")
        isDisplayed()
    }

    val colorChooserImage = KImageView {
        withId(R.id.color_chooser)
        isDisplayed()
    }

    val customButton = KButton{
        withId(R.id.md_buttonDefaultNeutral)
        withText("Custom")

    }



//    allOf(withId(), withText("Custom"),
//    childAtPosition(
//    allOf(withId(R.id.md_root),
//    childAtPosition(
//    withId(android.R.id.content),
//    0)),
//    2)))






}