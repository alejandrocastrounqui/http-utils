package org.alejandrocastro.http.utils.context;

public class HttpContextImpl implements HttpContext{
	
	HttpRequest request;
	HttpMessage response;

	private HttpContextImpl(Builder builder) {
		this.request = builder.request;
		this.response = builder.response;
	}
	
	public HttpContextImpl(HttpRequest request, HttpMessage response) {
		super();
		this.request = request;
		this.response = response;
	}

	@Override
	public HttpRequest getRequest() {
		return request;
	}

	@Override
	public HttpMessage getResponse() {
		return response;
	}
	
	public void setRequest(HttpRequest request) {
		this.request = request;
	}

	public void setResponse(HttpMessage response) {
		this.response = response;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private HttpRequest request;
		private HttpMessage response;

		private Builder() {}
		
		public static Builder init() {
			return new Builder();
		}

		public Builder withRequest(HttpRequest request) {
			this.request = request;
			return this;
		}
		
		public HttpRequestImpl.Builder withRequest() {
			return new HttpRequestImpl.Builder(this);
		}

		public Builder withResponse(HttpMessage response) {
			this.response = response;
			return this;
		}
		
		public HttpMessageImpl.Builder withResponse() {
			return new HttpMessageImpl.Builder(this);
		}

		public HttpContextImpl build() {
			return new HttpContextImpl(this);
		}
	}
		
}
