package com.benjih.caraml;

import java.util.ArrayList;
import java.util.List;

public class Route {

	private String type;
	private String url;
	private String controllerClass;
	private String controllerMethod;
	private List<String> parameters;
	
	public Route(String route) throws InvalidRouteException {
		String[] split = route.split("\\s+");
		if(split.length != 3) {
			throw new InvalidRouteException("The routes file contains an invalid route that cannot be read.");
		}
		
		this.type = split[0];
		this.url = split[1];
		this.controllerClass = split[2].split("\\.")[0];
		this.controllerMethod = split[2].split("\\.")[1];
		
		this.parameters = new ArrayList<String>();
	}
	
	public String getType() {
		return type;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getControllerClass() {
		return controllerClass;
	}
	
	public String getControllerClassUri() {
		return "/app/controllers/" + controllerClass + ".java";
	}
	
	public String getControllerMethod() {
		return controllerMethod;
	}

	public void addParameter(String parameter) {
		parameters.add(parameter);
	}
	
	public void addAllParameters(List<String> parameters) {
		this.parameters.addAll(parameters);
	}

	public List<String> getParameters() {
		return parameters;
	}

}