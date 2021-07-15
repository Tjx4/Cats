package swing.dev.cats.base.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import swing.dev.cats.MyDrawerController

abstract class BaseFragment : Fragment() {
    protected lateinit var myDrawerController: MyDrawerController

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myDrawerController = activity as MyDrawerController
    }
}