package com.kc.android.rickandmorty.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.kc.android.rickandmorty.R
import com.kc.android.rickandmorty.databinding.FragmentCharactersBinding
import com.kc.android.rickandmorty.utils.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharactersFragment : Fragment(), CharactersAdapter.CharacterItemListener {
    
    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: CharactersViewModel by viewModels()
    private lateinit var adapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        getData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        adapter = CharactersAdapter(this)
        binding.charactersRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.charactersRecyclerView.adapter = adapter
    }

    private fun getData() {
        viewmodel.characters.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty())
                        adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onClickedCharacter(characterId: Int) {
        findNavController().navigate(R.id.action_charactersFragment_to_detailFragment,
        bundleOf("id" to characterId))
    }
}