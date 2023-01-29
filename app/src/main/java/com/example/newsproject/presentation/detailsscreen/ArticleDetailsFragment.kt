package com.example.newsproject.presentation.detailsscreen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.newsproject.R
import com.example.newsproject.data.models.Article
import com.example.newsproject.databinding.FragmentArticleDetailsBinding
import com.example.newsproject.utils.getDateTimeFormatted

class ArticleDetailsFragment : Fragment() {

    private var _binding: FragmentArticleDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: ArticleDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        args.article?.let {
            renderUi(it)
        }
    }

    private fun renderUi(article: Article) {
        with(binding) {
            if (article.source != null) {
                articleSourceTv.visibility = View.VISIBLE
                articleSourceTv.text = "${article.source?.name}"
            } else {
                articleSourceTv.visibility = View.GONE
            }
            article.title?.let {
                titleTv.text = it
            }
            article.description?.let {
                articleDescriptionTv.text = it
            }
            article.content?.let {
                articleContentIv.text = it
            }
            if (article.publishedAt == null && article.author == null) {
                authorDateTv.visibility = View.GONE
            } else if (article.publishedAt != null && article.author != null) {
                authorDateTv.text = getString(
                    R.string.article_author_date_place_holder,
                    article.author.toString(),
                    getDateTimeFormatted(article.publishedAt)
                )
            } else if (article.publishedAt == null) {
                authorDateTv.text = article.author
            } else {
                authorDateTv.text = getDateTimeFormatted(article.publishedAt)
            }
            Glide.with(binding.root.context)
                .load(article.urlToImage)
                .placeholder(R.drawable.ic_search_24)
                .into(binding.articleIv)
            if (article.url.isNullOrEmpty()) {
                extendedFab.visibility = View.GONE
            } else {
                extendedFab.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                    startActivity(intent)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}