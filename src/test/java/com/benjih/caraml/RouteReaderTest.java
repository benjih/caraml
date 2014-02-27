package com.benjih.caraml;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.IOException;

import org.junit.Test;

public class RouteReaderTest {
	
	@Test
	public void iCanReadARoutesFile() throws IOException {
		RouteReader routeReader = new RouteReader("src/test/resources/");
		String something = routeReader.readRoutes();
		assertThat(something, is("test"));
	}
	
}