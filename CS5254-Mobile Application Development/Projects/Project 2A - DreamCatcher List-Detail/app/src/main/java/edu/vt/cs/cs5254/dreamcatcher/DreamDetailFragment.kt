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
import java.util.*

private const val TAG = "DreamDetailFragment"
private const val ARG_DREAM_ID = "dream_id"

class DreamDetailFragment : Fragment() {
    private var _binding: FragmentDreamDetailBinding? = null
    private val ui get() = _binding!!
    private val vm: DreamDetailViewModel by lazy {
        ViewModelProvider(this).get(DreamDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dreamId: UUID = arguments?.getSerializable(ARG_DREAM_ID) as UUID
        vm.loadDream(dreamId)
        Log.d(TAG, "Dream detail fragment for dream with ID ${vm.dream.id}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDreamDetailBinding.inflate(inflater, container, false)
        val view = ui.root

        ui.dreamTitle.setText(vm.dream.title)
        ui.dreamDate.apply {
            text = vm.dream.date.toString()
            isEnabled = false
        }
        ui.dreamFulfilled.isChecked = vm.dream.isFulfilled
        ui.dreamDeferred.isChecked = vm.dream.isDeferred
        return view
    }

    override fun onStart() {
        super.onStart()
        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(
                sequence: CharSequence?, start: Int, count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int, before: Int, count: Int
            ) {
                vm.dream.title = sequence.toString()
            }

            override fun afterTextChanged(sequence: Editable?) {}
        }
        ui.dreamTitle.addTextChangedListener(titleWatcher)
        ui.dreamFulfilled.apply {
            setOnCheckedChangeListener { _, isChecked ->
                vm.dream.isFulfilled = isChecked
            }
        }
        ui.dreamDeferred.apply {
            setOnCheckedChangeListener { _, isChecked ->
                vm.dream.isDeferred = isChecked
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(dreamId: UUID): DreamDetailFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DREAM_ID, dreamId)
            }
            return DreamDetailFragment().apply {
                arguments = args
            }
        }
    }
}