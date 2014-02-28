package com.benjih.caraml;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.Test;

public class RouteReaderTest {
	
	@Test
	public void iCanReadARoutesFile() throws IOException {
		List<Route> something = RouteReader.readRoutes("src/test/resources/routes");
		assertThat(something.get(0).getUrl(), is("test"));
	}
	
	@Test
	public void readingTheRoutesFileIgnoresComments() throws IOException {
		List<Route> something = RouteReader.readRoutes("src/test/resources/routes");
		
		assertThat(something.size(), is(1));
		assertFalse(something.get(0).getUrl().equals("# This is a comment"));
	}
	
	@Test
	public void thisIsInTheWrongPlace() throws IOException {
		Document document = Jsoup.parse(new File("src/main/resources/template.html"), "UTF-8");
		document.select("body").append("<div>hello</div>");
		System.out.println(document.toString());
	}
	
}