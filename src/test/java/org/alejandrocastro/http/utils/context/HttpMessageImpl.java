package org.alejandrocastro.http.utils.context;

import java.util.List;
import java.util.Map;

public class HttpMessageImpl implements HttpMessage{

	private Map<String, List<String>> header;
	private ContentType contentType;
	private String bodyContent;
	
	public HttpMessageImpl(Map<String, List<String>> header, ContentType contentType, String bodyContent) {
		super();
		this.header = header;
		this.contentType = contentType;
		this.bodyContent = bodyContent;
	}

	@Override
	public Map<String, List<String>> getHeader() {
		return header;
	}

	@Override
	public ContentType getContentType() {
		return contentType;
	}

	@Override
	public String getBodyContent() {
		return bodyContent;
	}

}
