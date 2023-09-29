package ir.sina.toolbarbuilder

import android.content.Context
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider

class ToolbarBuilder(private val context: Context) {
    private var toolbar: Toolbar? = null
    private var menuResId: Int = 0
    private var menuProvider: MenuProvider? = null
    private var menuHost: MenuHost? = null

    fun withToolbar(toolbar: Toolbar): ToolbarBuilder {
        this.toolbar = toolbar
        return this
    }

    fun withMenu(menuResId: Int): ToolbarBuilder {
        this.menuResId = menuResId
        return this
    }

    fun withMenuProvider(menuProvider: MenuProvider): ToolbarBuilder {
        this.menuProvider = menuProvider
        return this
    }

    fun withMenuHost(menuHost: MenuHost): ToolbarBuilder {
        this.menuHost = menuHost
        return this
    }

    fun build() {
        toolbar?.let { toolbar ->
            toolbar.inflateMenu(menuResId)
            toolbar.setOnMenuItemClickListener { item ->
                menuProvider?.onMenuItemSelected(item) ?: false
            }
            val menuInflater = MenuInflater(context)
            menuProvider?.onCreateMenu(toolbar.menu, menuInflater)
        }
    }
}