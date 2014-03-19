package com.benjih.caraml;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import japa.parser.ParseException;

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
//	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	    
	    FileUtils.deleteDirectory(new File("src/test/resources/demo-project/docs"));
	}

	@After
	public void after() throws IOException {
	    System.setOut(null);
	    System.setErr(null);
	    
	    FileUtils.deleteDirectory(new File("src/test/resources/demo-project/docs"));
	}
	
	@Test
	public void iCanRunCaraml() {
		String[] args = {"src/test/resources/demo-project"};
		try {
			Caraml.main(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		assertTrue(outContent.toString().contains("Documenting demo-project"));
//		assertTrue(outContent.toString().contains("3 routes found"));
		
		String writtenFile = null;
		
		try {
			writtenFile = FileUtils.readFileToString(new File("src/test/resources/demo-project/docs/routes.html"));
		} catch (IOException e) {
			fail();
		}
	}
	
}