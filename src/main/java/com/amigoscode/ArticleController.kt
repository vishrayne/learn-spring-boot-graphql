package com.amigoscode

import kotlinx.coroutines.flow.Flow
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class ArticleController(
    private val articleService: ArticleService,
) {

    @QueryMapping
    suspend fun article(@Argument id: String): Article? {
        return articleService.findArticleById(id)
    }

    @QueryMapping
    fun articles(): Flow<Article> {
        return articleService.findAllArticles()
    }

    @SchemaMapping
    suspend fun author(article: Article): User {
        return articleService.findUserById(article.authorId)!!
    }

    @SchemaMapping
    suspend fun author(comment: Comment): User {
        return articleService.findUserById(comment.userId)!!
    }

    @SchemaMapping
    fun comments(article: Article): Flow<Comment> {
        return articleService.findCommentsByArticleId(article.id)
    }

    @MutationMapping
    suspend fun createArticle(
        @Argument input: CreateArticleInput
    ): Article {
        return articleService.createArticle(input)
    }

    @MutationMapping
    suspend fun addComment(
        @Argument articleId: String,
        @Argument input: AddCommentInput,
    ): Comment {
        return articleService.addComment(articleId, input)
    }
}