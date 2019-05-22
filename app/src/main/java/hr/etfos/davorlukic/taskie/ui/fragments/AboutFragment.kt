package hr.etfos.davorlukic.taskie.ui.fragments

import android.os.Bundle
import android.view.View
import hr.etfos.davorlukic.taskie.R
import hr.etfos.davorlukic.taskie.ui.adapters.TaskPagerAdapter
import hr.etfos.davorlukic.taskie.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : BaseFragment() {

    override fun getLayoutResourceId() = R.layout.fragment_about

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPager()
    }

    private fun setUpPager() {
        viewPager.adapter = TaskPagerAdapter(childFragmentManager)
        tab.setupWithViewPager(viewPager)
    }

    companion object {
        fun newInstance() : AboutFragment {
            return AboutFragment()
        }
    }
}