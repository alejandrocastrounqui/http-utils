package org.alejandrocastro.http.utils.context;

import java.util.List;
import java.util.Map;


public interface HttpRequest extends HttpMessage{
	
	String getUri();
	
	String getPath();
	
	Map<String, List<String>> getQueryParams();

}
