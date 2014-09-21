package org.zajac;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;


public abstract class QueryParser 
{
	 protected abstract List<List<String>> parseBodyText(String playerName, String bodyText); 
	 
	protected void addStats(int index, List<List<String>> stats, List<String> tokens, int i) 
	{
		if (i < tokens.size())
		{
			stats.get(index).add(tokens.get(i));
		}
		else
		{
			stats.get(index).add("NA");
		}
	}
	
	public List<List<String>> queryAndParseUrl(String playerURL,
			String playerName) 
	{		
		URL url;
	    try 
	    { 
		    url = new URL(playerURL);
		    HtmlCleaner cleaner = new HtmlCleaner();
		    TagNode node = cleaner.clean(url);
		    List<? extends TagNode> bodyNodes = node.getElementListByName("body", true);
		    //List<TagNode> bodyNodes = node.getElementListByName("body", true);
		    TagNode bodyNode = null;
		    if (bodyNodes == null || bodyNodes.size() < 1)
		    {
		    	System.out.println("NO BODY NODES FOUND, SKIPPING...");
		    }
		    if (bodyNodes != null && bodyNodes.size() > 1)
		    {
		    	System.out.println("MULTIPLE BODY NODES FOUND, ANALYZING Zeroeth Node");
		    }
	    	bodyNode = bodyNodes.get(0);
	    	CharSequence bodyText = bodyNode.getText();
	    	return parseBodyText(playerName, bodyText.toString());
	    } 
	    catch (MalformedURLException e) 
	    {
	    	System.out.println("MalformedURLException "+e.getMessage());
	    }
	    catch (IOException ex)
	    {
	    	System.out.println("IOException = "+ex.getMessage());
	    }
	    return initStats();
	}
	
	protected List<List<String>> initStats()
	{
		List<List<String>> stats = new ArrayList<List<String>>();
		stats.add(new ArrayList<String>());
		stats.add(new ArrayList<String>());
		stats.add(new ArrayList<String>());
		stats.add(new ArrayList<String>());
		return stats;
	}

}
