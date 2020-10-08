package org.alejandrocastro.http.utils.result;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HeaderResult implements Result{
	
	String value;
	
	final static private ObjectMapper objectMapper = new ObjectMapper();
		
	public HeaderResult(String value) {
		super();
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public List<? extends Object> getListValue() {
		return Arrays.asList(value.split(";"));
	}

	@Override
	public Map<String, ? extends Object> getMapValue() {
		return null;
	}

	@Override
	public boolean isList() {
		return true;
	}

	@Override
	public boolean isMap() {
		return false;
	}

	public String toString() {
		return value;
	}

}
