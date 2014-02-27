package com.benjih.caraml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class RouteReader {


	private String projectRoot;

	public RouteReader(String projectRoot) {
		this.projectRoot = projectRoot;
	}

	public List<String> readRoutes() throws IOException {
		List<String> lines = FileUtils.readLines(new File(projectRoot + "/routes"));
		
		return removeCommentsFromRoutes(lines);
	}

	private List<String> removeCommentsFromRoutes(List<String> lines) {
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