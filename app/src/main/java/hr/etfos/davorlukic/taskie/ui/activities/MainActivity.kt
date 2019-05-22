package hr.etfos.davorlukic.taskie.ui.activities

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import hr.etfos.davorlukic.taskie.R
import hr.etfos.davorlukic.taskie.ui.interfaces.OptionsMenuListener
import hr.etfos.davorlukic.taskie.ui.activities.base.BaseActivity
import hr.etfos.davorlukic.taskie.ui.fragments.AboutFragment
import hr.etfos.davorlukic.taskie.ui.fragments.TasksFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var optionsMenuListener: OptionsMenuListener? = null

    override fun getLayoutResourceId() = R.layout.activity_main

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.sortAscending -> optionsMenuListener?.sortPriorityAscending()
            R.id.sortDescending -> optionsMenuListener?.sortPriorityDescending()
            R.id.clearAllTasksMenuItem -> showDeleteTasksAlertDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDeleteTasksAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.alertDialogTitleText)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton(R.string.alertPositiveButtonText) { _, _ -> optionsMenuListener?.deleteAllTasks()}
            .setNegativeButton(R.string.alertNegativeButtonText, null)
            .show()
    }


    override fun setUpUi() {
        val fragment = TasksFragment.newInstance()
        optionsMenuListener = fragment
        showFragment(fragment)

        val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home ->{
                    showFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_about ->{
                    showFragment(AboutFragment.newInstance())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}


