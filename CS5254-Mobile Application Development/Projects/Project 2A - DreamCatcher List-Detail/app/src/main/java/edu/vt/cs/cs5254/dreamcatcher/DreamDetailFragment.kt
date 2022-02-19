package edu.vt.cs.cs5254.dreamcatcher

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.vt.cs.cs5254.dreamcatcher.databinding.FragmentDreamDetailBinding

private const val TAG = "DreamDetailFragment"

class DreamDetailFragment : Fragment() {
    private var _binding: FragmentDreamDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DreamDetailViewModel by lazy {
        ViewModelProvider(this).get(DreamDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Dream detail fragment for dream with ID ${viewModel.dream.id}")
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDreamDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.dreamDate.apply {
            text = viewModel.dream.date.toString()
            isEnabled = false
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(
                sequence: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(sequence: CharSequence?,
                                       start: Int, before: Int, count: Int) {
                viewModel.dream.title = sequence.toString()
            }
            override fun afterTextChanged(sequence: Editable?) { }
        }
        binding.dreamTitle.addTextChangedListener(titleWatcher)
        binding.dreamFulfilled.apply {
            setOnCheckedChangeListener { _, isChecked ->
                viewModel.dream.isFulfilled = isChecked
            }
        }
        binding.dreamDeferred.apply {
            setOnCheckedChangeListener { _, isChecked ->
                viewModel.dream.isDeferred = isChecked
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        fun newInstance(): DreamDetailFragment {
            return DreamDetailFragment()
        }
    }
}