package com.benjih.caraml;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class ControllerSourceFile {
	
	private CompilationUnit parsedFile;
	private Set<MethodDeclaration> methods;

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
		
		for(MethodDeclaration method : methods) {
			methodNames.add(method.getName());
		}
		
		return methodNames;
	}

	public List<String> getParametersFor(String string) {
		List<String> methodParameters = new ArrayList<String>();
		for(MethodDeclaration method : methods) {
			if(method.getName().equals(string)) {
				if(method.getParameters() != null) {
					for(Parameter parameter : method.getParameters()) {
						methodParameters.add(parameter.toString());
					}
				}
			}
		}
		
		return methodParameters;
	}
	
	private Set<MethodDeclaration> findMethods(CompilationUnit cu) {
		Set<MethodDeclaration> methods = new HashSet<MethodDeclaration>();
		
		List<TypeDeclaration> types = cu.getTypes();
		for (TypeDeclaration type : types) {
			List<BodyDeclaration> members = type.getMembers();
			for (BodyDeclaration member : members) {
				if (member instanceof MethodDeclaration) {
					methods.add((MethodDeclaration) member);
				}
			}
		}
		
		return methods;
	}

}