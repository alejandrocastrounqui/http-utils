package org.alejandrocastro.http.utils.result;

import java.util.List;
import java.util.Map;

public interface Result {
		
	Object getValue();
	
	List<? extends Object> getListValue();
	
	Map<String, ? extends Object> getMapValue();
	
	boolean isList();
	
	boolean isMap();
	
	String toString();

}
