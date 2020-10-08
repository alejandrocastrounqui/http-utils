package org.alejandrocastro.http.utils.resolvers;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

import org.alejandrocastro.http.utils.commons.HttpResolver;
import org.alejandrocastro.http.utils.result.AtomResult;
import org.alejandrocastro.http.utils.result.HeaderResult;
import org.alejandrocastro.http.utils.result.Result;
import org.apache.commons.beanutils.PropertyUtilsBean;

public class HttpHeadersResolver implements HttpResolver<Map<String, String>>{
	
	String descriptor;

	PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean(); 

	public HttpHeadersResolver(String descriptor) {
		super();
		this.descriptor = descriptor;
	}
	
	//request.headers.content-type[0]
	//request.headers.content-type
	// Content-type
	
	@Override
	public Result resolve(Map<String, String> source) {
		if("letras numero y guiones" == descriptor/* descriptor solo es letras numero y guiones*/) {
			return new HeaderResult(source.get(descriptor));
		}
		try {
			Object value = propertyUtilsBean.getProperty(source, descriptor);
			return new AtomResult(value);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}

}
