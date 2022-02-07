package com.example.willywonka.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.willywonka.R
import com.example.willywonka.databinding.DescriptionDialogFragmentBinding

class DescriptionDialog : DialogFragment() {
    private lateinit var binding: DescriptionDialogFragmentBinding


    companion object {
        const val TAG = "DescriptionDialog"
        private const val KEY_DESCRIPTION = "descriptionKey"

        fun newInstance(description: String): DescriptionDialog {
            val args = Bundle()
            args.putString(KEY_DESCRIPTION, description)
            val fragment = DescriptionDialog()
            fragment.arguments = args
            return fragment

        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DescriptionDialogFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvBody.text = arguments?.getString(KEY_DESCRIPTION)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }
}