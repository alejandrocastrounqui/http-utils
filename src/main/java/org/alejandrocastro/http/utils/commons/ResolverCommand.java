package org.alejandrocastro.http.utils.commons;

import java.util.function.Function;
import java.util.regex.Pattern;

public class ResolverCommand<T>{
	
	Pattern pattern;
	
	ResolverSupplier<T> resolverSupplier;
	
	public <R> ResolverCommand(String regex, Function<T, R> contextSupplier, ResolverSupplier<R> resolverSupplier) {
		super();
		this.pattern = Pattern.compile(regex);
		this.resolverSupplier = (descriptor)->{
			HttpResolver<R> delegate = resolverSupplier.apply(descriptor);
			return (source) ->{
				R context = contextSupplier.apply(source);
				return delegate.resolve(context);
			};
		};
	}

	public boolean matches(String token) {
		return pattern.matcher(token).matches();
	}
	
	public HttpResolver<T> resolver(String descriptor) {
		return resolverSupplier.apply(descriptor);
	}
}
