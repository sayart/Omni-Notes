package it.feio.android.omninotes.ui

import android.Manifest.permission
import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import com.pixplicity.easyprefs.library.Prefs
import de.greenrobot.event.EventBus
import it.feio.android.omninotes.BaseAndroidTestCase
import it.feio.android.omninotes.MainActivity
import it.feio.android.omninotes.R
import it.feio.android.omninotes.async.bus.CategoriesUpdatedEvent
import it.feio.android.omninotes.db.DbHelper
import it.feio.android.omninotes.models.Category
import org.junit.*
import java.io.File
import java.util.*


abstract class BaseKaspressoTest : DocLocScreenshotTestCase(
        screenshotsDirectory = File("Pictures"),
        locales = "en"
) {
    companion object {
        init {
            // things that may need to be setup before companion class member variables are instantiated
        }

        // variables you initialize for the class just once:
        protected val PRESET_LOCALE = Locale(Locale.ENGLISH.toString())
        protected var dbHelper: DbHelper? = null
        protected var testContext: Context? = null
        protected var prefs: SharedPreferences? = null

        // variables you initialize for the class later in the @BeforeClass method:
//        lateinit var someClassLateVar: SomeResource

        @BeforeClass @JvmStatic
        open fun setUpBeforeClass() {
            testContext = ApplicationProvider.getApplicationContext()
            prefs = Prefs.getPreferences()
            dbHelper = DbHelper.getInstance(testContext)
        }

        @AfterClass
        @JvmStatic fun teardown() {
            // clean up after this class, leave nothing dirty behind
        }
    }

    // variables you initialize per instance of the test class:
//    val someInstanceVar = initializer()

    // variables you initialize per test case later in your @Before methods:
//    var lateinit someInstanceLateZVar: MyType

    @Rule @JvmField
    var permissionRule = GrantPermissionRule.grant(
            permission.ACCESS_COARSE_LOCATION, permission.ACCESS_FINE_LOCATION, permission.READ_EXTERNAL_STORAGE, permission.WRITE_EXTERNAL_STORAGE,
            permission.RECORD_AUDIO
    )

    @Rule @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java,
            false, false)

    @Before
    fun setUpBase() {
        prepareDatabase()
        prepareLocale()
        preparePreferences()
    }
//
    private fun preparePreferences() {
        prefs!!.edit().clear().commit()
    }

    private fun prepareDatabase() {
        dbHelper!!.getDatabase(true).delete(DbHelper.TABLE_NOTES, null, null)
        dbHelper!!.getDatabase(true).delete(DbHelper.TABLE_CATEGORY, null, null)
        dbHelper!!.getDatabase(true).delete(DbHelper.TABLE_ATTACHMENTS, null, null)
        Assert.assertFalse("Database MUST be writable", dbHelper!!.getDatabase(true).isReadOnly)
    }

    private fun prepareLocale() {
        Locale.setDefault(PRESET_LOCALE)
        val config = testContext!!.resources.configuration
        config.locale = PRESET_LOCALE
    }

    protected open fun createCategory(categoryName: String?) {
        val category = Category()
        category.name = categoryName
        category.color = testContext?.resources!!.getIntArray(R.array.material_colors)[0].toString()
        category.description = "testing category"
        dbHelper?.updateCategory(category)
        EventBus.getDefault().post(CategoriesUpdatedEvent())
    }
}