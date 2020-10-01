package org.alejandrocastro.http.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.alejandrocastro.http.utils.context.ContentType;
import org.alejandrocastro.http.utils.context.HttpContext;
import org.alejandrocastro.http.utils.context.HttpContextImpl;
import org.alejandrocastro.http.utils.context.HttpMessageImpl;
import org.alejandrocastro.http.utils.resolvers.HttpContextResolver;
import org.junit.jupiter.api.Test;


class ResolverTests {

	@Test
	void resolveJsonBodyContentAttribute() {
		HttpContextResolver httpContextResolver = new HttpContextResolver("response.body.currency");
		HttpContext httpContext = new HttpContextImpl(null, 
			new HttpMessageImpl(null, ContentType.JSON, "{\"currency\":\"123\"}") 
		);
		Object result = httpContextResolver.resolve(httpContext);
		assertThat("123", is(result));
	}
	
	@Test
	void resolveJsonBodyContentArrayByIndex() {
		HttpContextResolver httpContextResolver = new HttpContextResolver("response.body.items[0]");
		HttpContext httpContext = new HttpContextImpl(null, 
			new HttpMessageImpl(null, ContentType.JSON, "{\"items\":[\"123\"]}") 
		);
		Object result = httpContextResolver.resolve(httpContext);
		assertThat("123", is(result));
	}
	
	@Test
	void resolveRawJsonBody() {
		HttpContextResolver httpContextResolver = new HttpContextResolver("response.body");
		HttpContext httpContext = new HttpContextImpl(null, 
			new HttpMessageImpl(null, ContentType.JSON, "{\"items\":[\"123\"]}") 
		);
		Object result = httpContextResolver.resolve(httpContext);
		assertThat( "{\"items\":[\"123\"]}", is(result));
	}
}
