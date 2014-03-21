package com.benjih.caraml;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import japa.parser.ParseException;

import java.io.IOException;

import org.junit.Test;

public class ControllerSourceFileTest {
	
	@Test
	public void iCanCreateAControllerSourceFileAndGetAllMethods() throws Exception {
		ControllerSourceFile controller = new ControllerSourceFile("src/test/resources/Application.java");
		assertThat(controller.getMethods().size(), is(2));
		assertThat(controller.getMethods().get(0), is("showArtists"));
		assertThat(controller.getMethods().get(1), is("showAlbums"));
	}
	
	@Test
	public void iCanGetTheParametersForAMethod() throws Exception {
		ControllerSourceFile controller = new ControllerSourceFile("src/test/resources/Application.java");
		assertThat(controller.getParametersFor("showArtists").size(), is(0));
		
		assertThat(controller.getParametersFor("showAlbums").size(), is(1));
		assertThat(controller.getParametersFor("showAlbums").get(0), is("String artist"));
	}
	
	@Test
	public void iDontGetANullPointerExceptionWhenICallForParametersOfAnInvalidMethod() throws IOException, ParseException {
		ControllerSourceFile controller = new ControllerSourceFile("src/test/resources/Application.java");
		assertThat(controller.getParametersFor("missingMethod").size(), is(0));
	}
	
	@Test
	public void iCanGetTheAnnotationsForAMethod() throws Exception {
		ControllerSourceFile controller = new ControllerSourceFile("src/test/resources/Application.java");
		assertThat(controller.getAnnotationsFor("showAlbums").size(), is(0));
		
		assertThat(controller.getAnnotationsFor("showArtists").size(), is(1));
		assertThat(controller.getAnnotationsFor("showArtists").get(0), is("@CaramlController(\"This is about showArtists\")"));
	}
	
	@Test
	public void iDontGetANullPointerExceptionWhenICallForAnnotationsForAnInvalidMethod() throws IOException, ParseException {
		ControllerSourceFile controller = new ControllerSourceFile("src/test/resources/Application.java");
		assertThat(controller.getAnnotationsFor("missingMethod").size(), is(0));
	}
	
}