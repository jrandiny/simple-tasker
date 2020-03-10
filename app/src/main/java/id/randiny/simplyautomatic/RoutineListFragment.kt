package id.randiny.simplyautomatic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_routine_list.*

data class Routine(val name:String, val condition:String, val action:String)

class RoutineListFragment : Fragment() {

    private val dataExample = listOf(
        Routine("Bangun pagi","05.00","Alarm"),
        Routine("Tidur","22.00","Alarm"),
        Routine("Belajar","18.00","Pemberitahuan")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_routine_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lm = LinearLayoutManager(activity)
        list_item_view.apply{
            layoutManager = lm
            adapter = ListAdapter(dataExample)
        }
        list_item_view.addItemDecoration(DividerItemDecoration(activity,lm.orientation))
    }

    companion object{
        fun newInstance(): RoutineListFragment = RoutineListFragment()
    }
}