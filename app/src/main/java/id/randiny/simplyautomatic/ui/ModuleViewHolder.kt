package id.randiny.simplyautomatic.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.randiny.simplyautomatic.R
import id.randiny.simplyautomatic.module.ModuleType

class ModuleViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    private val listener: (ModuleType) -> Unit
) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.picker_module_item, parent, false)) {

    private val name: TextView = itemView.findViewById(R.id.item_text)
    private val description: TextView = itemView.findViewById(R.id.item_description)


    fun bind(moduleType: ModuleType) {
        name.text = ModuleType.getName(moduleType)
        description.text = ModuleType.getDescription(moduleType)

        itemView.setOnClickListener {
            listener(moduleType)
        }
    }

}