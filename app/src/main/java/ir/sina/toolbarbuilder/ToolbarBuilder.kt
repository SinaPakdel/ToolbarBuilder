package ir.sina.toolbarbuilder

import android.content.Context
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment


class ToolbarBuilder(private val context: Context) {
    private var menuProvider: MenuProvider? = null
    private var toolbar: Toolbar? = null
    private var drawableIcon: Int = 0
    private var toolbarTitle: String? = null

    fun setToolbar(toolbar: Toolbar): ToolbarBuilder {
        this.toolbar = toolbar
        return this
    }

    fun setMenuProvider(provider: MenuProvider): ToolbarBuilder {
        menuProvider = provider
        return this
    }

    fun setNavigationIcon(drawableIcon: Int): ToolbarBuilder {
        this.drawableIcon = drawableIcon
        return this
    }

    fun setToolbarTitle(toolbarTitle: String): ToolbarBuilder {
        this.toolbarTitle = toolbarTitle
        return this
    }

    fun build(fragment: Fragment): Toolbar {
        val appCompatActivity = fragment.requireActivity() as AppCompatActivity
        toolbar?.let {
            appCompatActivity.setSupportActionBar(toolbar)
            appCompatActivity.supportActionBar?.apply {
                title = toolbarTitle
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(drawableIcon)
            }
        }
        toolbar?.menu?.let { menu ->
            menu.clear()
            menuProvider?.onCreateMenu(menu, MenuInflater(appCompatActivity))
        }
        return toolbar!!
    }
}

interface MenuProvider {
    fun onCreateMenu(menu: Menu, menuInflater: MenuInflater)
    fun onMenuItemSelected(menuItem: MenuItem): Boolean
}