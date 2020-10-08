package org.alejandrocastro.http.utils.result;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AtomResult implements Result{
	
	Object value;
	
	final static private ObjectMapper objectMapper = new ObjectMapper();
		
	public AtomResult(Object value) {
		super();
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<? extends Object> getListValue() {
		if(value instanceof List) {
			return (List<? extends Object>) value;
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, ? extends Object> getMapValue() {
		if(value instanceof Map) {
			return (Map<String, ? extends Object>) value;
		}
		return null;
	}

	@Override
	public boolean isList() {
		return value instanceof List;
	}

	@Override
	public boolean isMap() {
		return value instanceof Map;
	}

	public String toString() {
		try {
			return objectMapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			return String.valueOf(value);
		}
	}

}
