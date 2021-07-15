package swing.dev.cats

import android.view.Menu
import androidx.navigation.NavController

interface MyDrawerController {
    var navController: NavController
    var toolbarMenu: Menu?
    fun setTitle(title: String)
    fun showContent()
    fun showSelectionMode()
    fun showLoading()
    fun hideLoading()
}