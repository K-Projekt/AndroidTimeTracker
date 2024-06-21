package pl.edu.pja.teamk.timetracking.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView
import pl.edu.pja.teamk.timetracking.TimeEntry

import pl.edu.pja.teamk.timetracking.databinding.FragmentTimeEntryBinding

class MyTimeEntryRecyclerViewAdapter(
    private var values: MutableList<TimeEntry>
) : RecyclerView.Adapter<MyTimeEntryRecyclerViewAdapter.ViewHolder>() {

    var onClickListener: View.OnClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentTimeEntryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.duration.toString()
        holder.contentView.text = item.description
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentTimeEntryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        init {
            binding.root.setOnClickListener {
                it.tag = values[bindingAdapterPosition]
                onClickListener?.onClick(it)
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    fun setData(list: MutableList<TimeEntry>) {
        with (values) {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }
}