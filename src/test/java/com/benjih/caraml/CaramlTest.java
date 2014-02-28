package com.benjih.caraml;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CaramlTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void before() throws IOException {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	    
	    FileUtils.deleteDirectory(new File("src/test/resources/project-1/docs"));
	}

	@After
	public void after() throws IOException {
	    System.setOut(null);
	    System.setErr(null);
	    
	    FileUtils.deleteDirectory(new File("src/test/resources/project-1/docs"));
	}
	
	@Test
	public void iCanRunCaraml() {
		String[] args = {"src/test/resources/project-1"};
		try {
			Caraml.main(args);
		} catch (IOException e1) {
		}
		
		System.out.println(outContent.toString());
		
		assertTrue(outContent.toString().contains("Documenting project-1"));
		assertTrue(outContent.toString().contains("3 routes found"));
		
		String writtenFile = null;
		
		try {
			writtenFile = FileUtils.readFileToString(new File("src/test/resources/project-1/docs/routes.html"));
		} catch (IOException e) {
			fail();
		}
		
		assertTrue(writtenFile.contains("<h1>Routes</h1>"));
		assertTrue(writtenFile.contains("GET /home MainController.home"));
		assertTrue(writtenFile.contains("POST /submit MainController.post"));
		assertTrue(writtenFile.contains("* /about MainController.about"));
	}
	
}