package com.benjih.caraml;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CaramlAnnotationTest {
	
	@Test
	public void iCanHandAStringAnnotationAndCreateACaramlAnnotation() {
		CaramlAnnotation annotation = new CaramlAnnotation("@CaramlController(\"This is an annotation\")");
		assertThat(annotation.getType(), is("CaramlController"));
		
		annotation = new CaramlAnnotation("@CaramlParameter(parameter = \"response\", description = \"This is about a parameter\")");
		assertThat(annotation.getType(), is("CaramlParameter"));
	}
	
	@Test
	public void iCanGetDescriptionsFromAnnotations() {
		CaramlAnnotation annotation = new CaramlAnnotation("@CaramlController(\"This is an annotation\")");
		assertThat(annotation.getDescriptions().size(), is(1));
		assertThat(annotation.getDescriptions().get("controller"), is("This is an annotation"));
	}
	
}