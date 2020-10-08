package org.alejandrocastro.http.utils.resolvers;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.alejandrocastro.http.utils.commons.HttpResolver;
import org.alejandrocastro.http.utils.context.HttpMessage;
import org.alejandrocastro.http.utils.result.AtomResult;
import org.alejandrocastro.http.utils.result.JSONArrayResult;
import org.alejandrocastro.http.utils.result.JSONObjectResult;
import org.alejandrocastro.http.utils.result.JSONStringResult;
import org.alejandrocastro.http.utils.result.Result;

import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class HttpBodyContentResolver implements HttpResolver<HttpMessage>{
	
	private Map<String, Function<String, Result>> commands = new HashMap<String, Function<String, Result>>();
	
	public HttpBodyContentResolver(String descriptor) {
		if("".equals(descriptor )) {
			commands.put("MAIN", (c)-> new JSONStringResult(c));
		}
		else {
			JsonPath jsonPath = JsonPath.compile(descriptor);
			commands.put("JSON", (c)-> {
				Object value = jsonPath.read(c);
				if(value instanceof JSONArray) {
					return new JSONArrayResult((JSONArray) value);
				}
				if(value instanceof JSONObject) {
					return new JSONObjectResult((JSONObject) value);
				}
				return new AtomResult(value);
			});
		}
	}

	@Override
	public Result resolve(HttpMessage source) {
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
