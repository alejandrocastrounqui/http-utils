package org.alejandrocastro.http.utils.context;

public enum ContentType {
	
	JSON("JSON"),
	XML("XML"),
	TXT("TXT");
	
	public final String value;
	
    private ContentType(String value) {
        this.value = value;
    }
	

}
