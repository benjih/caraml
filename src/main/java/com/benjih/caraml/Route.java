package com.benjih.caraml;

public class Route {

	private String type;
	private String url;
	private String controllerClass;
	private String controllerMethod;
	
	public Route(String route) throws InvalidRouteException {
		String[] split = route.split("\\t+");
		
		if(split.length != 3) {
			throw new InvalidRouteException("The routes file contains an invalid route that cannot be read.");
		}
		
		this.type = split[0];
		this.url = split[1];
		this.controllerClass = split[2].split("\\.")[0];
		this.controllerMethod = split[2].split("\\.")[1];
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
	
	public String getControllerMethod() {
		return controllerMethod;
	}
	
}