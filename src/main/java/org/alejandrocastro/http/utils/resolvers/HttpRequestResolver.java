package org.alejandrocastro.http.utils.resolvers;


import java.util.Arrays;
import java.util.List;

import org.alejandrocastro.http.utils.commons.HttpResolver;
import org.alejandrocastro.http.utils.commons.ResolverCommand;
import org.alejandrocastro.http.utils.context.HttpRequest;

class HttpRequestResolver extends CompositeResolver<HttpRequest> implements HttpResolver<HttpRequest>{
	
	//TODO: add empty token command
	static List<ResolverCommand<HttpRequest>> commands = Arrays.asList(
 		new ResolverCommand<HttpRequest>(
			"(?)header", 
			c -> c.getHeader(),
			d -> new HttpHeadersResolver(d)
		),
 		new ResolverCommand<HttpRequest>(
			"(?)body", 
			c -> c,
			d -> new HttpBodyContentResolver(d)
		)
	);

	HttpRequestResolver(String descriptor) {
		super(descriptor);
	}

	@Override
	List<ResolverCommand<HttpRequest>> getCommands() {
		return commands;
	}

}
