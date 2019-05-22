package hr.etfos.davorlukic.taskie.ui.activities

import hr.etfos.davorlukic.taskie.R
import hr.etfos.davorlukic.taskie.common.EXTRA_SCREEN_TYPE
import hr.etfos.davorlukic.taskie.common.EXTRA_TASK_ID
import hr.etfos.davorlukic.taskie.common.gone
import hr.etfos.davorlukic.taskie.ui.activities.base.BaseActivity
import hr.etfos.davorlukic.taskie.ui.fragments.TaskDetailsFragment
import kotlinx.android.synthetic.main.activity_main.*

class ContainerActivity: BaseActivity() {

    override fun getLayoutResourceId() = R.layout.activity_main

    override fun setUpUi() {
        val screenType = intent.getStringExtra(EXTRA_SCREEN_TYPE)
        val id = intent.getIntExtra(EXTRA_TASK_ID, -1)
        if (screenType.isNotEmpty()) {
            when (screenType) {
                SCREEN_TASK_DETAILS -> showFragment(TaskDetailsFragment.newInstance(id))
            }
        } else {
            finish()
        }

        nav_view.gone()

    }

    companion object{
        const val SCREEN_TASK_DETAILS = "task_details"
    }
}

