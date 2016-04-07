package net.dcrgraphs.logchecker;
import org.deckfour.xes.model.XTrace;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.deckfour.xes.extension.std.XConceptExtension;
import net.dcrgraphs.core.*;

public class DCRLogChecker {
	public static DCRGraph emptyGraph(XTrace t)
	{
		DCRGraph g = new DCRGraph();
		for(XEvent event :t)
		{
			String activityName = XConceptExtension.instance().extractName(event);
			g.AddEvent(activityName);
		}
		return g;
	}		
	
	public static DCRGraph emptyGraph(XLog log)
	{
		DCRGraph g = new DCRGraph();
		for(XTrace trace :log)
			for(XEvent event :trace)
			{
				String activityName = XConceptExtension.instance().extractName(event);
				g.AddEvent(activityName);
			}
		return g;
	}		
	
	public static HashSet<String> eventSet(XLog log)
	{
		HashSet<String> result = new HashSet<String>();
		for(XTrace trace :log)
			for(XEvent event :trace)
			{
				String activityName = XConceptExtension.instance().extractName(event);
				result.add(activityName);
			}		
		return result;
	}
	
	public static boolean validate(XTrace trace, DCRGraph graph, final DCRMarking marking)
	{
		List<String> eventTrace = new LinkedList<String>();
		for(XEvent event :trace)
		{
			String activityName = XConceptExtension.instance().extractName(event);
			//System.out.println(activityName);
			eventTrace.add(activityName);
		}		
		//System.out.println(g.Run(m, eventTrace).toString());
		return (graph.Run(marking, eventTrace) != null);
	}
	
	
	public static boolean acceptingTrace(XTrace trace, DCRGraph graph, final DCRMarking marking)
	{
		List<String> eventTrace = new LinkedList<String>();
		for(XEvent event :trace)
		{
			String activityName = XConceptExtension.instance().extractName(event);
			eventTrace.add(activityName);
		}
		//System.out.println(g.Run(m, eventTrace).toString());
		return (graph.Run(marking, eventTrace) != null && graph.Run(marking, eventTrace).IsAccepting());
	}	
}
