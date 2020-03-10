package id.randiny.simplyautomatic.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RoutineListAdapter(private val list: List<Routine>)
    : RecyclerView.Adapter<RoutineViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RoutineViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RoutineViewHolder, position: Int) {
        val routine: Routine = list[position]
        holder.bind(routine)
    }

    override fun getItemCount(): Int = list.size
}