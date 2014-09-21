package org.zajac;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerToMonitor 
{
	SiteType site;
	String playerName;
	String playerURL;
	
	Map<SiteType, List<PlayerScore>> scores;

	public PlayerToMonitor (String siteTypeString, String playerName, String playerURL)
	{
		setSite(SiteType.valueOf(siteTypeString));
		setPlayerName(playerName);
		setPlayerURL(playerURL);
	}
	
	public static List<PlayerToMonitor> readFile(String filename) throws IOException 
	{
		List<PlayerToMonitor> players = new ArrayList<PlayerToMonitor>();
		FileReader reader = new FileReader(filename);
		BufferedReader buffer = new BufferedReader(reader);
		List<String> contents = new ArrayList<String>();
		try
		{
			String line = null;
			while ((line=buffer.readLine()) != null)
			{
				contents.add(line);
			}
		}
		finally 
		{
			buffer.close();
		}
		
		// Each line should be formatted playerName|collectionURL
		int lineNumber = 1;
		for (String line : contents)
		{
			if (line.startsWith("#"))
			{
				// Ignore commented out lines.
				continue;
			}
			String[] subStrings = line.split("\\|");
			if (subStrings.length != 3)
			{
				System.out.println ("Error reading line #"+lineNumber+" in file "+filename+" content = '"+line+"'");
			}
			else
			{
				PlayerToMonitor newPlayer = new PlayerToMonitor(subStrings[0], subStrings[1], subStrings[2]);
				players.add(newPlayer);
			}
		}

		return players;
	}

	public void collectData(SiteType site, Calendar collectionTime, int numberOfQueries) 
	{
		if (scores == null)
		{
			scores = new HashMap<SiteType, List<PlayerScore>>();
		}
		if (scores.get(site) == null)
		{
			scores.put(site, new ArrayList<PlayerScore>());
		}
		List<List<String>> stats = site.getQueryParser().queryAndParseUrl(playerURL, playerName);
		PlayerScore score = site.createPlayerScore(playerName, stats, numberOfQueries, collectionTime);
		scores.get(site).add(score);
	}

	public String getPlayerName() 
	{
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerURL() {
		return playerURL;
	}

	public void setPlayerURL(String playerURL) {
		this.playerURL = playerURL;
	}

	public List<PlayerScore> getScores(SiteType site) 
	{
		return scores.get(site);
	}

	public void setScores(SiteType site, List<PlayerScore> scores) {
		this.scores.put(site, scores);
	}

	public void clearStats() 
	{
		scores.clear();
	}

	public SiteType getSite() {
		return site;
	}

	public void setSite(SiteType site) {
		this.site = site;
	}

	public static List<SiteType> getSiteTypesMonitored(
			List<PlayerToMonitor> players) 
	{
		Map<SiteType, SiteType> set = new HashMap<SiteType, SiteType>();
		for (PlayerToMonitor player : players)
		{
			set.put(player.getSite(), player.getSite());
		}
		List<SiteType> returnValue = new ArrayList<SiteType>();
		returnValue.addAll(set.keySet());
		return returnValue;
	}
}
