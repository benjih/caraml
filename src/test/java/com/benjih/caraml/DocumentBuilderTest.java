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
	public void iCanReadARoutesFile() {
		DocumentBuilder builder = new DocumentBuilder();
		
		List<Route> routes = new ArrayList<Route>();
		routes.add(new Route("test/1"));
		routes.add(new Route("test/2"));
		
		Document routeDocument = builder.addRoutes(routes).build();
		
		assertThat(routeDocument.getElementsByTag("h1").text(), is("Routes"));
		assertThat(routeDocument.getElementsByTag("div").size(), is(2));
		assertThat(routeDocument.getElementsByTag("div").get(0).text(), is("test/1"));
		assertThat(routeDocument.getElementsByTag("div").get(1).text(), is("test/2"));
	}
	
}