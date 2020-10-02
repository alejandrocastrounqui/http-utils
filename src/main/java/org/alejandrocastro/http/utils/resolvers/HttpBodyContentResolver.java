package org.alejandrocastro.http.utils.resolvers;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.alejandrocastro.http.utils.commons.HttpResolver;
import org.alejandrocastro.http.utils.context.HttpMessage;

import com.jayway.jsonpath.JsonPath;

public class HttpBodyContentResolver implements HttpResolver<HttpMessage>{
	
	private Map<String, Function<String, Object>> commands = new HashMap<String, Function<String, Object>>();
	
	public HttpBodyContentResolver(String descriptor) {
		if("".equals(descriptor )) {
			commands.put("MAIN", (c)->c);
		}
		else {
			JsonPath jsonPath = JsonPath.compile(descriptor);
			commands.put("JSON", (c)->jsonPath.read(c));
		}
	}

	@Override
	public Object resolve(HttpMessage source) {
		if(commands.containsKey("MAIN")) {
			return commands.get("MAIN").apply(source.getBodyContent());
		}
		switch (source.getContentType().value) {
			case "JSON":{
				return commands.get("JSON").apply(source.getBodyContent());
			}
		}
		return null;
	}

}
