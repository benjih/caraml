package com.benjih.caraml;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.IOException;

import org.junit.Test;

public class RouteTest {
	
	@Test
	public void iCanCreateARouteAndRetrieveTheUrl() throws IOException {
		Route route = new Route("/a/url");
		assertThat(route.getUrl(), is("/a/url"));
	}
	
}