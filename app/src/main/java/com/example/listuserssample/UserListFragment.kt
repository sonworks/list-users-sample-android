package com.example.listuserssample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listuserssample.databinding.FragmentUserListBinding
import com.example.model.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private val viewModel: UserListViewModel by viewModels()

    private var _binding: FragmentUserListBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userListView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val adapter = UserListAdapter()
        binding.userListView.adapter = adapter
        adapter.setOnUserItemClickListener(
            object : UserListAdapter.OnUserItemClickListener {
                override fun onItemClick(user: User) {
                    findNavController().navigate(
                        UserListFragmentDirections.actionUserListFragmentToUserDetailFragment(
                            userName = user.login
                        )
                    )
                }
            }
        )

        viewModel.userList.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter.replaceAll(it)
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getUserList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
