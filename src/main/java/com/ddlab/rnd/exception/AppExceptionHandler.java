package com.ddlab.rnd.exception;

import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;

@ControllerAdvice
public class AppExceptionHandler {

	@GraphQlExceptionHandler
	public GraphQLError handleValidationException(RuntimeException ex, DataFetchingEnvironment env) {
		return GraphqlErrorBuilder
				.newError()
				.message("Error Message: " + ex.getMessage())
				.errorType(ErrorType.BAD_REQUEST)
				.build();
	}
}
