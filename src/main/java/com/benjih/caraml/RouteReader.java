package com.benjih.caraml;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class RouteReader {


	private String projectRoot;

	public RouteReader(String projectRoot) {
		this.projectRoot = projectRoot;
	}

	public String readRoutes() throws IOException {
		System.out.println(new File(projectRoot + "/routes").getAbsolutePath());
		List<String> sstrings = FileUtils.readLines(new File(projectRoot + "/routes"));
		
		return sstrings.get(0);
	}

}