package hr.etfos.davorlukic.taskie.ui.fragments

import androidx.fragment.app.Fragment
import hr.etfos.davorlukic.taskie.R
import hr.etfos.davorlukic.taskie.ui.fragments.base.BaseFragment

class AboutAuthorFragment : BaseFragment() {
    override fun getLayoutResourceId() = R.layout.fragment_about_author

    companion object{
        fun newInstance():Fragment{
            return AboutAuthorFragment()
        }
    }
}