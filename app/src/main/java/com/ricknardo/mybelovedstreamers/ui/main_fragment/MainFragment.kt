package com.ricknardo.mybelovedstreamers.ui.main_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ricknardo.mybelovedstreamers.R
import com.ricknardo.mybelovedstreamers.data.remote.pojos.streams.Streams
import com.ricknardo.mybelovedstreamers.ui.main_activity.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
        if (savedInstanceState == null) {
            viewModel.letsgo()
        }

        viewModel.getMyStreamersOnline().observe(
            viewLifecycleOwner,
            Observer { it ->
                textView.text = it.size.toString()
            })
    }
}