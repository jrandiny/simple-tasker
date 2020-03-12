package id.randiny.simplyautomatic.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.randiny.simplyautomatic.module.ModuleType

class ModuleListAdapter(
    private val list: List<ModuleType>,
    private val listener: (ModuleType) -> Unit
) : RecyclerView.Adapter<ModuleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ModuleViewHolder(inflater, parent, listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        holder.bind(list[position])
    }


}