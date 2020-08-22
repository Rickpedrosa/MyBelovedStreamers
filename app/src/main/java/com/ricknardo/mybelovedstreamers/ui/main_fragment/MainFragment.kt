package com.ricknardo.mybelovedstreamers.ui.main_fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ricknardo.mybelovedstreamers.R
import com.ricknardo.mybelovedstreamers.base.downgrade.RecyclerAdapter
import com.ricknardo.mybelovedstreamers.data.remote.pojos.streams.DatumStreams
import com.ricknardo.mybelovedstreamers.ui.main_activity.MainActivityViewModel
import com.ricknardo.mybelovedstreamers.ui.main_activity.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var recyclerViewAdapter: RecyclerAdapter<DatumStreams, ViewHolderImpl>

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

        setupRecyclerView()

        viewModel.getMyStreamersOnline().observe(
            viewLifecycleOwner,
            Observer {
                recyclerViewAdapter.setData(it.sortedBy { u ->
                    u.viewer_count
                })
            })
    }

    private fun setupSwipeRefreshLayout() {
        refreshLayout.setOnRefreshListener {
            viewModel.retrieveOnlineStreamers()
            refreshLayout.isRefreshing = false
        }
    }

    private fun setupRecyclerView() {
        setupRecyclerViewAdapter()
        listStreamers.run {
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
            adapter = recyclerViewAdapter
        }
        setupSwipeRefreshLayout()
    }

    private fun setupRecyclerViewAdapter() {
        recyclerViewAdapter = object : RecyclerAdapter<DatumStreams, ViewHolderImpl>(
            listOf(),
            R.layout.stream_item,
            ViewHolderImpl::class.java
        ) {
            override fun onBindViewHolder(holder: ViewHolderImpl, position: Int) {
                holder.streamerName.text = getItem(position).user_name
                holder.streamerViewers.text = getItem(position).viewer_count.toString()
                holder.streamerTitle.text = getItem(position).title
            }
        }
    }


    class ViewHolderImpl(view: View) : RecyclerView.ViewHolder(view) {
        val streamerName = view.findViewById<TextView>(R.id.lbl_streamer_name)!!
        val streamerViewers = view.findViewById<TextView>(R.id.lbl_streamer_viewers)!!
        val streamerTitle = view.findViewById<TextView>(R.id.lbl_streamer_title)!!
    }
}

