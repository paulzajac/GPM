package org.zajac;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SteamFriendsUsageWriter extends UsageWriter 
{	
	public void writeOutput(List<PlayerToMonitor> players)  
	{	
		int numberOfRowsToWrite = 0;
		try 
		{
			// Collect the scores and write the header
			List<SteamPlayerScore> scores = new ArrayList<SteamPlayerScore>();
			for (PlayerToMonitor player : players) 
			{
				if (player.site.equals(this.site))
				{
					scores.add((SteamPlayerScore)
							player.getScores(this.site)
						.get(player.getScores(this.site).size() - 1)
						);
				}
			}
			StringBuffer header = new StringBuffer();
			for (SteamPlayerScore score : scores)
			{
				header.append(score.toHeaderCSVString());
				if (score.getSkills().size() > numberOfRowsToWrite)
				{
					numberOfRowsToWrite = score.getSkills().size();
				}
			}

			writeBuffer.write("\n"+header.toString()+"\n");
			// find max size of the rows
			for (int row = 0; row < numberOfRowsToWrite; row++) 
			{
				StringBuffer rowText = new StringBuffer();
				for (SteamPlayerScore score : scores) 
				{
					rowText.append(score.toCSVString(row));
				}
				writeBuffer.write(rowText.toString()+"\n");
			}
			writeBuffer.flush();
		} 
		catch (IOException ex)
		{
			System.err.println("Error writing to buffer "+ex.getMessage());
		}
	}
}
