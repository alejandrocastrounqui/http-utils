package org.alejandrocastro.http.utils.resolvers;


import java.util.Arrays;
import java.util.List;

import org.alejandrocastro.http.utils.commons.HttpResolver;
import org.alejandrocastro.http.utils.commons.ResolverCommand;
import org.alejandrocastro.http.utils.context.HttpMessage;

class HttpResponseResolver extends CompositeResolver<HttpMessage> implements HttpResolver<HttpMessage>{

	//TODO: add empty token command
	static List<ResolverCommand<HttpMessage>> commands = Arrays.asList(
 		new ResolverCommand<HttpMessage>(
			"(?)header", 
			c -> c.getHeaders(),
			d -> new HttpHeadersResolver(d)
		),
 		new ResolverCommand<HttpMessage>(
			"(?)body", 
			c -> c,
			d -> new HttpBodyContentResolver(d)
		)
	);
	
	HttpResponseResolver(String descriptor) {
		super(descriptor);
	}

	@Override
	List<ResolverCommand<HttpMessage>> getCommands() {
		return commands;
	}

}
