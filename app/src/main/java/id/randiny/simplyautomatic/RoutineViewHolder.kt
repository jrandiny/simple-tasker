package id.randiny.simplyautomatic

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RoutineViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_routine,parent,false)) {
    private var mName: TextView? = null
    private var mKondisi: Button? = null
    private var mAksi: Button? = null

    init {
        mName = itemView.findViewById(R.id.name)
        mKondisi = itemView.findViewById(R.id.kondisi)
        mAksi = itemView.findViewById(R.id.aksi)
    }

    fun bind(routine: Routine) {
        mName?.text = routine.name
        mKondisi?.text = routine.kondisi
        mAksi?.text = routine.aksi
    }
}
