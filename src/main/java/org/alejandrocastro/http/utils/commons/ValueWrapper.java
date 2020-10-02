package org.alejandrocastro.http.utils.commons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

public class ValueWrapper {
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	Object value;
		
	public ValueWrapper(Object value) {
		super();
		this.value = value;
	}

	public String toJSONString() {
		if(value == null) {
			return "";
		}
		if(value instanceof JSONArray) {
			return ((JSONArray) value).toJSONString();
		}
		if(value instanceof JSONObject) {
			return ((JSONObject) value).toJSONString();
		}
		if(value instanceof JSONValue) {
			return ((JSONValue) value).toString();
		}
		try {
			return objectMapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			return String.valueOf(value);
		}
	}

}
