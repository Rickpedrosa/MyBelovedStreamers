package com.ricknardo.mybelovedstreamers.ui.main_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ricknardo.mybelovedstreamers.R
import com.ricknardo.mybelovedstreamers.ui.main_activity.MainActivityViewModel
import com.ricknardo.mybelovedstreamers.ui.main_activity.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            requireActivity(),
            MainActivityViewModelFactory(requireActivity().application)
        ).get(MainActivityViewModel::class.java)

        if (savedInstanceState == null) {
            viewModel.retrieveOnlineStreamers()
        }

        viewModel.getMyStreamersOnline().observe(
            viewLifecycleOwner,
            Observer {
                textView.text = it.size.toString()
            })
    }
}