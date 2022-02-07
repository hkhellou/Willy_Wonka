package com.example.willywonka.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.willywonka.R
import com.example.willywonka.adapters.ItemClickListener
import com.example.willywonka.adapters.OompaLoompaWorkerAdapter
import com.example.willywonka.databinding.OompaLoompaWorkersFragmentBinding
import com.example.willywonka.gson.Worker
import com.example.willywonka.viewModel.OompaLoompaWorkersViewModel

class OompaLoompaWorkersFragment : Fragment(), ItemClickListener {

    companion object {
        fun newInstance() = OompaLoompaWorkersFragment()
        val TAG = OompaLoompaWorkersFragment::class
    }

    private lateinit var viewModel: OompaLoompaWorkersViewModel
    private lateinit var binding: OompaLoompaWorkersFragmentBinding
    private lateinit var adapter: OompaLoompaWorkerAdapter
    private lateinit var list: List<Worker>
    private var errorMesage : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OompaLoompaWorkersFragmentBinding.inflate(
            layoutInflater
        )
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initValues()
        initObservers()
    }

    private fun initValues() {
        viewModel = ViewModelProvider(this).get(OompaLoompaWorkersViewModel::class.java)
        viewModel.getWorkers()
    }

    private fun initObservers() {
        viewModel.itemMutableList.observe(viewLifecycleOwner, Observer {
            list = it
            configAdapter()
        })

        viewModel.workersServiceError.observe(viewLifecycleOwner, Observer {
            errorMesage = it
            if (errorMesage) Toast.makeText(context,"error", Toast.LENGTH_SHORT)
        })

        viewModel.pbVisibility.observe(viewLifecycleOwner, Observer {
            if(it){
                binding.progressBar.visibility = View.VISIBLE
                binding.rvOompaLoompaWorkers.visibility = View.GONE
            }else{
                binding.progressBar.visibility = View.GONE
                binding.rvOompaLoompaWorkers.visibility = View.VISIBLE
            }
        })
    }

    private fun configAdapter() {
        adapter = OompaLoompaWorkerAdapter(list,this)
        binding.rvOompaLoompaWorkers.layoutManager = LinearLayoutManager(context)
        binding.rvOompaLoompaWorkers.adapter = adapter
    }

    override fun itemClickListener(worker: Worker) {
        //EN ESTA INSTANCIA EL VALOR DE LA VARIABLE DESCRIPTION LLEGA A NULO POR ESO SE LE DA UN VALOR
        worker.description = ""
        val action = OompaLoompaWorkersFragmentDirections.actionHomeFragmentToWorkerInfoFragment(worker)
        view?.findNavController()?.navigate(action)
    }


}