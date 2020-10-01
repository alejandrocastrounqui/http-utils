package org.alejandrocastro.http.utils.commons;

@FunctionalInterface
public interface HttpResolver<T> {
	
    Object resolve(T source);
  
}
