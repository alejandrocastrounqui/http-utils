package org.alejandrocastro.http.utils.context;

import java.util.Map;


public interface HttpMessage {
	
	Map<String, String> getHeaders();
	
	ContentType getContentType();
	
	String getBodyContent();

}
