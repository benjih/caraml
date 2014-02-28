package com.benjih.caraml;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DocumentBuilder {
	
	private Document document;
	
	public DocumentBuilder() {
		try {
			this.document = Jsoup.parse(new File("src/main/resources/template.html"), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DocumentBuilder addRoutes(List<Route> routes) {
		document.body().append("<h1>Routes</h1>");
		for(Route route : routes) {
			document.body().append("<div>" + route.getUrl() + "</div>");
		}
		return this;
	}

	public Document build() {
		return document;
	}

}