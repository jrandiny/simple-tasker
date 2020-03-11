package id.randiny.simplyautomatic.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.randiny.simplyautomatic.R
import id.randiny.simplyautomatic.data.RoutineViewModel
import id.randiny.simplyautomatic.data.RoutineViewModelFactory
import kotlinx.android.synthetic.main.fragment_routine_list.view.*

class RoutineListFragment : Fragment() {

    private lateinit var routineViewModel: RoutineViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        routineViewModel = ViewModelProvider(this, RoutineViewModelFactory(requireActivity())).get(
            RoutineViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_routine_list, container, false)
        val activeList = arguments?.getBoolean(ARG_LIST_TYPE)

        val lm = LinearLayoutManager(activity)
        val adapter = RoutineListAdapter()
        val listRecycler = root.list_item_view
        listRecycler.layoutManager = lm
        listRecycler.adapter = adapter
        listRecycler.addItemDecoration(DividerItemDecoration(activity, lm.orientation))

        if (activeList != null && activeList) {
            routineViewModel.activeList.observe(this, Observer { routines ->
                Log.d("My/new data", routines.toString())
                adapter.submitList(routines)
            })
        } else {
            routineViewModel.inactiveList.observe(this, Observer { routines ->
                adapter.submitList(routines)
            })
        }
        return root
    }

    companion object {
        private const val ARG_LIST_TYPE = "use_active_list"

        fun newInstance(listType: Boolean) = RoutineListFragment().apply {
            arguments = Bundle().apply {
                putBoolean(ARG_LIST_TYPE, listType)
            }
        }
    }
}