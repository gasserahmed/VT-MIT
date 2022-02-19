package edu.vt.cs.cs5254.dreamcatcher

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.vt.cs.cs5254.dreamcatcher.databinding.FragmentDreamListBinding
import edu.vt.cs.cs5254.dreamcatcher.databinding.ListItemDreamBinding

class DreamListFragment : Fragment() {
    private var _binding: FragmentDreamListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DreamListViewModel by lazy {
        ViewModelProvider(this).get(DreamListViewModel::class.java)
    }

    private var adapter: DreamAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDreamListBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.dreamRecyclerView.layoutManager = LinearLayoutManager(context)
        updateUI()
        return view
    }

    private fun updateUI() {
        val dreams = viewModel.dreams
        adapter = DreamAdapter(dreams)
        binding.dreamRecyclerView.adapter = adapter
    }

    // DreamHolder && DreamAdapter
    private inner class DreamHolder(val itemBinding: ListItemDreamBinding) :
        RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {
        private lateinit var dream: Dream

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(dream: Dream) {
            this.dream = dream
            itemBinding.dreamItemTitle.text = this.dream.title
            itemBinding.dreamItemDate.text = this.dream.date.toString()
            itemBinding.dreamItemImage.visibility = if (dream.isFulfilled || dream.isDeferred) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
        }

        override fun onClick(v: View) {
            Toast.makeText(
                context,
                "${dream.title} pressed!", Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    private inner class DreamAdapter(var dreams: List<Dream>) :
        RecyclerView.Adapter<DreamHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DreamHolder {
            val itemBinding = ListItemDreamBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return DreamHolder(itemBinding)
        }

        override fun getItemCount() = dreams.size
        override fun onBindViewHolder(holder: DreamHolder, position: Int) {
            val dream = dreams[position]
            holder.bind(dream)
        }
    }

    companion object {
        fun newInstance(): DreamListFragment {
            return DreamListFragment()
        }
    }
}