package org.alejandrocastro.http.utils.result;

import java.util.List;
import java.util.Map;

import net.minidev.json.JSONObject;

public class JSONObjectResult implements Result{
	
	JSONObject value;
	
	public JSONObjectResult(JSONObject value) {
		super();
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public List<? extends Object> getListValue() {
		return null;
	}

	@Override
	public Map<String, ? extends Object> getMapValue() {
		return value;
	}

	@Override
	public boolean isList() {
		return false;
	}

	@Override
	public boolean isMap() {
		return true;
	}

	@Override
	public String toString() {
		return value.toJSONString();
	}

}
