package org.zajac;


public class SteamPlayerScore extends PlayerScore 
{
	//@Override
	public String toCSVString(int row) 
	{
		String returnValue = "||";
		if (row < skills.size() && row < rank.size() )
		{
			returnValue = this.skills.get(row)+"|"+this.rank.get(row)+"|";
		}
		else
		{
			System.out.println ("Error with stats for player "+this.playerName+" row ="+row);
		}
		return returnValue;
	}

	public boolean equals(PlayerScore score)
	{
		if (score instanceof SteamPlayerScore)
		{
			return equals((SteamPlayerScore) score);
		}
		return false;			
	}
	
	public boolean equals (SteamPlayerScore score)
	{
		if (skills.size() != score.getSkills().size())
		{
			return false;
		}
		if (rank.size() != score.getRank().size())
		{
			return false;
		}
		if (level.size() != score.getLevel().size())
		{
			return false;
		}
		if (experience.size() != score.getExperience().size())
		{
			return false;
		}
		
		for (int i=0;i<skills.size();i++)
		{
			if (
				!skills.get(i).trim().equalsIgnoreCase(score.getSkills().get(i).trim()) ||
				!level.get(i).trim().equalsIgnoreCase(score.getLevel().get(i).trim()) ||
				!experience.get(i).trim().equalsIgnoreCase(score.getExperience().get(i).trim()))
			{
			    return false;		
			}
		}
		return true;
	}	
}
