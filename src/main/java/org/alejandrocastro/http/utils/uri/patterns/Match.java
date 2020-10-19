package org.alejandrocastro.http.utils.uri.patterns;

import java.util.Map;

public class Match {
	
	Boolean matches;
	Integer weigth;
	Map<String, String> data;
	
	public Match(Boolean matches, Integer weigth, Map<String, String> data) {
		super();
		this.matches = matches;
		this.weigth = weigth;
		this.data = data;
	}

	public Boolean matches() {
		return matches;
	}

	public Integer weigth() {
		return weigth;
	}

	public Map<String, String> data() {
		return data;
	}

	
}
