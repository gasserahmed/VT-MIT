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
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.vt.cs.cs5254.dreamcatcher.DreamDetailFragment.Companion.newInstance
import edu.vt.cs.cs5254.dreamcatcher.databinding.FragmentDreamDetailBinding
import edu.vt.cs.cs5254.dreamcatcher.databinding.ListItemDreamBinding
import edu.vt.cs.cs5254.dreamcatcher.databinding.ListItemDreamEntryBinding
import org.w3c.dom.Text
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
    private val vm: DreamDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dreamWithEntries = DreamWithEntries(Dream(), emptyList())
        val dreamId: UUID = arguments?.getSerializable(ARG_DREAM_ID) as UUID
        Log.d(TAG, "Dream detail fragment for dream with ID $dreamId")
        vm.loadDream(dreamId)
    }

    private var adapter: DreamEntryAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDreamDetailBinding.inflate(inflater, container, false)
        val view = ui.root
        ui.dreamEntryRecyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    private fun updateUI() {
        ui.dreamTitleText.setText(dreamWithEntries.dream.title)
        ui.dreamFulfilledCheckbox.isChecked = dreamWithEntries.dream.isFulfilled
        ui.dreamDeferredCheckbox.isChecked = dreamWithEntries.dream.isDeferred
        adapter = DreamEntryAdapter(dreamWithEntries.dreamEntries)
        ui.dreamEntryRecyclerView.adapter = adapter
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
        ui.dreamTitleText.doOnTextChanged { text, start, before, count ->
            dreamWithEntries.dream.title = text.toString()
        }

        ui.dreamFulfilledCheckbox.apply {
            setOnCheckedChangeListener { _, isChecked ->
                dreamWithEntries.dream.isFulfilled = isChecked
                if (isChecked) {
                    ui.dreamDeferredCheckbox.isEnabled = false
                    ui.addReflectionButton.isEnabled = false
                    if (!dreamWithEntries.dreamEntries.any { dreamEntry -> dreamEntry.kind == DreamEntryKind.FULFILLED }) {
                        dreamWithEntries.dreamEntries += DreamEntry(
                            kind = DreamEntryKind.FULFILLED,
                            dreamId = dreamWithEntries.dream.id
                        )
                    }
                } else {
                    ui.dreamDeferredCheckbox.isEnabled = true
                    ui.addReflectionButton.isEnabled = true
                    dreamWithEntries.dreamEntries =
                        dreamWithEntries.dreamEntries.filter { dreamEntry ->
                            dreamEntry.kind != DreamEntryKind.FULFILLED
                        }
                }

                updateUI()
            }
        }

        ui.dreamDeferredCheckbox.apply {
            setOnCheckedChangeListener { _, isChecked ->
                dreamWithEntries.dream.isDeferred = isChecked
                if (isChecked) {
                    ui.dreamFulfilledCheckbox.isEnabled = false
                    if (!dreamWithEntries.dreamEntries.any { dreamEntry -> dreamEntry.kind == DreamEntryKind.DEFERRED }) {
                        dreamWithEntries.dreamEntries += DreamEntry(
                            kind = DreamEntryKind.DEFERRED,
                            dreamId = dreamWithEntries.dream.id
                        )
                    }
                } else {
                    ui.dreamFulfilledCheckbox.isEnabled = true
                    dreamWithEntries.dreamEntries =
                        dreamWithEntries.dreamEntries.filter { dreamEntry ->
                            dreamEntry.kind != DreamEntryKind.DEFERRED
                        }
                }
                updateUI()
            }
        }

        ui.addReflectionButton.setOnClickListener {
            AddReflectionDialog.newInstance(REQUEST_KEY_ADD_REFLECTION)
                .show(parentFragmentManager, REQUEST_KEY_ADD_REFLECTION)
        }

        parentFragmentManager.setFragmentResultListener(
            REQUEST_KEY_ADD_REFLECTION,
            viewLifecycleOwner
        )
        { _, bundle ->
            val reflectionText = bundle.getSerializable(BUNDLE_KEY_REFLECTION_TEXT) as String
            val newReflection = DreamEntry(
                kind = DreamEntryKind.REFLECTION,
                dreamId = dreamWithEntries.dream.id,
                text = reflectionText
            )
            dreamWithEntries.dreamEntries += newReflection
            updateUI()
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
                button.setTextColor(Color.WHITE)
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

    // DreamEntryHolder && DreamEntryAdapter
    inner class DreamEntryHolder(val itemBinding: ListItemDreamEntryBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(dreamEntry: DreamEntry) {
            refreshDreamEntry(dreamEntry, itemBinding.dreamEntryButton)
        }
    }

    private inner class DreamEntryAdapter(var dreamEntries: List<DreamEntry>) :
        RecyclerView.Adapter<DreamEntryHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DreamEntryHolder {
            val itemBinding = ListItemDreamEntryBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return DreamEntryHolder(itemBinding)
        }

        override fun getItemCount() = dreamEntries.size
        override fun onBindViewHolder(holder: DreamEntryHolder, position: Int) {
            val dreamEntry = dreamEntries[position]
            holder.bind(dreamEntry)
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