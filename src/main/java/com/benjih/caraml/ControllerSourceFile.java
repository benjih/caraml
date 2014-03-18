package com.benjih.caraml;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.body.TypeDeclaration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerSourceFile {
	
	private CompilationUnit parsedFile;
	private Map<String, MethodDeclaration> methods;

	public ControllerSourceFile(String uri) throws IOException, ParseException {
		FileInputStream in = new FileInputStream(uri);

		try {
			parsedFile = JavaParser.parse(in);
		} finally {
			in.close();
		}
		
		this.methods = findMethods(parsedFile);

	}

	public List<String> getMethods() {
		List<String> methodNames = new ArrayList<String>();
		
		methodNames.addAll(methods.keySet());
		
		return methodNames;
	}

	public List<String> getParametersFor(String methodName) {
		List<String> methodParameters = new ArrayList<String>();
		
		MethodDeclaration method = methods.get(methodName);
		
		if(method.getParameters() != null) {
			for(Parameter parameter : method.getParameters()) {
				methodParameters.add(parameter.toString());
			}
		}
		
		return methodParameters;
	}
	
	private Map<String, MethodDeclaration> findMethods(CompilationUnit cu) {
		Map<String, MethodDeclaration> methods = new HashMap<String, MethodDeclaration>();
		
		List<TypeDeclaration> types = cu.getTypes();
		for (TypeDeclaration type : types) {
			List<BodyDeclaration> members = type.getMembers();
			for (BodyDeclaration member : members) {
				if (member instanceof MethodDeclaration) {
					MethodDeclaration method = (MethodDeclaration) member;
					methods.put(method.getName(), method);
				}
			}
		}
		
		return methods;
	}

}