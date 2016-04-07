package net.dcrgraphs.logchecker;
import java.util.HashSet;


import net.dcrgraphs.core.*;

public class DCRTemplateFactory {
	
	static DCRGraph init(HashSet<String> events, String eventA)
	{
		DCRGraph g = new DCRGraph();
		for(String e : events)
		{
			g.AddEvent(e);
			if (!e.equals(eventA))
				g.AddCondition(eventA, e);
		}
		g.marking = g.DefaultInitialMarking();		
		return g;
	}
	
	
	static DCRGraph existence(HashSet<String> events, String eventA)
	{
		DCRGraph g = new DCRGraph();
		for(String e : events)
			g.AddEvent(e);
		g.marking = g.DefaultInitialMarking();
		g.marking.pending.add(eventA);
		return g;
	}
	
	
	static DCRGraph absence(HashSet<String> events, String eventA)
	{
		DCRGraph g = new DCRGraph();
		for(String e : events)
			g.AddEvent(e);
		g.marking = g.DefaultInitialMarking();
		g.marking.included.remove(eventA);
		return g;
	}	
	
	
	static DCRGraph absence2(HashSet<String> events, String eventA)
	{
		DCRGraph g = new DCRGraph();
		for(String e : events)
			g.AddEvent(e);
		g.marking = g.DefaultInitialMarking();
		g.AddExclude(eventA, eventA);
		return g;
	}		
	

}
