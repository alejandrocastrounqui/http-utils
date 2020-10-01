package org.alejandrocastro.http.utils.commons;

@FunctionalInterface
public interface ResolverSupplier<R> {
	
	HttpResolver<R> apply(String descriptor);
	
}
	