package com.benjih.caraml;

public class Route {

	private String type;
	private String url;
	private String classAndMethod;
	
	public Route(String route) throws InvalidRouteException {
		String[] split = route.split("\\t+");
		
		if(split.length != 3) {
			throw new InvalidRouteException("The routes file contains an invalid route that cannot be read.");
		}
		
		this.type = split[0];
		this.url = split[1];
		this.classAndMethod = split[2];
	}
	
	public String getType() {
		return type;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getControllerName() {
		return classAndMethod;
	}
	
}