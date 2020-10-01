package org.alejandrocastro.http.utils.context;

public class HttpContextImpl implements HttpContext{
	
	HttpRequest request;
	HttpMessage response;
	
	public HttpContextImpl(HttpRequest request, HttpMessage response) {
		super();
		this.request = request;
		this.response = response;
	}

	@Override
	public HttpRequest getRequest() {
		// TODO Auto-generated method stub
		return request;
	}

	@Override
	public HttpMessage getResponse() {
		// TODO Auto-generated method stub
		return response;
	}

	
}
