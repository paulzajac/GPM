package org.zajac;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SteamFriendsQueryParser extends QueryParser 
{
	@Override
	protected List<List<String>> parseBodyText(String playerName, String bodyText) 
	{
		List<List<String>> stats = initStats();

		int startSubString = bodyText.indexOf("Friends");
		int endSubString = bodyText.substring(startSubString).indexOf("View all")+startSubString;
		if (endSubString > bodyText.length() || endSubString <startSubString)
		{
			endSubString = bodyText.substring(startSubString).indexOf("Steam Profile")+startSubString;
		}
    	String sectionOfInterest = bodyText.substring(startSubString, endSubString);
    	StringTokenizer tokenizer = new StringTokenizer(sectionOfInterest, "\n");
    	List<String> tokens = new ArrayList<String>();
    	while (tokenizer.hasMoreTokens())
    	{
    		String nextToken = tokenizer.nextToken();
    		if (nextToken.trim().length() > 0 && !nextToken.trim().equals("Friends"))
    		{
    			tokens.add(nextToken.trim());
    		}
    	}
    	
    	int i=0;
    	while (i < tokens.size())
	    {
    		addStats(0, stats, tokens, i);
    		addStats(1, stats, tokens, i+1);
    		i+=2;
	    }
    	return stats;
	}
	
	@Override
	protected void addStats(int index, List<List<String>> stats, List<String> tokens, int i) 
	{
		if (i < tokens.size())
		{
			String addString = tokens.get(i);
			int fsIndex = addString.indexOf("Friends since");
			if (fsIndex > 0)
			{
				addString = addString.substring(0, fsIndex).trim();
			}
			stats.get(index).add(addString);
		}
		else
		{
			stats.get(index).add("NA");
		}
	}
	

}
