package co.gotoinc.ui.view.passengers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import co.gotoinc.ui.R
import co.gotoinc.ui.base.BaseFragment
import co.gotoinc.ui.databinding.FragmentPassengersBinding

class PassengersFragment : BaseFragment<PassengersViewModel>(), View.OnClickListener {
    override fun getLayoutId() = R.layout.fragment_passengers

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.applyChangesButton -> {
                viewModel.applyChanges()
                back()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.clickListener = this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentPassengersBinding>(
            inflater,
            getLayoutId(),
            container,
            false
        )
            .apply { viewModel = this@PassengersFragment.viewModel }
            .root
    }
}