package swing.dev.cats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity(), MyDrawerController {
    override lateinit var navController: NavController
    override var toolbarMenu: Menu? = null
    private var convertMenuItem: MenuItem? = null
    private var findMenuItem: MenuItem? = null
    private var closeMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)
        navController = this.findNavController(R.id.navControllerFragment)
        setupWithNavController(toolbar, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navControllerFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item)
    }

    override fun setTitle(title: String) {
        toolbar?.title = title
    }

    override fun showContent() {
        findMenuItem?.isVisible = true
        closeMenuItem?.isVisible = false
        convertMenuItem?.isVisible = true
        flLoader.visibility = View.GONE
    }

    override fun showSelectionMode() {
        findMenuItem?.isVisible = false
        closeMenuItem?.isVisible = true
        convertMenuItem?.isVisible = false
        flLoader?.visibility = View.GONE
    }

    override fun showLoading() {
        flLoader?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        flLoader?.visibility = View.GONE
    }

    override fun onBackPressed() {

    }

}