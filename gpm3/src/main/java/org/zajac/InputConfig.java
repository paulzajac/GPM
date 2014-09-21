package org.zajac;

import java.io.IOException;
import java.util.List;

public class InputConfig {

	// Declare private variables
	private	int monitorIntervalInMinutes;
	private int totalNumberOfQueries;
	private	int numberOfQueries;

	private List<PlayerToMonitor> players;
	
	// Declare Constructor
	public InputConfig (String[] args) throws IOException{
		
		if (args.length != 4)
		{
			usage ("Incorrect number of arguments.");
		}
		
		//Assigning values to variables
		monitorIntervalInMinutes = Integer.parseInt(args[1]);
		totalNumberOfQueries = Integer.parseInt(args[2]);
		numberOfQueries = 0;
		
		if (monitorIntervalInMinutes <1)
		{
			usage("The valid monitor interval needs to be a number greater than 0.");
		}
		
		players = PlayerToMonitor.readFile(args[0]);
		
		if (players.size() < 1)
		{
			usage("Error establishing player configuration - No Players constructed.");
		}
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

	public int getMonitorIntervalInMinutes() {
		return monitorIntervalInMinutes;
	}

	public void setMonitorIntervalInMinutes(int monitorIntervalInMinutes) {
		this.monitorIntervalInMinutes = monitorIntervalInMinutes;
	}

	public int getTotalNumberOfQueries() {
		return totalNumberOfQueries;
	}

	public void setTotalNumberOfQueries(int totalNumberOfQueries) {
		this.totalNumberOfQueries = totalNumberOfQueries;
	}

	public int getNumberOfQueries() {
		return numberOfQueries;
	}

	public void setNumberOfQueries(int numberOfQueries) {
		this.numberOfQueries = numberOfQueries;
	}

	public List<PlayerToMonitor> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerToMonitor> players) {
		this.players = players;
	}
	
}

