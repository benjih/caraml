package com.benjih.caraml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class RouteReader {

	public static List<Route> readRoutes(String uri) throws IOException {
		List<String> lines = FileUtils.readLines(new File(uri));
		List<String> tidiedStrings = removeCommentsFromRoutes(lines);
		
		List<Route> routes = new ArrayList<Route>();
		
		for(String string : tidiedStrings) {
			routes.add(new Route(string));
		}
		
		return routes;
	}

	private static List<String> removeCommentsFromRoutes(List<String> lines) {
		List<String> linesToRemove = new ArrayList<String>();
		
		for(String singleLine : lines) {
			if(singleLine.startsWith("#")) {
				linesToRemove.add(singleLine);
			}
		}
		
		lines.removeAll(linesToRemove);
		return lines;
	}

}