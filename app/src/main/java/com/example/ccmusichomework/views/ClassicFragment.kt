package com.example.ccmusichomework.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ccmusichomework.SongViewModel
import com.example.ccmusichomework.databinding.FragmentClassicBinding
import com.example.ccmusichomework.rest.UIState

class ClassicFragment : Fragment() {


    private val binding by lazy{
        FragmentClassicBinding.inflate(layoutInflater)
    }
    private val songlist by lazy{
        ViewModelProvider(requireActivity())[SongViewModel::class.java]
    }
    private val classicAdapter by lazy{
        Adapter(){
            ViewModelProvider(requireActivity())[SongViewModel::class.java]
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {  binding.ClassicRe.apply {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        adapter = classicAdapter
    }

        /**
         * switches between the three states of the UI state we made
         * Checks if the API is loading, when displayed the resulting data
         * and returns the faulire error we set earlier if it fails
         */
        // Inflate the layout for this fragment
        songlist.songs.observe(viewLifecycleOwner) { state ->
            when(state){
                is UIState.LOADING -> {
                    Toast.makeText(requireContext(),"Loading", Toast.LENGTH_LONG).show()
                }
                is UIState.SUCCESS -> {
                    Toast.makeText(requireContext(),"Success", Toast.LENGTH_LONG).show()
                    Log.d("Data", state.songs.size.toString())
                    classicAdapter.updateSongs(state.songs)
                }
                is UIState.FAILURE -> {
                    Toast.makeText(requireContext(),"Failure", Toast.LENGTH_LONG).show()
                }
            }

        }
        songlist.getClassicSongs()
        return binding.root
    }

}