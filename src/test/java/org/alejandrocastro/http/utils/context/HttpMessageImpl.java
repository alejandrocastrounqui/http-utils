package org.alejandrocastro.http.utils.context;

import java.util.HashMap;
import java.util.Map;

public class HttpMessageImpl implements HttpMessage{

	private Map<String, String> headers;
	private ContentType contentType;
	private String bodyContent;

	private HttpMessageImpl(Builder builder) {
		this.headers = builder.headers;
		this.contentType = builder.contentType;
		this.bodyContent = builder.bodyContent;
	}
	
	public HttpMessageImpl(
		Map<String, String> headers, 
		ContentType contentType, 
		String bodyContent
	) {
		super();
		this.headers = headers;
		this.contentType = contentType;
		this.bodyContent = bodyContent;
	}
	
	@Override
	public Map<String, String> getHeaders() {
		return headers;
	}

	@Override
	public ContentType getContentType() {
		return contentType;
	}

	@Override
	public String getBodyContent() {
		return bodyContent;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private Map<String, String> headers = new HashMap<String, String>();
		private ContentType contentType;
		private String bodyContent;
		HttpContextImpl.Builder httpContextBuilder;

		private Builder() {}

		public Builder(HttpContextImpl.Builder httpContextBuilder) {
			super();
			this.httpContextBuilder = httpContextBuilder;
		}

		public HttpContextImpl.Builder end() {
			HttpMessageImpl httpResponseImpl = new HttpMessageImpl(this);
			httpContextBuilder.withResponse(httpResponseImpl);
			return httpContextBuilder;
		}

		public Builder withHeaders(Map<String, String> header) {
			this.headers = header;
			return this;
		}

		public Builder withHeader(String key, String values) {
			this.headers.put(key, values);
			return this;
		}

		public Builder withContentType(ContentType contentType) {
			this.contentType = contentType;
			return this;
		}

		public Builder withBodyContent(String bodyContent) {
			this.bodyContent = bodyContent;
			return this;
		}

		public HttpMessageImpl build() {
			return new HttpMessageImpl(this);
		}
	}

}
