package org.alejandrocastro.http.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;

import org.alejandrocastro.http.utils.context.ContentType;
import org.alejandrocastro.http.utils.context.HttpContext;
import org.alejandrocastro.http.utils.context.HttpContextImpl;
import org.alejandrocastro.http.utils.resolvers.HttpContextResolver;
import org.alejandrocastro.http.utils.result.Result;
import org.junit.jupiter.api.Test;

import com.jayway.jsonpath.PathNotFoundException;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;


class ResolverTests {
	
	private static final String REQUEST_BODY = "{\n" + 
		"  \"items\":[{\n" + 
		"    \"value\": 1\n" + 
		"  },{\n" + 
		"    \"value\": \"text\"\n" + 
		"  },{\n" + 
		"    \"nested_items\": [\"value1\",\"value2\",\"value3\"]\n" + 
		"  }],\n" + 
		"  \"simple_value_key\":\"value1\",\n" + 
		"  \"map_value_key\":{\n" + 
		"    \"nested_key1\":\"nested_value1\"\n" + 
		"  }\n" + 
		"}";
	
	private static final String RESPONSE_BODY = "{\n" + 
		"  \"items\":[{\n" + 
		"    \"value\": 1\n" + 
		"  },{\n" + 
		"    \"value\": \"text\"\n" + 
		"  },{\n" + 
		"    \"nested_items\": [\"value1\",\"value2\",\"value3\"]\n" + 
		"  }],\n" + 
		"  \"simple_value_key\":\"value1\",\n" + 
		"  \"map_value_key\":{\n" + 
		"    \"nested_key1\":\"nested_value1\"\n" + 
		"  }\n" + 
		"}";
	
	HttpContext httpContext = HttpContextImpl.builder()
		.withRequest()
			.withBodyContent(REQUEST_BODY)
			.withContentType(ContentType.JSON)
			.withHeader("content-length", "100")
			.withPath("/v1/pom/change-rate")
			.end()
		.withResponse()
			.withBodyContent(RESPONSE_BODY)
			.withContentType(ContentType.JSON)
		    .end()
		.build();

	@Test
	void resolveResponseJsonBodyContentAttribute() {
		HttpContextResolver httpContextResolver = new HttpContextResolver("response.body.simple_value_key");
		Object result = httpContextResolver.resolve(httpContext).getValue();
		assertThat(result, equalTo("value1"));
	}
	
	@Test
	void resolveResponseJsonBodyContentArrayIntegerItemByIndex() {
		HttpContextResolver httpContextResolver = new HttpContextResolver("response.body.items[0].value");
		Object result = httpContextResolver.resolve(httpContext).getValue();
		assertThat(result, equalTo(1));
	}
	
	@Test
	void resolveResponseJsonBodyContentArrayIntegerItemTypeByIndex() {
		HttpContextResolver httpContextResolver = new HttpContextResolver("response.body.items[0].value");
		Object result = httpContextResolver.resolve(httpContext).getValue();
		assertThat(result, instanceOf(Integer.class));
	}
	
	@Test
	void resolveResponseJsonBodyContentArrayStringItemByIndex() {
		HttpContextResolver httpContextResolver = new HttpContextResolver("response.body.items[1].value");
		Object result = httpContextResolver.resolve(httpContext).getValue();
		assertThat(result, equalTo("text"));
	}
	
	@Test
	void resolveResponseJsonBodyContentArrayStringItemTypeByIndex() {
		HttpContextResolver httpContextResolver = new HttpContextResolver("response.body.items[1].value");
		Object result = httpContextResolver.resolve(httpContext).getValue();
		assertThat(result, instanceOf(String.class));
	}
		
	@Test
	void resolveResponseJsonBodyContentJsonArrayByIndex() {
		HttpContextResolver httpContextResolver = new HttpContextResolver("response.body.items");
		Result result = httpContextResolver.resolve(httpContext);
		Object actual = result.getValue();
		assertThat(actual, instanceOf(List.class));
		if(actual instanceof JSONArray) {
			JSONArray actualJson = (JSONArray) actual;
			assertThat(actualJson, hasSize(3));		
		}
	}
	
	@Test
	void resolveResponseJsonBodyContentJsonArrayTypeByIndex() {
		HttpContextResolver httpContextResolver = new HttpContextResolver("response.body.items");
		Object result = httpContextResolver.resolve(httpContext).getValue();
		assertThat(result, instanceOf(List.class));
	}
	
	@Test
	void resolveResponseJsonBodyContentJsonArrayJsonTypeByIndex() {
		HttpContextResolver httpContextResolver = new HttpContextResolver("response.body.items");
		Object result = httpContextResolver.resolve(httpContext).getValue();
		assertThat(result, instanceOf(JSONArray.class));
	}
	
	@Test
	void resolveResponseJsonBodyContentJsonMapByIndex() {
		HttpContextResolver httpContextResolver = new HttpContextResolver("response.body.map_value_key");
		Object result = httpContextResolver.resolve(httpContext).getValue();
		assertThat(result, instanceOf(Map.class));
		if(result instanceof JSONObject) {
			JSONObject actual = (JSONObject) result;
			assertThat(actual, hasKey("nested_key1"));		
		}
	}
	
	@Test
	void resolveResponseJsonBodyContentJsonMapTypeByIndex() {
		HttpContextResolver httpContextResolver = new HttpContextResolver("response.body.map_value_key");
		Object result = httpContextResolver.resolve(httpContext).getValue();
		assertThat(result, instanceOf(Map.class));
	}
	
	@Test
	void resolveResponseJsonBodyContentJsonMapJsonTypeByIndex() {
		HttpContextResolver httpContextResolver = new HttpContextResolver("response.body.map_value_key");
		Object result = httpContextResolver.resolve(httpContext).getValue();
		assertThat(result, instanceOf(Map.class));
	}
	
	@Test
	void resolveResponseJsonBodyContentInexistentItem() {
		assertThrows(PathNotFoundException.class, ()->{
        	HttpContextResolver httpContextResolver = new HttpContextResolver("response.body.items[8].value");
        	httpContextResolver.resolve(httpContext);
        });
	}
	
	@Test
	void resolveResponseRawJsonBody() {
		HttpContextResolver httpContextResolver = new HttpContextResolver("request.body");
		Object result = httpContextResolver.resolve(httpContext).toString();
		assertThat(result, equalTo(REQUEST_BODY));
	}
	
	
}
