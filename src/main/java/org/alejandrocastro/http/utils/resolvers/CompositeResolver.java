package org.alejandrocastro.http.utils.resolvers;

import java.util.List;

import org.alejandrocastro.http.utils.commons.HttpResolver;
import org.alejandrocastro.http.utils.commons.ResolverCommand;
import org.alejandrocastro.http.utils.result.Result;

public abstract class CompositeResolver<T> {
	
	HttpResolver<T> delegate;
	
	public CompositeResolver(String despriptor) {
		if(null == despriptor) {
			throw new IllegalArgumentException("Expecting not null descriptor");
		}
		int dotIndex = despriptor.indexOf('.');
		if(dotIndex > 0) {
			String token = despriptor.substring(0, dotIndex);
			String rest = despriptor.substring(dotIndex + 1);
			delegate = next(token, rest);
		}
		else {
			String token = despriptor;
			String rest = "";
			delegate = next(token, rest);
		}
	}

	private <R> HttpResolver<T> next(String token, String rest) {
		for (ResolverCommand<T> resolverCommand : getCommands()) {
			if(resolverCommand.matches(token)) {
				return resolverCommand.resolver(rest);
			}
		}
		throw new IllegalArgumentException("cannot parse descriptor");
	}

	public Result resolve(T source) {
		return delegate.resolve(source);
	}
	
	abstract List<ResolverCommand<T>> getCommands();
}
