package org.alejandrocastro.http.utils.uri.patterns;

public class UriConstant implements UriToken{
	
	String value;

	public UriConstant(String value) {
		super();
		this.value = value;
	}

	@Override
	public Match matches(String value) {
		if(this.value.equals(value)) {
			return new Match(true, 2, null);
		}
		return new Match(false, null, null);
	}

}
