package net.dcrgraphs.logchecker;
import org.deckfour.xes.model.XTrace;
import org.deckfour.xes.model.XEvent;

import java.util.LinkedList;
import java.util.List;

import org.deckfour.xes.extension.std.XConceptExtension;
import net.dcrgraphs.core.*;

public class DCRLogChecker {
	public static DCRGraph EmptyGraph(XTrace t)
	{
		DCRGraph g = new DCRGraph();
		for(XEvent event :t)
		{
			String activityName = XConceptExtension.instance().extractName(event);
			g.AddEvent(activityName);
		}
		return g;
	}	
	
	public static boolean Validate(XTrace t, DCRGraph g, final DCRMarking m)
	{
		List<String> eventTrace = new LinkedList<String>();
		for(XEvent event :t)
		{
			String activityName = XConceptExtension.instance().extractName(event);
			//System.out.println(activityName);
			eventTrace.add(activityName);
		}		
		//System.out.println(g.Run(m, eventTrace).toString());
		return (g.Run(m, eventTrace) != null);
	}
	
	
	public static boolean AcceptingTrace(XTrace t, DCRGraph g, final DCRMarking m)
	{
		List<String> eventTrace = new LinkedList<String>();
		for(XEvent event :t)
		{
			String activityName = XConceptExtension.instance().extractName(event);
			eventTrace.add(activityName);
		}
		//System.out.println(g.Run(m, eventTrace).toString());
		return (g.Run(m, eventTrace) != null && g.Run(m, eventTrace).IsAccepting());
	}	
}
