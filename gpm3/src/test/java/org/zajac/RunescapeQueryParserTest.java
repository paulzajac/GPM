package org.zajac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RunescapeQueryParserTest 
{

	@Before
	public void setUp() throws Exception 
	{
	}

	@After
	public void tearDown() throws Exception 
	{
	}
	
	public static String readFile(String filename) 
	{
		String fullName = "/data/"+filename;
		InputStream is = ClassLoader.getSystemResourceAsStream(fullName);
		InputStreamReader isReader = new InputStreamReader(is);
		BufferedReader reader = new BufferedReader(isReader);
		StringBuffer bodyText = new StringBuffer();
		String line = null;
		try 
		{
			try 
			{
				while ((line = reader.readLine()) != null) 
				{
					bodyText.append(line);
					bodyText.append("\n");
				}
			} 
			finally 
			{
				reader.close();
			}
		} 
		catch (IOException e) 
		{
			throw new RuntimeException(e);
		}
		return bodyText.toString();
	}

	@Test
	public void testParseBodyText() 
	{
		if (1==1)
		{
			return; // TODO - Paul fix me
		}
		String bodyText = readFile("RunescapeTestData.txt");
		RunescapeQueryParser parser = new RunescapeQueryParser();
		List<List<String>> stats = parser
				.parseBodyText("redrocksmama", bodyText);
		Assert.assertNotNull(stats);
		Assert.assertEquals(4, stats.size());
		List<String> stats1 = stats.get(0);
		List<String> stats2 = stats.get(1);
		List<String> stats3 = stats.get(2);
		List<String> stats4 = stats.get(3);
		
		Assert.assertEquals(25, stats1.size());
		Assert.assertEquals(25, stats2.size());
		Assert.assertEquals(25, stats3.size());
		Assert.assertEquals(25, stats4.size());
		
		Assert.assertEquals("Overall", stats1.get(0));
		Assert.assertEquals("Summoning", stats1.get(24));
		Assert.assertEquals("189,214", stats2.get(0));
		Assert.assertEquals("250,448", stats2.get(24));
		Assert.assertEquals("1,904", stats3.get(0));
		Assert.assertEquals("68", stats3.get(24));
		Assert.assertEquals("86,220,271", stats4.get(0));
		Assert.assertEquals("606,214", stats4.get(24));
	}
}
