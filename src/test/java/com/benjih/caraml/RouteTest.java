package com.benjih.caraml;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

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
		assertThat(route.getControllerClass(), is("AController"));
		assertThat(route.getControllerMethod(), is("somewhere"));
		Route route2 = new Route("GET	/anotherurl							AnotherController.somewhereElse");
		assertThat(route2.getControllerClass(), is("AnotherController"));
		assertThat(route2.getControllerMethod(), is("somewhereElse"));
	}
	
	@Test
	public void iCanGetTheAcceptedParametersForARoute() throws InvalidRouteException {
		Route route = new Route("POST				/a/url		AController.somewhere");
		
		route.addParameter("one");
		route.addParameter("two");
		route.addParameter("three");
		
		assertThat(route.getParameters().size(), is(3));
		assertThat(route.getParameters().get(0), is("one"));
		assertThat(route.getParameters().get(1), is("two"));
		assertThat(route.getParameters().get(2), is("three"));
		
	}
	
	@Test
	public void iCanParseRoutesThatUseSpacesInsteadOfTabs() {
		try {
			Route route = new Route("GET     /                                       Application.index");
			assertThat(route.getType(), is("GET"));
			assertThat(route.getUrl(), is("/"));
			assertThat(route.getControllerClass(), is("Application"));
			assertThat(route.getControllerMethod(), is("index"));
		} catch (InvalidRouteException e) {
			fail();
		}
		
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