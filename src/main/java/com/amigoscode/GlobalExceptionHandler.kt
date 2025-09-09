package com.amigoscode

import graphql.ErrorType
import graphql.GraphQLError
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler
import org.springframework.web.bind.annotation.ControllerAdvice

@ControllerAdvice
class GlobalExceptionHandler {
    @GraphQlExceptionHandler
    fun handleGenericException(e: GenericNotFound): GraphQLError {
        return GraphQLError.newError()
            .errorType(ErrorType.DataFetchingException)
            .message(e.message)
            .build()
    }
}