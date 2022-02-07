package com.example.willywonka.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.willywonka.R
import com.example.willywonka.databinding.WorkerInfoFragmentBinding
import com.example.willywonka.gson.Worker
import com.example.willywonka.viewModel.WorkerInfoViewModel
import com.squareup.picasso.Picasso

class WorkerInfoFragment : Fragment() {

    private lateinit var binding: WorkerInfoFragmentBinding
    private lateinit var viewModel: WorkerInfoViewModel
    private lateinit var bundle: Bundle
    private lateinit var args: WorkerInfoFragmentArgs
    private lateinit var workerInfo: Worker

    companion object {
        fun newInstance() = WorkerInfoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WorkerInfoFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initValues()
        initListeners()
        initObservers()
    }

    private fun initValues() {
        viewModel = ViewModelProvider(this).get(WorkerInfoViewModel::class.java)
        bundle = arguments ?: return
        args = WorkerInfoFragmentArgs.fromBundle(bundle)
        viewModel.getWorker(args.workerArg.id)
    }

    private fun initListeners() {
        binding.cvEmail.setOnClickListener {
            sendEmail()
        }

        binding.btnDesc.setOnClickListener {
            DescriptionDialog.newInstance(workerInfo.description).show(parentFragmentManager,DescriptionDialog.TAG)
        }
    }

    private fun initObservers() {
        viewModel.worker.observe(viewLifecycleOwner, Observer {
            workerInfo = it
            setValues()
        })

        viewModel.workersServiceError.observe(viewLifecycleOwner, Observer {
            if (it) Toast.makeText(context, "error", Toast.LENGTH_SHORT)
        })

        viewModel.pbVisibility.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

    }

    private fun setValues() {
        var image = workerInfo.image
        Picasso.get().load(image).into(binding.imgWorker)
        binding.apply {
            tvWorkerName.text = workerInfo.firstName
            tvWorkerLastName.text = workerInfo.lastName
            tvProfession.text = workerInfo.profession
            tvCountry.text = workerInfo.country
            tvAge.text = concatStringYears()
            tvHeight.text = concatStringHeight()
            tvGender.text = setGender()
            tvMail.text = workerInfo.email
        }

    }


    private fun setGender(): String? {
        var gender = activity?.getString(R.string.GENDER)

        when (workerInfo.gender) {
            activity?.getString(R.string.F) -> gender = activity?.getString(R.string.FEMALE)
            activity?.getString(R.string.M) -> gender = activity?.getString(R.string.MALE)
        }
        return gender
    }

    private fun concatStringYears(): String {
        val age = workerInfo.age
        val years = activity?.getString(R.string.YEARS)
        val ageYears = buildString {
            append(age)
            append(" ")
            append(years)
        }
        return ageYears
    }

    private fun concatStringHeight(): String {
        val height = workerInfo.height
        val cm = activity?.getString(R.string.CM)
        val heightCm = buildString {
            append(height)
            append(" ")
            append(cm)
        }
        return heightCm
    }

    private fun sendEmail() {
        val name = binding.tvWorkerName.text.toString()
        val intent = Intent(
            Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", binding.tvMail.text.toString(), null
            )
        )
        intent.putExtra(Intent.EXTRA_EMAIL, binding.tvMail.text)
        intent.putExtra(Intent.EXTRA_SUBJECT, activity?.getString(R.string.app_name))
        intent.putExtra(Intent.EXTRA_TEXT, "Hello my name is $name")
        startActivity(intent)
    }
}