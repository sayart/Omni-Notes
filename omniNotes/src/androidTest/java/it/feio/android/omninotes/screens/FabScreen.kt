package it.feio.android.omninotes.screens

import com.agoda.kakao.text.KButton
import com.kaspersky.kaspresso.screens.KScreen
import it.feio.android.omninotes.MainActivity
import it.feio.android.omninotes.R
import it.feio.android.omninotes.models.views.Fab

object FabScreen : KScreen<FabScreen>(){
    override val layoutId: Int?
        get() = R.layout.fab
    override val viewClass: Class<*>?
        get() = Fab::class.java

    val fabExpandMenuButton = KButton{
        withId(R.id.fab_expand_menu_button)
        withParent {
            withId(R.id.fab)
        }
    }

    val fabNoteButton = KButton{
        withId(R.id.fab_note)
        withParent {
            withId(R.id.fab)
        }
    }

}