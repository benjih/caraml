package com.benjih.caraml;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class RouteReaderTest {
	
	@Test
	public void iCanReadARoutesFile() throws IOException {
		RouteReader routeReader = new RouteReader("src/test/resources/");
		List<String> something = routeReader.readRoutes();
		assertThat(something.get(0), is("test"));
	}
	
	@Test
	public void readingTheRoutesFileIgnoresComments() throws IOException {
		RouteReader routeReader = new RouteReader("src/test/resources/");
		List<String> something = routeReader.readRoutes();
		
		assertThat(something.size(), is(1));
		assertFalse(something.contains("# This is a comment"));
	}
	
}