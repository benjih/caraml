package com.benjih.caraml;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Caraml {

	public static void main(String args[]) throws IOException {
		System.out.println("Hello");
		String uri = args[0];
		
		File projectRoot = new File(uri);
		System.out.println("Documenting " + projectRoot.getName());
		
		List<Route> routes = RouteReader.readRoutes(projectRoot.getAbsolutePath() + "/config/routes");
		
		System.out.println(routes.size() + " routes found");
	}
	
}