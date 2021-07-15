package swing.dev.cats.helpers

import androidx.fragment.app.FragmentActivity
import swing.dev.cats.base.fragments.BaseDialogFragment

fun showDialogFragment(newFragmentBaseBase: BaseDialogFragment, activity: FragmentActivity) {
    val ft = activity.supportFragmentManager.beginTransaction()
    newFragmentBaseBase.show(ft, "dialog")
}