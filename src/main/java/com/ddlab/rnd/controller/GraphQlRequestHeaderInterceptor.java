package com.ddlab.rnd.controller;

import java.util.Collections;

import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;

import graphql.GraphQLContext;
import reactor.core.publisher.Mono;

@Component
public class GraphQlRequestHeaderInterceptor implements WebGraphQlInterceptor {
	
	@Override
	public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
		String value1 = request.getHeaders().getFirst("myHeader1");
		String value2 = request.getHeaders().getFirst("myHeader2");

		if (value1 != null && value2 != null) {
			request.configureExecutionInput(
					(executionInput, builder) -> builder.graphQLContext(Collections.singletonMap("myHeader1", value1))
							.graphQLContext(Collections.singletonMap("myHeader2", value2)).build());
		}
        return chain.next(request).doOnNext((response) -> {
        	GraphQLContext graphQlContext = response.getExecutionInput().getGraphQLContext();
        	String responseValue1 = graphQlContext.get("ResponseHeader1");
        	String responseValue2 = graphQlContext.get("ResponseHeader1");
            if(responseValue1 != null && responseValue2 != null)
            	response.getResponseHeaders().add("ResponseHeader1", responseValue1);
            response.getResponseHeaders().add("ResponseHeader2", responseValue2);
        });

//		return chain.next(request);
	}

////    private final KLogger log = KotlinLogging.logger();
//
//    @Override
//    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
//        Map<String, String> headers = getHeadersFromRequest(request);
////        log.trace(() -> String.format("Found %d headers that will be added to the GQL-context: %s", headers.size(), headers));
//        addHeadersToGraphQLContext(request, headers);
//        return chain.next(request);
//    }
//
//    private Map<String, String> getHeadersFromRequest(WebGraphQlRequest request) {
//        return request.getHeaders().toSingleValueMap();
//    }
//
//    private void addHeadersToGraphQLContext(WebGraphQlRequest request, Map<String, String> headers) {
//        request.configureExecutionInput((input, builder) -> builder.graphQLContext((Consumer<Builder>) headers).build());
//    }
}
