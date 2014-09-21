package org.zajac;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
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
	
	@Test
	public void testParseBodyText() 
	{
		String bodyText = RunescapeQueryParserTest.readFile("SteamTestData.txt");
		SteamFriendsQueryParser parser = new SteamFriendsQueryParser();
		List<List<String>> stats = parser
				.parseBodyText("someuser", bodyText);
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
	public void testParseBodyTextFriends() 
	{
		String bodyText = RunescapeQueryParserTest.readFile("SteamTestData2.txt");
		SteamFriendsQueryParser parser = new SteamFriendsQueryParser();
		List<List<String>> stats = parser
				.parseBodyText("someuser", bodyText);
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
		
		Assert.assertEquals("someOtherUser", stats1.get(0));
		Assert.assertEquals("Online", stats2.get(0));
	}

}
