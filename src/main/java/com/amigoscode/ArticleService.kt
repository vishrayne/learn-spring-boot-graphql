package com.amigoscode

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
object ArticleService {

    private val users = List(5) { id ->
        val nonZeroId = id + 1

        User(
            id = "user-id-$nonZeroId",
            name = "user-name-$nonZeroId",
        )
    }.toMutableList()

    private val articles = List(5) { id ->
        val nonZeroId = id + 1

        Article(
            id = "article-id-$nonZeroId",
            title = "article-title-$nonZeroId",
            content = "article-content-$nonZeroId",
            authorId = users.random().id,
            createdAt = LocalDateTime.now().toString(),
        )
    }.toMutableList()

    private val comments = List(5) { id ->
        val nonZeroId = id + 1
        Comment(
            id = "comment-id-$nonZeroId",
            content = "comment-content-$nonZeroId",
            articleId = articles.random().id,
            userId = users.random().id,
        )
    }.toMutableList()

    suspend fun findArticleById(id: String): Article? {
        delay(200)
        return articles.firstOrNull { it.id == id }
    }

    fun findAllArticles(): Flow<Article> {
        return articles.asFlow()
    }

    suspend fun findUserById(id: String): User? {
        delay(200)
        return users.firstOrNull { it.id == id }
    }

    fun findCommentsByArticleId(id: String): Flow<Comment> {
        return comments.filter { it.articleId == id }.asFlow()
    }

    suspend fun createArticle(input: CreateArticleInput): Article {
        delay(200)

        return Article(
            id = UUID.randomUUID().toString(),
            title = input.title,
            content = input.content,
            authorId = input.userId,
            createdAt = LocalDateTime.now().toString(),
        ).also(articles::add)
    }

    suspend fun addComment(id: String, input: AddCommentInput): Comment {
        delay(200)

        users.firstOrNull { it.id == input.userId }
            ?: throw GenericNotFound("User Not Found")

        return articles.firstOrNull { it.id == id }
            ?.let { article ->
                Comment(
                    id = UUID.randomUUID().toString(),
                    content = input.content,
                    articleId = article.id,
                    userId = input.userId
                )
            }
            ?: throw GenericNotFound("Article Not Found - $id")
    }
}