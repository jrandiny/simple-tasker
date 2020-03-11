package id.randiny.simplyautomatic.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import id.randiny.simplyautomatic.data.Routine

val diffUtil = object : DiffUtil.ItemCallback<Routine>() {
    override fun areItemsTheSame(oldItem: Routine, newItem: Routine): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Routine, newItem: Routine): Boolean {
        return oldItem == newItem
    }
}

class RoutineListAdapter
    : ListAdapter<Routine, RoutineViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RoutineViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RoutineViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}