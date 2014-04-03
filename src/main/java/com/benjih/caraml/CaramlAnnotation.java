package com.benjih.caraml;

import java.util.HashMap;
import java.util.Map;

public class CaramlAnnotation {
	
	private String annotation;

	public CaramlAnnotation(String annotation) {
		this.annotation = annotation;
	}

	public String getType() {
		if(annotation.startsWith("@CaramlController")) {
			return "CaramlController";
		}
		return "CaramlParameter";
	}

	public Map<String, String> getDescriptions() {
		Map<String, String> descriptions = new HashMap<String, String>();
		descriptions.put("controller", annotation.split("\"")[1]);
		return descriptions;
	}
	
}