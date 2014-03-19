package com.benjih.caraml;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.junit.Test;

public class DocumentBuilderTest {

	@Test
	public void iCanBuildABasicPage() throws IOException {
		DocumentBuilder builder = new DocumentBuilder();
		Document document = builder.build();
		
		assertTrue(document.toString().contains("<html>"));
		assertTrue(document.toString().contains("<head>"));
		assertTrue(document.toString().contains("</head>"));
		assertTrue(document.toString().contains("<body>"));
		assertTrue(document.toString().contains("</body>"));
		assertTrue(document.toString().contains("<html>"));
	}
	
	@Test
	public void iCanReadARoutesFile() throws InvalidRouteException {
		DocumentBuilder builder = new DocumentBuilder();
		
		List<Route> routes = new ArrayList<Route>();
		routes.add(new Route("GET	/test/1	something.somewhere"));
		routes.add(new Route("GET	/test/2	something.somewhere"));
		
		Document routeDocument = builder.addRoutes(routes).build();
		
		assertThat(routeDocument.getElementsByTag("section").get(0).getElementsByClass("endpoint-type").text(), is("GET"));
		assertThat(routeDocument.getElementsByTag("section").get(0).getElementsByClass("endpoint-address").text(), is("/test/1 something.somewhere"));
		assertThat(routeDocument.getElementsByTag("section").get(0).getElementsByClass("endpoint-parameters").text(), is(""));
		assertThat(routeDocument.getElementsByTag("section").get(1).getElementsByClass("endpoint-type").text(), is("GET"));
		assertThat(routeDocument.getElementsByTag("section").get(1).getElementsByClass("endpoint-address").text(), is("/test/2 something.somewhere"));
		assertThat(routeDocument.getElementsByTag("section").get(1).getElementsByClass("endpoint-parameters").text(), is(""));
	}
	
	@Test
	public void ifThereAreParametersOnARouteTheyWillBeOutputted() throws InvalidRouteException {
		DocumentBuilder builder = new DocumentBuilder();
		
		List<Route> routes = new ArrayList<Route>();
		Route route = new Route("GET	/test/1	something.somewhere");
		route.addParameter("String parameter");
		routes.add(route);
		
		Document routeDocument = builder.addRoutes(routes).build();
		
		assertThat(routeDocument.getElementsByTag("section").get(0).getElementsByClass("endpoint-type").text(), is("GET"));
		assertThat(routeDocument.getElementsByTag("section").get(0).getElementsByClass("endpoint-address").text(), is("/test/1 something.somewhere"));
		assertThat(routeDocument.getElementsByTag("section").get(0).getElementsByClass("endpoint-parameters").text(), is("String parameter"));
	}
	
	@Test
	public void ifThereAreNoParamtersOnTheRouteThereIsNoParameterOutput() throws InvalidRouteException {
		DocumentBuilder builder = new DocumentBuilder();
		
		List<Route> routes = new ArrayList<Route>();
		routes.add(new Route("GET	/test/1	something.somewhere"));
		
		Document routeDocument = builder.addRoutes(routes).build();
		
		assertThat(routeDocument.getElementsByTag("section").get(0).getElementsByClass("endpoint-type").text(), is("GET"));
		assertThat(routeDocument.getElementsByTag("section").get(0).getElementsByClass("endpoint-address").text(), is("/test/1 something.somewhere"));
		assertThat(routeDocument.getElementsByTag("section").get(0).getElementsByClass("endpoint-parameters").text(), is(""));
	}
	
}