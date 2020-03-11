package id.randiny.simplyautomatic.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.randiny.simplyautomatic.R
import id.randiny.simplyautomatic.data.Routine

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
        Log.d("My/vh", routine.toString())
        mName?.text = routine.actionType.toString()
        mCondition?.text = routine.conditionType.toString()
        mAction?.text = routine.actionType.toString()
    }
}
