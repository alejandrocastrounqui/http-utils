package org.alejandrocastro.http.utils.uri.patterns;

import java.util.Map;

public class UriParam implements UriToken{
	
	String name;
	
	public UriParam(String name) {
		this.name = name;
	}

	@Override
	public Match matches(String value) {
		Map<String, String> data = Map.of(name, value);
		return new Match(true, 1, data);
	}

}
