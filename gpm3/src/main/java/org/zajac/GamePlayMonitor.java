package org.zajac;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * This class is used to collect Game Player scores at a set interval.
 * 
 * TODO - Follow on work is to analyze the HF data and produce correllation plots between players
 * TODO - Follow on work is to analyze the HF data and produce Activity plots (When playing and when not)
 * TODO - Follow on work - Analyze the HF data to determine the max XP per hour to guage usage.
 * @author dad
 * 
 */
public class GamePlayMonitor 
{
	public static void main (String[] args) throws Exception
	{
		if (args.length != 4)
		{
			usage ("Incorrect number of arguments.");
		}
		
		int monitorIntervalInMinutes = Integer.parseInt(args[1]);
		int totalNumberOfQueries = Integer.parseInt(args[2]);
		int numberOfQueries = 0;
		
		if (monitorIntervalInMinutes <1)
		{
			usage("The valid monitor interval needs to be a number greater than 0.");
		}
		
		List<PlayerToMonitor> players = PlayerToMonitor.readFile(args[0]);
		
		if (players.size() < 1)
		{
			usage("Error establishing player configuration - No Players constructed.");
		}
		System.out.println("GamePlayMonitor started with the following args: "+args[0]+" "+args[1]+" "+args[2]+" "+args[3]);
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
		Calendar collectionTime = Calendar.getInstance();
		String dateString = dateFormatter.format(collectionTime.getTime());
		List<UsageWriter> highFreqWriters = new ArrayList<UsageWriter>();
		List<SiteType> siteTypes = PlayerToMonitor.getSiteTypesMonitored(players);
		for (SiteType type : siteTypes)
		{			
			highFreqWriters.add(type.createUsageWriter(args[3]+"_"+type.name()+"_"+dateString+"_HF.txt"));
		}
		
		boolean done = false;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm");
		
		while (!done)
		{
			collectionTime = Calendar.getInstance();	
			System.out.println("Collecting stats at "+formatter.format(collectionTime.getTime()));
			for (PlayerToMonitor player : players)
			{
				player.collectData(player.getSite(), collectionTime, numberOfQueries);
			}
			++numberOfQueries;
			System.out.println("Collected "+numberOfQueries+" Stats");
			for (UsageWriter writer : highFreqWriters)
			{
				writer.writeOutput(players);
			}
			System.out.println("Stats written to output file.");
			if (totalNumberOfQueries > 0)
			{
				if (numberOfQueries >= totalNumberOfQueries)
				{
					done = true;
					break;
				}
			}
			
			try
			{
				if (!done)
				{
					System.out.println ("Sleeping for "+monitorIntervalInMinutes+" minutes...");
					Thread.sleep(monitorIntervalInMinutes * 60 * 1000);
				}
				Calendar timeNow = Calendar.getInstance();
				if (collectionTime.get(Calendar.DAY_OF_YEAR) != timeNow.get(Calendar.DAY_OF_YEAR))
				{
					for (UsageWriter highFreqWriter : highFreqWriters)
					{
						highFreqWriter.closeWriter();
						dateString = dateFormatter.format(timeNow.getTime());
						highFreqWriter.site.createUsageWriter(highFreqWriter.site.name()+"_"+args[3]+"_"+dateString+"_HF.txt");
					}
				}
				for (PlayerToMonitor player : players)
				{
					player.clearStats();
				}
			}
			catch (InterruptedException e)
			{}
		}
		
		for (UsageWriter highFreqWriter : highFreqWriters)
		{
			highFreqWriter.closeWriter();
		}
		System.out.println("Collection ended normally.");
	}
	
	private static void usage(String message)
	{
		System.out.println ("Invalid arguments: "+message);
		System.out.println("usage GamePlayMonitor <PlayersToMonitorFile> <Monitor Interval (minutes)> <Monitor duration (count)> <Output File>");
		System.out.println("");
		System.out.println("Format of PlayersToMonitor file is:");
		System.out.println("     SiteType|playerName|url");
		System.exit(1);
	}
}
