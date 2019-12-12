package com.example.androidproject.Main

import android.content.Intent
import android.content.Intent.getIntent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.androidproject.R
import com.example.androidproject.databinding.MainFragmentBinding
import kotlinx.android.synthetic.main.main_fragment.view.autocomplete_channel

class MainFragment : Fragment() {

    private val OAUTH_REQUEST = "https://discordapp.com/api/oauth2/authorize?client_id=649577462245883904&redirect_uri=discordmulti%3A%2F%2Fcallback&response_type=code&scope=identify%20guilds"

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(OAUTH_REQUEST) )
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        val uri = getIntent("discordmulti://callback").data

        if(uri != null){
            Toast.makeText(this, "yay", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<MainFragmentBinding>(inflater,
            R.layout.main_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
