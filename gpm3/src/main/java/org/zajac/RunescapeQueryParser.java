package org.zajac;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class RunescapeQueryParser extends QueryParser 
{
	@Override
	protected List<List<String>> parseBodyText(String playerName, String bodyText) 
	{
		List<List<String>> stats = initStats();

    	String sectionOfInterest = bodyText.substring(bodyText.indexOf(playerName), bodyText.indexOf("Dungeoneering"));
    	StringTokenizer tokenizer = new StringTokenizer(sectionOfInterest, "\n");
    	List<String> tokens = new ArrayList<String>();
    	while (tokenizer.hasMoreTokens())
    	{
    		tokens.add(tokenizer.nextToken());
    	}
    	
    	int i=5;
    	while (i < tokens.size())
	    {
	    	if (tokens.get(i+1).indexOf("Not Ranked")>-1)
	    	{
		    	addStats(0, stats, tokens, i);
	    		stats.get(1).add("Not Ranked");
	    		stats.get(2).add("Not Ranked");
	    		stats.get(3).add("Not Ranked");
	    		i+=2;
	    	}
	    	else 
	    	{
				addStats(0, stats, tokens, i);
				addStats(1, stats, tokens, i + 1);
				addStats(2, stats, tokens, i + 2);
				addStats(3, stats, tokens, i + 3);
				i+=4;
			}
	    }
    	return stats;
	}
}
