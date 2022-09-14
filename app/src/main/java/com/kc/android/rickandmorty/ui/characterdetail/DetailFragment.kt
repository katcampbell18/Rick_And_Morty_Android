package com.kc.android.rickandmorty.ui.characterdetail

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.kc.android.rickandmorty.data.entities.CharacterItem
import com.kc.android.rickandmorty.databinding.FragmentDetailBinding
import com.kc.android.rickandmorty.utils.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        getData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getData() {
        viewModel.character.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindCharacter(it.data)
                    binding.progressBar.visibility = View.GONE
                    binding.characterDetailLayout.visibility = View.VISIBLE
                    Log.d(TAG, "character: " + bindCharacter(it.data))
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.characterDetailLayout.visibility = View.GONE
                }
            }
        })
    }

    //TODO: add Location and Episode params for textviews
    private fun bindCharacter(character: CharacterItem?) {
        if (character != null) {
            binding.nameDetailTextView.text = character.name
            binding.speciesTextView.text = character.species
            binding.statusTextView.text = character.status
            binding.genderTextView.text = character.gender

            Glide.with(binding.root)
                .load(character.image)
                .transform(CircleCrop())
                .into(binding.characterDetailImageView)
        }
    }
}