package com.benjih.caraml;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DocumentBuilder {
	
	private Document document;
	
	public DocumentBuilder() {
		try {
			this.document = Jsoup.parse(getClass().getResourceAsStream("/template.html"), "UTF-8", "/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DocumentBuilder addRoutes(List<Route> routes) {
		for(Route route : routes) {
			String parameters = new String();
			if(route.getParameters().size() != 0) {
				for(String parameter : route.getParameters()) {
					parameters = parameters + " " + parameter;
				}
			}
			document.getElementsByTag("main").append("<section><div>" +
					"<span class=\"endpoint-type endpoint-" + route.getType().toLowerCase() + "\">" + route.getType() + "</span>" +
					"<span class=\"endpoint-address\">" + route.getUrl() + " " + route.getControllerClass() + "." + route.getControllerMethod() + "</span>" + 
					"<span class=\"endpoint-parameters\">" + parameters + "</span></div>" +
					"<p>Some stuff goes in here</p>" + "</section>");
		}
		return this;
	}

	public Document build() {
		return document;
	}

}