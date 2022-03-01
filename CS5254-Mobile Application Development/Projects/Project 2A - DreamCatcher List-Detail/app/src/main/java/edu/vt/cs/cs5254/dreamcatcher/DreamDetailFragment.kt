package edu.vt.cs.cs5254.dreamcatcher

import android.content.res.ColorStateList
import android.graphics.Color
import android.opengl.Visibility
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.children
import edu.vt.cs.cs5254.dreamcatcher.databinding.FragmentDreamDetailBinding
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "DreamDetailFragment"
private const val ARG_DREAM_ID = "dream_id"
private val CONCEIVED_BUTTON_COLOR = "#90107F"
private val FULFILLED_BUTTON_COLOR = "#2E3B1F"
private val DEFERRED_BUTTON_COLOR = "#2C2544"
private val REFLECTION_BUTTON_COLOR = "#FEC9C1"

class DreamDetailFragment : Fragment() {
    private var _binding: FragmentDreamDetailBinding? = null
    private val ui get() = _binding!!
    private val vm: DreamDetailViewModel by lazy {
        ViewModelProvider(this).get(DreamDetailViewModel::class.java)
    }

    lateinit var dreamEntryButtonList: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dreamId: UUID = arguments?.getSerializable(ARG_DREAM_ID) as UUID
        vm.loadDream(dreamId)
        Log.d(TAG, "Dream detail fragment for dream with ID ${vm.dreamWithEntries.dream.id}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDreamDetailBinding.inflate(inflater, container, false)
        val view = ui.root

        ui.dreamTitleText.setText(vm.dreamWithEntries.dream.title)
        ui.dreamFulfilledCheckbox.isChecked = vm.dreamWithEntries.dream.isFulfilled
        ui.dreamDeferredCheckbox.isChecked = vm.dreamWithEntries.dream.isDeferred
        dreamEntryButtonList = ui.dreamEntryButtons.children.toList().filterIsInstance<Button>()
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
                vm.dreamWithEntries.dream.title = sequence.toString()
            }

            override fun afterTextChanged(sequence: Editable?) {}
        }
        ui.dreamTitleText.addTextChangedListener(titleWatcher)
        ui.dreamFulfilledCheckbox.apply {
            setOnCheckedChangeListener { _, isChecked ->
                vm.dreamWithEntries.dream.isFulfilled = isChecked
                refreshView()
            }
        }
        ui.dreamDeferredCheckbox.apply {
            setOnCheckedChangeListener { _, isChecked ->
                vm.dreamWithEntries.dream.isDeferred = isChecked
                refreshView()
            }
        }

        refreshView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun refreshView() {
        // Refresh checkboxes
        if (vm.dreamWithEntries.dreamEntries.last().kind == DreamEntryKind.FULFILLED ||
            vm.dreamWithEntries.dreamEntries.last().kind == DreamEntryKind.DEFERRED
        ) {
            vm.dreamWithEntries.dreamEntries = vm.dreamWithEntries.dreamEntries.dropLast(1);
        }

        when {
            vm.dreamWithEntries.dream.isFulfilled -> {
                ui.dreamFulfilledCheckbox.isChecked = true
                ui.dreamFulfilledCheckbox.isEnabled = true
                ui.dreamDeferredCheckbox.isChecked = false
                ui.dreamDeferredCheckbox.isEnabled = false
                vm.dreamWithEntries.dreamEntries += DreamEntry(
                    kind = DreamEntryKind.FULFILLED,
                    dreamId = vm.dreamWithEntries.dream.id
                )
            }
            vm.dreamWithEntries.dream.isDeferred -> {
                ui.dreamDeferredCheckbox.isChecked = true
                ui.dreamDeferredCheckbox.isEnabled = true
                ui.dreamFulfilledCheckbox.isChecked = false
                ui.dreamFulfilledCheckbox.isEnabled = false
                vm.dreamWithEntries.dreamEntries += DreamEntry(
                    kind = DreamEntryKind.DEFERRED,
                    dreamId = vm.dreamWithEntries.dream.id
                )
            }
            else -> {
                ui.dreamDeferredCheckbox.isChecked = false
                ui.dreamDeferredCheckbox.isEnabled = true
                ui.dreamFulfilledCheckbox.isChecked = false
                ui.dreamFulfilledCheckbox.isEnabled = true
            }
        }

        // Hide all dream entries
        dreamEntryButtonList.forEach { button -> button.visibility = View.GONE }

        // Refresh dream entries
        val dreamEntryButtonPairs = vm.dreamWithEntries.dreamEntries.zip(dreamEntryButtonList)
        dreamEntryButtonPairs.forEach { (dreamEntry, button) ->
            refreshDreamEntry(
                dreamEntry,
                button
            )
        }
    }

    private fun refreshDreamEntry(dreamEntry: DreamEntry, button: Button) {
        button.visibility = View.VISIBLE
        when (dreamEntry.kind) {
            DreamEntryKind.CONCEIVED -> {
                button.text = DreamEntryKind.CONCEIVED.name
                button.backgroundTintList =
                    ColorStateList.valueOf(Color.parseColor(CONCEIVED_BUTTON_COLOR))
            }
            DreamEntryKind.FULFILLED -> {
                button.text = DreamEntryKind.FULFILLED.name
                button.backgroundTintList =
                    ColorStateList.valueOf(Color.parseColor(FULFILLED_BUTTON_COLOR))
            }
            DreamEntryKind.DEFERRED -> {
                button.text = DreamEntryKind.DEFERRED.name
                button.backgroundTintList =
                    ColorStateList.valueOf(Color.parseColor(DEFERRED_BUTTON_COLOR))
                button.setTextColor(Color.WHITE)
            }
            else -> {
                val date = DateFormat.format("MMM dd, yyyy", dreamEntry.date).toString() +
                        ": " + dreamEntry.text
                button.text = date
                button.backgroundTintList =
                    ColorStateList.valueOf(Color.parseColor(REFLECTION_BUTTON_COLOR))
                button.setTextColor(Color.BLACK)
            }
        }
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