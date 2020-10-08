package org.alejandrocastro.http.utils.result;

import java.util.List;
import java.util.Map;

import net.minidev.json.JSONArray;

public class JSONArrayResult implements Result{
	
	JSONArray value;
		
	public JSONArrayResult(JSONArray value) {
		super();
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public List<? extends Object> getListValue() {
		return value;
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
		return value.toJSONString();
	}

}
