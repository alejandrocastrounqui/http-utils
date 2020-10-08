package org.alejandrocastro.http.utils.result;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.Configuration;

public class JSONStringResult implements Result{
	
	String value;
		
	Object jsonObject;
		
	public JSONStringResult(String value) {
		super();
		this.value = value;
	}

	@Override
	public Object getValue() {
		if(jsonObject == null) {
			Configuration defaultConfiguration = Configuration.defaultConfiguration();
			jsonObject = defaultConfiguration.jsonProvider().parse(value);
		}
		return jsonObject;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<? extends Object> getListValue() {
		Object jsonObject = getValue();
		if(jsonObject instanceof List) {
			return (List<? extends Object>) jsonObject;
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, ? extends Object> getMapValue() {
		Object jsonObject = getValue();
		if(jsonObject instanceof Map) {
			return (Map<String, ? extends Object>) jsonObject;
		}
		return null;
	}

	@Override
	public boolean isList() {
		Object jsonObject = getValue();
		return jsonObject instanceof List;
	}

	@Override
	public boolean isMap() {
		Object jsonObject = getValue();
		return jsonObject instanceof Map;
	}
	
	@Override
	public String toString() {
		return value;
	}

}
