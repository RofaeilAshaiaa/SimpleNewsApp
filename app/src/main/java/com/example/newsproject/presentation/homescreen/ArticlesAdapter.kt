package com.example.newsproject.presentation.homescreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsproject.R
import com.example.newsproject.data.models.Article
import com.example.newsproject.databinding.LayoutArticleItemBinding
import com.example.newsproject.utils.getDateTimeFormatted

class ArticlesAdapter(
    private var articles: Articles, private var clickListener: (article: Article) -> Unit
) : RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(
        private val binding: LayoutArticleItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
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
                if (article.publishedAt != null) {
                    publishAtDateTv.visibility = View.VISIBLE
                    publishAtDateTv.text = getDateTimeFormatted(article.publishedAt)
                } else {
                    publishAtDateTv.visibility = View.GONE
                }
                Glide.with(binding.root.context)
                    .load(article.urlToImage)
                    .placeholder(R.drawable.ic_outline_image_24)
                    .error(R.drawable.ic_outline_broken_image_24)
                    .into(binding.articleIv)
                root.setOnClickListener {
                    clickListener(article)
                }
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ArticlesAdapter.ArticleViewHolder {
        val binding = LayoutArticleItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticlesAdapter.ArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size

}