package com.example.androidproject.details

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.androidproject.R
import com.example.androidproject.database.MessageDatabase
import com.example.androidproject.databinding.DetailsFragmentBinding
import com.example.androidproject.network.MessageApi
import com.example.androidproject.repository.MessageRepository

class DetailsFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<DetailsFragmentBinding>(inflater, R.layout.details_fragment, container, false)

        val args = DetailsFragmentArgs.fromBundle(arguments!!)

        val application = requireNotNull(this.activity).application

        val dao = MessageDatabase.getInstance(application).messageDao

        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val messageRepository = MessageRepository(dao, MessageApi.retrofitService, cm)

        val viewModelFactory = DetailsViewModelFactory(messageRepository, args.messageId)

        val detailsViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(DetailsViewModel::class.java)

        binding.detailsViewModel = detailsViewModel

        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
