package org.zajac;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public abstract class UsageWriter 
{
	protected String outputFilename;
	protected FileWriter writer;
	protected BufferedWriter writeBuffer;
	protected SiteType site;

	public void initialize(SiteType site, String outputFilename) throws IOException 
	{
		this.site = site;
		this.outputFilename = outputFilename;
		File file = new File(outputFilename);
		int index = 0;
		while (file.exists())
		{
			outputFilename +="."+index+".txt";
			file = new File(outputFilename);
		}
		writer = new FileWriter(outputFilename);
		writeBuffer = new BufferedWriter(writer);
	}
	
	public void closeWriter() throws IOException
	{
		writeBuffer.close();
	}

	public abstract void writeOutput(List<PlayerToMonitor> players);
}
