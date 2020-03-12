package id.randiny.simplyautomatic.ui

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.randiny.simplyautomatic.R
import id.randiny.simplyautomatic.viewmodel.RoutineListViewModel
import id.randiny.simplyautomatic.viewmodel.RoutineListViewModelFactory

class RoutineListFragment : Fragment() {

    private lateinit var routineListViewModel: RoutineListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        routineListViewModel = ViewModelProvider(
            this,
            RoutineListViewModelFactory(
                requireActivity()
            )
        ).get(
            RoutineListViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.main_fragment_routine_list, container, false)
        val activeList = arguments?.getBoolean(ARG_LIST_TYPE)

        val listRecycler = root.findViewById<RecyclerView>(R.id.list_item_view)

        val lm = LinearLayoutManager(activity)
        val adapter = RoutineListAdapter()
        adapter.toggleCallback = routineListViewModel::toggleActivation
        adapter.deleteCallback = { id: Int ->
            AlertDialog.Builder(requireContext())
                .setMessage(getString(R.string.dialog_confirm_delete))
                .setPositiveButton(getString(R.string.dialog_yes)) { _: DialogInterface, _: Int ->
                    routineListViewModel.deleteRoutine(id)
                }
                .setNegativeButton(getString(R.string.dialog_no)) { dialog: DialogInterface, _: Int ->
                    dialog.dismiss()
                }
                .show()
        }

        listRecycler.layoutManager = lm
        listRecycler.adapter = adapter
        listRecycler.addItemDecoration(DividerItemDecoration(activity, lm.orientation))

        if (activeList != null && activeList) {
            routineListViewModel.activeList.observe(this, Observer { routines ->
                adapter.submitList(routines)
            })
        } else {
            routineListViewModel.inactiveList.observe(this, Observer { routines ->
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