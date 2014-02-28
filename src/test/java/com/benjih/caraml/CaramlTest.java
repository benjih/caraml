package com.benjih.caraml;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CaramlTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void before() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void after() {
	    System.setOut(null);
	    System.setErr(null);
	}
	
	@Test
	public void iCanRunCaraml() throws IOException {
		String[] args = {"src/test/resources/project-1"};
		Caraml.main(args);
		
		System.out.println(outContent.toString());
		
		assertTrue(outContent.toString().contains("Documenting project-1"));
		assertTrue(outContent.toString().contains("3 routes found"));
	}
	
}