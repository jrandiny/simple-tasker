package id.randiny.simplyautomatic.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.randiny.simplyautomatic.R
import id.randiny.simplyautomatic.data.Routine

class RoutineViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    private val toggleCallback: (Int) -> Unit,
    private val deleteCallback: (Int) -> Unit
) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_routine, parent, false)) {
    private val mName: TextView = itemView.findViewById(R.id.name)
    private val mCondition: TextView = itemView.findViewById(R.id.condition)
    private val mAction: TextView = itemView.findViewById(R.id.action)


    fun bind(routine: Routine) {
        mName.text = routine.actionType.toString()
        mCondition.text = routine.conditionType.toString()
        mAction.text = routine.actionType.toString()

        itemView.findViewById<ImageButton>(R.id.routine_on_off).setOnClickListener {
            toggleCallback(routine.id)
        }

        itemView.findViewById<ImageButton>(R.id.routine_delete).setOnClickListener {
            deleteCallback(routine.id)
        }
    }
}
