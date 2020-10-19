package org.alejandrocastro.http.utils.uri.pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.alejandrocastro.http.utils.uri.patterns.Match;
import org.alejandrocastro.http.utils.uri.patterns.UriPattern;
import org.junit.jupiter.api.Test;

class UriPatternTests {
	
	@Test
	void emptyPatternResolvesEmptyPathWithWeigth0() {
		UriPattern empty = new UriPattern("");
		Match match = empty.matches("");
		assertThat(match.weigth(), equalTo(0));
	}
	
	@Test
	void emptyPatternResolvesSlashPathWithWeigth0() {
		UriPattern empty = new UriPattern("");
		Match match = empty.matches("/");
		assertThat(match.weigth(), equalTo(0));
	}
	
	@Test
	void emptyPatternRejectsNonEmptyPathWithWeigth0() {
		UriPattern empty = new UriPattern("");
		Match match = empty.matches("/v1");
		assertThat(match.weigth(), equalTo(0));
	}
	
	@Test
	void emptyPatternRejectsNonEmptyPath() {
		UriPattern empty = new UriPattern("");
		Match match = empty.matches("/v1");
		assertThat(match.matches(), equalTo(false));
	}
	
	@Test
	void constantsPatternWithSlashRejectsEmptyPath() {
		UriPattern constants = new UriPattern("/v1/pom/");
		Match match = constants.matches("");
		assertThat(match.matches(), equalTo(false));
	}

	@Test
	void constantsPatternWithSlashResolvesSamePathWithoutSlashes() {
		UriPattern constants = new UriPattern("/v1/pom/");
		Match match = constants.matches("v1/pom");
		assertThat(match.matches(), equalTo(true));
	}
	
	@Test
	void actionParamPatternWithSlashResolvesPathWithValue() {
		UriPattern actionParam = new UriPattern("/v1/pom/{action}");
		Match match = actionParam.matches("v1/pom/register");
		assertThat(match.matches(), equalTo(true));
	}
	
	@Test
	void actionParamPatternWithSlashRejectsPathWithoutValue() {
		UriPattern actionParam = new UriPattern("/v1/pom/{action}");
		Match match = actionParam.matches("v1/pom/");
		assertThat(match.matches(), equalTo(false));
	}
	
	@Test
	void idAndActionParamPatternWithSlashRejectsPathWithoutValue() {
		UriPattern idAndActionParam = new UriPattern("/v1/pom/{id}/{action}");
		Match match = idAndActionParam.matches("v1/pom/123/register");
		assertThat(match.matches(), equalTo(true));
	}
	
	@Test
	void idAndActionParamPatternWithSlashReturnPathParamId() {
		UriPattern idAndActionParam = new UriPattern("/v1/pom/{id}/{action}");
		Match match = idAndActionParam.matches("v1/pom/123/register");
		assertThat(match.data().get("id"), equalTo("123"));
	}

}
