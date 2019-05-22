package hr.etfos.davorlukic.taskie.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.etfos.davorlukic.taskie.ui.fragments.AboutAuthorFragment
import hr.etfos.davorlukic.taskie.ui.fragments.AboutApplicationFragment

class TaskPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> AboutApplicationFragment.newInstance()
            else -> AboutAuthorFragment.newInstance()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Application"
            else -> "Author"
        }
    }

    override fun getCount() = 2
}