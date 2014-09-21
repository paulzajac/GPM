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

public class SteamFriendsQueryParserTest 
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
		InputStream is = ClassLoader.getSystemResourceAsStream(filename);
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
	public void testParseBodyTextXian() 
	{
		if (1==1)
		{
			return; // TODO Fixme
		}
		String bodyText = readFile("ChristianSteamData.txt");
		SteamFriendsQueryParser parser = new SteamFriendsQueryParser();
		List<List<String>> stats = parser
				.parseBodyText("cnquistador", bodyText);
		Assert.assertNotNull(stats);
		Assert.assertEquals(4, stats.size());
		List<String> stats1 = stats.get(0);
		List<String> stats2 = stats.get(1);
		List<String> stats3 = stats.get(2);
		List<String> stats4 = stats.get(3);
		
		Assert.assertEquals(6, stats1.size());
		Assert.assertEquals(6, stats2.size());
		Assert.assertEquals(0, stats3.size());
		Assert.assertEquals(0, stats4.size());
		
		Assert.assertEquals("667man", stats1.get(0));
		Assert.assertEquals("LugNutz", stats1.get(5));
		Assert.assertEquals("Online", stats2.get(0));
		Assert.assertEquals("Last Online: 21 hrs, 59 mins ago", stats2.get(5));
	}
	
	@Test
	public void testParseBodyTextSeb() 
	{
		if (1==1)
		{
			return;// TODO Fixme
		}
		String bodyText = readFile("SebastianSteamData.txt");
		SteamFriendsQueryParser parser = new SteamFriendsQueryParser();
		List<List<String>> stats = parser
				.parseBodyText("TakedaIesyu", bodyText);
		Assert.assertNotNull(stats);
		Assert.assertEquals(4, stats.size());
		List<String> stats1 = stats.get(0);
		List<String> stats2 = stats.get(1);
		List<String> stats3 = stats.get(2);
		List<String> stats4 = stats.get(3);
		
		Assert.assertEquals(1, stats1.size());
		Assert.assertEquals(1, stats2.size());
		Assert.assertEquals(0, stats3.size());
		Assert.assertEquals(0, stats4.size());
		
		Assert.assertEquals("cnquistador", stats1.get(0));
		Assert.assertEquals("Online", stats2.get(0));
	}

}
