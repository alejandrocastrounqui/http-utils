package org.alejandrocastro.http.utils.uri.patterns;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UriPattern {
	
	String raw;
	
	static final Pattern pattern = Pattern.compile("\\{([^\\}]*)\\}");
	static final String[] STRING_EMPTY_ARRAY = new String[0];
	
	UriToken[] uriTokens;
	
	String[] params;
	
    public UriPattern(String raw) {
    	this.raw = raw;
    	String template = sanitize(raw);
    	List<String> paramsList = new LinkedList<String>();
		if(template.length() == 0) {
    		uriTokens = new UriToken[0];
    		params = new String[0];
    		return;
		}
	    String[] values = template.split("\\/");
	    uriTokens = new UriToken[values.length];
	    for (int i = 0; i < values.length; i++) {
	        String value = values[i];
    	    Matcher m = pattern.matcher(value);
	        if(m.find()) {
	    	    String param = m.group(1);
	    	    if(paramsList.contains(param)) {
	    	    	//TODO: notify
	    	    }
	    	    paramsList.add(param);
	    	    uriTokens[i] = new UriParam(param);
	        }
	        else {
	        	uriTokens[i] = new UriConstant(value);
	        }
		}
	    params = paramsList.toArray(STRING_EMPTY_ARRAY);
    }
    
    private String sanitize(String raw) {
    	String result = raw;
    	if(result == null) {
    		result = "";
    	}
    	int queryStart = result.indexOf('?');
		if(queryStart  > -1) {
			result = result.substring(0, queryStart-1);
    	}
		if(result.length() > 0 && '/' == result.charAt(0) ) {
    		result = result.substring(1);
    	}
		if(result.length() > 0 && '/' == result.charAt(result.length() - 1) ) {
			result = result.substring(0, result.length()-1);
		}
		if(result.length() > 0 && '/' == result.charAt(result.length() - 1) ) {
			result = result.substring(0, result.length()-1);
		}
		return result;
    }
    
    public Match matches(String uriPath) {
    	uriPath = sanitize(uriPath);
    	if(uriPath.length() == 0) {
    		return new Match(this.uriTokens.length == 0, 0, null);
		}
    	String[] values = uriPath.split("\\/");
    	if(values.length != uriTokens.length) {
    		return new Match(false, 0, null);
		}
    	Integer weight = 0;
    	Map<String, String> data = new HashMap<String, String>();
    	for (int i = 0; i < values.length; i++) {
    		UriToken token = uriTokens[i];
    		Match tokenMatch = token.matches(values[i]);
    		if(!tokenMatch.matches) {
    			return new Match(false, 0, null);
    		}
    		weight += tokenMatch.weigth;
    		if(tokenMatch.data != null) {
        		data.putAll(tokenMatch.data);
    		}
    	}
    	return new Match(true, weight, data);
    }

	public String getRaw() {
		return raw;
	}

	public String[] getParams() {
		return params;
	}
    

   
    
    
}
