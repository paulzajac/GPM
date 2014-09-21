package org.zajac;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RunescapeUsageWriter extends UsageWriter 
{	
	public void writeOutput(List<PlayerToMonitor> players)  
	{	
		try 
		{
			// Collect the scores and write the header
			List<RunescapePlayerScore> runescapeScores = new ArrayList<RunescapePlayerScore>();
			for (PlayerToMonitor player : players) 
			{
				if (player.site.equals(site))
				{
					runescapeScores.add((RunescapePlayerScore)
							player.getScores(site)
						.get(player.getScores(site).size() - 1)
						);
				}
			}
			StringBuffer header = new StringBuffer();
			for (RunescapePlayerScore score : runescapeScores)
			{
				header.append(score.toHeaderCSVString());
			}

			writeBuffer.write("\n"+header.toString()+"\n");
			int numberOfRowsToWrite = runescapeScores.get(0).getSkills().size();
			for (int row = 0; row < numberOfRowsToWrite; row++) 
			{
				StringBuffer rowText = new StringBuffer();
				for (RunescapePlayerScore score : runescapeScores) 
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
