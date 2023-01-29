package com.example.newsproject.presentation.homescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.newsproject.databinding.FragmentArticlesBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ArticlesFragment : Fragment() {

    private var _binding: FragmentArticlesBinding? = null
    private val binding get() = _binding!!
    val viewModel: ArticlesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticlesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeStateChanges()
        configureSearchView()
    }

    private fun observeStateChanges() {
        viewModel.articles.observe(viewLifecycleOwner) {
            renderUi(it)
        }
        viewModel.searchResult.observe(viewLifecycleOwner) {
            renderUi(it)
        }
        viewModel.message.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            when (it) {
                true -> binding.progressBar.visibility = View.VISIBLE
                false -> binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun configureSearchView() {
        with(binding.searchView) {
            onActionViewExpanded()
            isIconified = false
            clearFocus()
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (!newText.isNullOrEmpty())
                        viewModel.performSearchQuery(newText)
                    else
                        viewModel.clearSearch()
                    return false
                }

            })
        }
    }

    private fun renderUi(articles: Articles?) {
        articles?.let {
            binding.articlesRv.adapter = ArticlesAdapter(it) { article ->
                findNavController().navigate(
                    ArticlesFragmentDirections.actionArticlesFragmentToArticleDetailsFragment(
                        article
                    )
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}