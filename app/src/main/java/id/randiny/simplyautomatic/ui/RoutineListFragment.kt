package id.randiny.simplyautomatic.ui

import android.content.DialogInterface
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
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

        val orientation = resources.configuration.orientation
        val lm: RecyclerView.LayoutManager
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            lm = GridLayoutManager(activity, 2)
            listRecycler.addItemDecoration(
                DividerItemDecoration(
                    activity,
                    DividerItemDecoration.HORIZONTAL
                )
            )
            listRecycler.addItemDecoration(
                DividerItemDecoration(
                    activity,
                    DividerItemDecoration.VERTICAL
                )
            )
        } else {
            lm = LinearLayoutManager(activity)
            listRecycler.addItemDecoration(DividerItemDecoration(activity, lm.orientation))
        }

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

        if (activeList != null && activeList) {
            routineListViewModel.activeList.observe(viewLifecycleOwner, Observer { routines ->
                adapter.submitList(routines)
            })
        } else {
            routineListViewModel.inactiveList.observe(viewLifecycleOwner, Observer { routines ->
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