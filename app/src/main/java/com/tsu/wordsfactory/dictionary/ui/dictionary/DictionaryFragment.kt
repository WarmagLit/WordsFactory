package com.tsu.wordsfactory.dictionary.ui.dictionary

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tsu.wordsfactory.R
import com.tsu.wordsfactory.network.Network
import com.tsu.wordsfactory.databinding.FragmentDictionaryBinding
import com.tsu.wordsfactory.dictionary.ui.dictionary.adapter.WordsAdapter
import com.tsu.wordsfactory.repository.model.Word
import com.tsu.wordsfactory.utils.NetworkUtils


class DictionaryFragment : Fragment() {

    private var _binding: FragmentDictionaryBinding? = null

    // This property is only valid between onCreateView and  onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: ListViewModel


    private lateinit var networkUtils: NetworkUtils
    private lateinit var adapter: WordsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDictionaryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cxt: Context = requireContext()
        Network.createRepository(cxt)

        networkUtils = NetworkUtils(cxt)
        adapter = WordsAdapter(networkUtils)

        binding.recyclerView.adapter = adapter

        val viewModelFactory = WordsViewModelFactory(networkUtils)
        viewModel = ViewModelProvider(this, viewModelFactory)[ListViewModel::class.java]

        binding.textInputSearch.setEndIconOnClickListener {
            val word = binding.textSearch.text.toString()
            viewModel.loadWord(word)
        }

        binding.btnAddWord.setOnClickListener {
            viewModel.saveWord()
            Toast.makeText(cxt,getString(R.string.word_saved), Toast.LENGTH_SHORT).show()
        }

        viewModel.words.observe(viewLifecycleOwner, ::bindWord)

    }


    private fun bindWord(words: List<Word>) {
        adapter.words = words
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}