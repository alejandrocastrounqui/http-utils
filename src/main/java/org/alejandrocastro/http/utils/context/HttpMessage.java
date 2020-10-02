package org.alejandrocastro.http.utils.context;

import java.util.List;
import java.util.Map;


public interface HttpMessage {
	
	Map<String, List<String>> getHeaders();
	
	ContentType getContentType();
	
	String getBodyContent();

}
