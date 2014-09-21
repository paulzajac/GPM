package org.zajac;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;


public enum SiteType 
{
	RUNESCAPE(new RunescapePlayerScore(), new RunescapeQueryParser(), new RunescapeUsageWriter()),
	STEAM (new SteamPlayerScore(), new SteamFriendsQueryParser(), new SteamFriendsUsageWriter()),
	PS3N (null, null, null),
	;

	private QueryParser parser;
	private PlayerScore playerScore;
	private UsageWriter writer;
	
	private SiteType(PlayerScore playerScore, QueryParser parser, UsageWriter writer)
	{
		this.parser = parser;
		this.playerScore = playerScore;
		this.writer = writer;
	}
	
	public QueryParser getQueryParser()
	{
		return parser;
	}
	
	public UsageWriter createUsageWriter(String filename) throws IOException
	{
		UsageWriter newWriter = null;
		try
		{
			newWriter = writer.getClass().newInstance();
		}
		catch (InstantiationException e) 
		{
			System.err.println("Coding Error ");
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) 
		{
			System.err.println("Coding Error ");
			e.printStackTrace();
		}
		if (newWriter != null)
		{
			newWriter.initialize(this, filename);
		}
		return newWriter;
	}
	
	public PlayerScore createPlayerScore(String playerName,
		List<List<String>> stats, int numberOfQueries, Calendar collectionTime) 
	{
		PlayerScore newScore = null;
		try 
		{
			newScore = playerScore.getClass().newInstance();
		}
		catch (InstantiationException e) 
		{
			System.err.println("Coding Error ");
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) 
		{
			System.err.println("Coding Error ");
			e.printStackTrace();
		}
		if (newScore != null)
		{
			newScore.initialize(playerName, stats, numberOfQueries, collectionTime);
		}
		return newScore;
	}
}

