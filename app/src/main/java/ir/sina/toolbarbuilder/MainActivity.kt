package ir.sina.toolbarbuilder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import ir.sina.toolbarbuilder.R.id.action_search


class MainActivity : AppCompatActivity() {
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val menuHost: MenuHost = this

        val menuProvider = object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_toolbar, menu)
                val searchItem = menu.findItem(action_search)
                searchView = searchItem.actionView as SearchView

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        Log.e("TAG", "onQueryTextChange: $newText")
                        return true
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_sort_by_name -> {
                        Toast.makeText(this@MainActivity, "name", Toast.LENGTH_SHORT).show()
                        true
                    }

                    R.id.action_sort_by_date -> {
                        Toast.makeText(this@MainActivity, "date", Toast.LENGTH_SHORT).show()
                        true
                    }

                    else -> false
                }
            }
        }
        menuHost.addMenuProvider(menuProvider, this)

        ToolbarBuilder(this@MainActivity)
            .withToolbar(toolbar)
            .withMenu(R.menu.menu_toolbar)
            .withMenuProvider(menuProvider).build()
    }
}