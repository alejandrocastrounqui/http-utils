package org.alejandrocastro.http.utils.commons;

import org.alejandrocastro.http.utils.result.Result;

@FunctionalInterface
public interface HttpResolver<T> {
	
	Result resolve(T source);
  
}
