package org.zajac;


public class RunescapePlayerScore extends PlayerScore
{
	/**
	 * Format is Player Name,Date,Skill,Rank,Level,XP
	 * @return
	 */
	@Override
	public String toCSVString(int row) 
	{
		if (row >= skills.size() || row >= rank.size() || row >= level.size() || row >= experience.size() )
		{
			System.out.println ("Error with stats for player "+this.playerName+" row ="+row);
			return "||||";
		}
		return this.skills.get(row)+"|"+this.rank.get(row)+"|"+this.level.get(row)+"|"+this.experience.get(row)+"|";
	}

	public boolean equals (RunescapePlayerScore score)
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

	@Override
	public boolean equals(PlayerScore score) 
	{
		if (score instanceof RunescapePlayerScore)
		{
			return equals((RunescapePlayerScore) score);
		}
		return false;
	}	
}
