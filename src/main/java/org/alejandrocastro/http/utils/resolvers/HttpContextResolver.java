package org.alejandrocastro.http.utils.resolvers;

import java.util.Arrays;
import java.util.List;

import org.alejandrocastro.http.utils.commons.HttpResolver;
import org.alejandrocastro.http.utils.commons.ResolverCommand;
import org.alejandrocastro.http.utils.context.HttpContext;


public class HttpContextResolver extends CompositeResolver<HttpContext> implements HttpResolver<HttpContext>{
		
	//TODO: add empty token command
	static List<ResolverCommand<HttpContext>> commands = Arrays.asList(
 		new ResolverCommand<HttpContext>(
			"(?)request", 
			c -> c.getRequest(),
			d -> new HttpRequestResolver(d)
		),
 		new ResolverCommand<HttpContext>(
			"(?)response", 
			c -> c.getResponse(),
			d -> new HttpResponseResolver(d)
		)
//		new RootCommand("(?)auth", (despriptor)=>{})
	);
	
	public HttpContextResolver(String despriptor) {
		super(despriptor);
	}
	
	List<ResolverCommand<HttpContext>> getCommands(){
		return commands;
	}
	

}
