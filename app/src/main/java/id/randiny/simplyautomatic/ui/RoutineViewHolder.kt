package id.randiny.simplyautomatic.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.randiny.simplyautomatic.R

class RoutineViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_routine,parent,false)) {
    private var mName: TextView? = null
    private var mCondition: TextView? = null
    private var mAction: TextView? = null

    init {
        mName = itemView.findViewById(R.id.name)
        mCondition = itemView.findViewById(R.id.condition)
        mAction = itemView.findViewById(R.id.action)
    }

    fun bind(routine: Routine) {
        mName?.text = routine.name
        mCondition?.text = routine.condition
        mAction?.text = routine.action
    }
}
