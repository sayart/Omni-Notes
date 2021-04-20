package it.feio.android.omninotes.ui

import android.view.View
import android.view.ViewGroup
import androidx.test.ext.junit.runners.AndroidJUnit4
import it.feio.android.omninotes.db.DbHelper
import it.feio.android.omninotes.screens.CategoryScreen
import it.feio.android.omninotes.screens.FabScreen
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class CategoryLifecycleTestKaspresso : BaseKaspressoTest(){

    @Test
    fun kaspressoAddNewCategoryTest() {
        val categoryName = "Cat_" + Calendar.getInstance().timeInMillis

        before { activityTestRule.launchActivity(null) }
                .after { }
                .run {
                    step("Checking Fab Menu and move to Category menu") {
                        FabScreen {
                            fabExpandMenuButton {
                                isDisplayed()
                                click()
                            }
                            captureScreenshot("1. Fab menu opened")
                            fabNoteButton {
                                isDisplayed()
                                click()
                            }
                        }
                        captureScreenshot("2. Category opened")
                    }
                    step("Edit category") {
                        CategoryScreen {
                            menuCategoryToolbarButton {
                                click()
                            }
                            addCategoryButtonOnMaterialDialog {
                                isDisplayed()
                                click()
                            }
                            categoryTitleOnMaterialDialog {
                                typeText(categoryName)
                            }
                            okButtonOnMaterialDialog {
                                click()
                            }
                            detailTitle {
                                typeText("Note with new category")
                            }
                            drawerOpenButton {
                                isDisplayed()
                                click()
                            }
                        }
                        captureScreenshot("3. Note with new category created")
                    }
                    val categories = DbHelper.getInstance().categories

                    Assert.assertEquals(1, categories.size.toLong())
                    Assert.assertEquals(categoryName, categories[0].name)
                }
    }

    @Test
    fun kaspressoCheckCategoryCreation(){
//        val categoryName = "Cat_" + Calendar.getInstance().timeInMillis
        val categoryName = "Cat"

        createCategory(categoryName)

        before { activityTestRule.launchActivity(null) }
                .after { }
                .run {
                    step("Checking Category creation") {
                        CategoryScreen{
                            drawerOpenButton{
                                isDisplayed()
                                click()
                            }
//                          как передать в скрин  categoryName??
                            title{
                                hasText(categoryName)
                            }
                            captureScreenshot("Checking category creation")
                        }
                    }
                }
    }

    @Test
    fun kaspressoCategoryColorChange() {
        val categoryName = "Cat"
        createCategory(categoryName)

        before { activityTestRule.launchActivity(null) }
                .after { }
                .run {
                    step("Checking Category color changes") {
                        CategoryScreen{
                            drawerOpenButton{
                                isDisplayed()
                                click()
                            }
                            title{
                                longClick()
                            }
                            colorChooserImage{
                                click()
                            }
                            captureScreenshot("Checking choosing colors")



                        }
                    }
                }
    }








}