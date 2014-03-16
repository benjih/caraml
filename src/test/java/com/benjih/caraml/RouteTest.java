package com.benjih.caraml;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

import org.junit.Test;

public class RouteTest {
	
	@Test
	public void iCanCreateARouteAndRetrieveTheUrl() throws InvalidRouteException {
		Route route = new Route("POST				/a/url		AController.somewhere");
		assertThat(route.getUrl(), is("/a/url"));
		Route route2 = new Route("GET	/anotherurl							AnotherController.somewhereElse");
		assertThat(route2.getUrl(), is("/anotherurl"));
	}
	
	@Test
	public void iCanCreateARouteAndRetrieveTheType() throws InvalidRouteException {
		Route route = new Route("POST				/a/url		AController.somewhere");
		assertThat(route.getType(), is("POST"));
		Route route2 = new Route("GET	/anotherurl							AnotherController.somewhereElse");
		assertThat(route2.getType(), is("GET"));
	}
	
	@Test
	public void iCanCreateARouteAndRetrieveTheClassAndMethod() throws InvalidRouteException {
		Route route = new Route("POST				/a/url		AController.somewhere");
		assertThat(route.getControllerName(), is("AController.somewhere"));
		Route route2 = new Route("GET	/anotherurl							AnotherController.somewhereElse");
		assertThat(route2.getControllerName(), is("AnotherController.somewhereElse"));
	}
	
	@Test
	public void iCannotCreateAnIncorrectRoute() {
		try {
			Route invalidRoute = new Route("GET	/hello");
			fail();
		} catch(InvalidRouteException e) {
			assertThat(e.getMessage(), is("The routes file contains an invalid route that cannot be read."));
		}
		
	}
	
}