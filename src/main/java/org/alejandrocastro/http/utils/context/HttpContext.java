package org.alejandrocastro.http.utils.context;

public interface HttpContext {
	
	HttpRequest getRequest();
	
	HttpMessage getResponse();

}
