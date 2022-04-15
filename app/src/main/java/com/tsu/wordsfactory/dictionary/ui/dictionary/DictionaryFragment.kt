package com.tsu.wordsfactory.dictionary.ui.dictionary

import android.R.attr.data
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tsu.wordsfactory.app.WordsApplication
import com.tsu.wordsfactory.databinding.FragmentDictionaryBinding
import com.tsu.wordsfactory.repository.model.Word
import com.tsu.wordsfactory.utils.NetworkUtils


class DictionaryFragment : Fragment() {

    private var _binding: FragmentDictionaryBinding? = null

    // This property is only valid between onCreateView and  onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: ListViewModel

    private val adapter = WordsAdapter()
    private lateinit var networkUtils: NetworkUtils

    @SuppressLint("SetTextI18n")
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

        binding.recyclerView.adapter = adapter

        val cxt: Context = requireActivity().applicationContext
        val wordsApplication = WordsApplication()
        wordsApplication.createRepository(cxt)

        networkUtils = NetworkUtils(cxt)

        val viewModelFactory = WordsViewModelFactory(wordsApplication, networkUtils)
        viewModel = ViewModelProvider(this, viewModelFactory)[ListViewModel::class.java]

        binding.textInputSearch.setEndIconOnClickListener {
            val word = binding.textSearch.text.toString()
            viewModel.loadWord(word)
        }

        binding.btnAddWord.setOnClickListener {
            viewModel.saveWord()
            Toast.makeText(cxt,"Word saved!", Toast.LENGTH_SHORT).show()
        }

        viewModel.words.observe(viewLifecycleOwner, ::bindWord)
    }

    @SuppressLint("SetTextI18n")
    fun bindWord(words: List<Word>) {
        //binding.textView3.text = words[0].word + " " + words[0].phonetic
        adapter.words = words
        Log.d("TAG", "IIt!")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}