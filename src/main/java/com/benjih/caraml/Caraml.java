package com.benjih.caraml;

import japa.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.Document;

public class Caraml {

	public static void main(String args[]) throws IOException, ParseException {
		System.out.println("Hello");
		String uri = args[0];
		
		File projectRoot = new File(uri);
		System.out.println("Documenting " + projectRoot.getName());
		
		List<Route> routes = RouteReader.readRoutes(projectRoot.getAbsolutePath() + "/conf/routes");
		System.out.println(routes.size() + " routes found");
		
		for(Route route : routes) {
			ControllerSourceFile csf = new ControllerSourceFile(uri + route.getControllerClassUri());
			route.addAllParameters(csf.getParametersFor(route.getControllerMethod()));
		}
		
		Document document = new DocumentBuilder().addRoutes(routes).build();
		FileUtils.writeStringToFile(new File(uri + "/docs/routes.html"), document.toString());
	}
	
}