package org.zajac;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public abstract class PlayerScore 
{
	protected List<String> skills;
	protected List<String> rank;
	protected List<String> level;
	protected List<String> experience;
	protected String playerName;
	protected int collectionNumber;
	protected Calendar collectionTime;
	protected String errorString = null;

	// TODO - Rename this method.
	public void initialize(String playerName, List<List<String>> stats, int collectionNumber, Calendar collectionTime)
	{
		parseStats(stats);
		setPlayerName(playerName);
		setCollectionNumber(collectionNumber);
		setCollectionTime(collectionTime);		
	}
	
	protected void parseStats(List<List<String>> stats) 
	{
		skills = stats.get(0);
		rank = stats.get(1);
		level = stats.get(2);
		experience = stats.get(3);
	}
	
	public abstract boolean equals (PlayerScore score);
	
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	public List<String> getRank() {
		return rank;
	}
	public void setRank(List<String> rank) {
		this.rank = rank;
	}
	public List<String> getLevel() {
		return level;
	}
	public void setLevel(List<String> level) {
		this.level = level;
	}
	public List<String> getExperience() {
		return experience;
	}
	public void setExperience(List<String> experience) {
		this.experience = experience;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public int getCollectionNumber() {
		return collectionNumber;
	}
	
	public void setCollectionNumber(int collectionNumber)
	{
		this.collectionNumber = collectionNumber;
	}
	public Calendar getCollectionTime() {
		return collectionTime;
	}
	public void setCollectionTime(Calendar time) {
		collectionTime = time;
	}

	/**
	 * Format is Player Name,Date,Skill,Rank,Level,XP
	 * @return
	 */
	public String toHeaderCSVString() 
	{
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		return playerName+"|"+formatter.format(collectionTime.getTime())+"|||";
	}

	/**
	 * Format is Player Name,Date,Skill,Rank,Level,XP
	 * @return
	 */
	public abstract String toCSVString(int row); 
}
