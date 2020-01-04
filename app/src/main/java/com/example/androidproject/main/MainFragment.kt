package com.example.androidproject.main

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.androidproject.R
import com.example.androidproject.database.MessageDatabase
import com.example.androidproject.databinding.MainFragmentBinding
import com.example.androidproject.network.MessageApi
import com.example.androidproject.repository.MessageRepository

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<MainFragmentBinding>(inflater,
            R.layout.main_fragment, container, false)

        val application = requireNotNull(this.activity).application

        // val arguments = MainFragmentArgs.fromBundle(arguments!!)

        val dao = MessageDatabase.getInstance(application).messageDao

        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val messageRepository = MessageRepository(dao, MessageApi.retrofitService, cm)

        val viewModelFactory = MainViewModelFactory(messageRepository)

        val mainViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(MainViewModel::class.java)

        binding.mainViewModel = mainViewModel

        val adapter = MessageAdapter(MessageClickListener
        {
                messageId ->
            view!!.findNavController().navigate(MainFragmentDirections.actionMainFragmentToFavoritesFragment(messageId))
            // Toast.makeText(context, "${messageId}", Toast.LENGTH_SHORT).show()
        })

        binding.messageList.adapter = adapter

        mainViewModel.messages.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
