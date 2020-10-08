package org.alejandrocastro.http.utils.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequestImpl implements HttpRequest{

	private Map<String, String> headers;
	private ContentType contentType;
	private String bodyContent;
	private Map<String, List<String>> queryParams;
	private String path;
	private String uri;
	
	private HttpRequestImpl(Builder builder) {
		this.headers = builder.headers;
		this.contentType = builder.contentType;
		this.bodyContent = builder.bodyContent;
		this.queryParams = builder.queryParams;
		this.path = builder.path;
		this.uri = builder.uri;
	}
	
	public HttpRequestImpl(Map<String, String> header, ContentType contentType, String bodyContent) {
		super();
		this.headers = header;
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

	@Override
	public String getUri() {
		return uri;
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public Map<String, List<String>> getQueryParams() {
		return queryParams;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder{
		private Map<String, String> headers = new HashMap<String, String>();
		private ContentType contentType;
		private String bodyContent;
		private Map<String, List<String>> queryParams = new HashMap<String, List<String>>();
		private String path;
		private String uri;
		private HttpContextImpl.Builder httpContextBuilder;

		private Builder() {}
		
		public Builder(HttpContextImpl.Builder httpContextBuilder) {
			super();
			this.httpContextBuilder = httpContextBuilder;
		}

		public HttpContextImpl.Builder end() {
			HttpRequestImpl httpRequestImpl = new HttpRequestImpl(this);
			httpContextBuilder.withRequest(httpRequestImpl);
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

		public Builder withQueryParams(Map<String, List<String>> queryParams) {
			this.queryParams = queryParams;
			return this;
		}
		
		public Builder withQueryParam(String key, List<String> values) {
			this.queryParams.put(key, values);
			return this;
		}

		public Builder withPath(String path) {
			this.path = path;
			return this;
		}

		public Builder withUri(String uri) {
			this.uri = uri;
			return this;
		}

		public HttpRequestImpl build() {
			return new HttpRequestImpl(this);
		}
	}



	
}
