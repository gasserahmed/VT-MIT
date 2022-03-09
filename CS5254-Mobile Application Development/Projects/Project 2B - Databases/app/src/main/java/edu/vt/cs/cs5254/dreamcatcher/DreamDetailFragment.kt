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
import androidx.core.view.children
import androidx.lifecycle.Observer
import edu.vt.cs.cs5254.dreamcatcher.databinding.FragmentDreamDetailBinding
import java.util.*

private const val TAG = "DreamDetailFragment"
private const val ARG_DREAM_ID = "dream_id"
private val CONCEIVED_BUTTON_COLOR = "#90107F"
private val FULFILLED_BUTTON_COLOR = "#2E3B1F"
private val DEFERRED_BUTTON_COLOR = "#2C2544"
private val REFLECTION_BUTTON_COLOR = "#FEC9C1"

class DreamDetailFragment : Fragment() {
    private lateinit var dreamWithEntries: DreamWithEntries

    private var _binding: FragmentDreamDetailBinding? = null
    private val ui get() = _binding!!
    private val vm: DreamDetailViewModel by lazy {
        ViewModelProvider(this).get(DreamDetailViewModel::class.java)
    }

    lateinit var dreamEntryButtonList: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dreamWithEntries = DreamWithEntries(Dream(), emptyList())
        val dreamId: UUID = arguments?.getSerializable(ARG_DREAM_ID) as UUID
        Log.d(TAG, "Dream detail fragment for dream with ID $dreamId")
        vm.loadDream(dreamId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDreamDetailBinding.inflate(inflater, container, false)
        val view = ui.root
        updateUI()
        return view
    }

    private fun updateUI() {
        ui.dreamTitleText.setText(dreamWithEntries.dream.title)
        ui.dreamFulfilledCheckbox.isChecked = dreamWithEntries.dream.isFulfilled
        ui.dreamDeferredCheckbox.isChecked = dreamWithEntries.dream.isDeferred
        dreamEntryButtonList = ui.dreamEntryButtons.children.toList().filterIsInstance<Button>()
        refreshView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.dreamLiveData.observe(
            viewLifecycleOwner,
            Observer { dreamWithEntries ->
                dreamWithEntries?.let {
                    this.dreamWithEntries = dreamWithEntries
                    updateUI()
                }
            })
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
                dreamWithEntries.dream.title = sequence.toString()
            }

            override fun afterTextChanged(sequence: Editable?) {}
        }

        ui.dreamTitleText.addTextChangedListener(titleWatcher)
        ui.dreamFulfilledCheckbox.apply {
            setOnCheckedChangeListener { _, isChecked ->
                dreamWithEntries.dream.isFulfilled = isChecked
                refreshView()
            }
        }
        ui.dreamDeferredCheckbox.apply {
            setOnCheckedChangeListener { _, isChecked ->
                dreamWithEntries.dream.isDeferred = isChecked
                refreshView()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        vm.saveDream(dreamWithEntries)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun refreshView() {
        // Refresh checkboxes
        if (dreamWithEntries.dreamEntries.isNotEmpty() &&
            (dreamWithEntries.dreamEntries.last().kind == DreamEntryKind.FULFILLED ||
                    dreamWithEntries.dreamEntries.last().kind == DreamEntryKind.DEFERRED)
        ) {
            dreamWithEntries.dreamEntries = dreamWithEntries.dreamEntries.dropLast(1);
        }

        when {
            dreamWithEntries.dream.isFulfilled -> {
                ui.dreamFulfilledCheckbox.isChecked = true
                ui.dreamFulfilledCheckbox.isEnabled = true
                ui.dreamDeferredCheckbox.isChecked = false
                ui.dreamDeferredCheckbox.isEnabled = false
                dreamWithEntries.dreamEntries += DreamEntry(
                    kind = DreamEntryKind.FULFILLED,
                    dreamId = dreamWithEntries.dream.id
                )
            }
            dreamWithEntries.dream.isDeferred -> {
                ui.dreamDeferredCheckbox.isChecked = true
                ui.dreamDeferredCheckbox.isEnabled = true
                ui.dreamFulfilledCheckbox.isChecked = false
                ui.dreamFulfilledCheckbox.isEnabled = false
                dreamWithEntries.dreamEntries += DreamEntry(
                    kind = DreamEntryKind.DEFERRED,
                    dreamId = dreamWithEntries.dream.id
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
        val dreamEntryButtonPairs = dreamWithEntries.dreamEntries.zip(dreamEntryButtonList)
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
                val buttonText = DateFormat.format("MMM dd, yyyy", dreamEntry.date).toString() +
                        ": " + dreamEntry.text
                button.text = buttonText
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