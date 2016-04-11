package net.dcrgraphs.logchecker;
import java.util.HashSet;


import net.dcrgraphs.core.*;

public class DCRTemplateFactory {
	
	static DCRGraph init(HashSet<String> events, String eventA)
	{
		DCRGraph g = new DCRGraph();
		for(String e : events)
		{
			g.addEvent(e);
			if (!e.equals(eventA))
				g.addCondition(eventA, e);
		}
		g.marking = g.defaultInitialMarking();		
		return g;
	}
	
	
	static DCRGraph existence(HashSet<String> events, String eventA)
	{
		DCRGraph g = new DCRGraph();
		for(String e : events)
			g.addEvent(e);
		g.marking = g.defaultInitialMarking();
		g.marking.pending.add(eventA);
		return g;
	}
	
	
	static DCRGraph absence(HashSet<String> events, String eventA)
	{
		DCRGraph g = new DCRGraph();
		for(String e : events)
			g.addEvent(e);
		g.marking = g.defaultInitialMarking();
		g.marking.included.remove(eventA);
		return g;
	}	
	
	
	static DCRGraph absence2(HashSet<String> events, String eventA)
	{
		DCRGraph g = new DCRGraph();
		for(String e : events)
			g.addEvent(e);
		g.marking = g.defaultInitialMarking();
		g.addExclude(eventA, eventA);
		return g;
	}		
	
	
	static DCRGraph exactly1(HashSet<String> events, String eventA)
	{
		DCRGraph g = new DCRGraph();
		for(String e : events)
			g.addEvent(e);
		g.marking = g.defaultInitialMarking();
		g.addExclude(eventA, eventA);
		g.marking.pending.add(eventA);
		return g;
	}	
	
	static DCRGraph choice(HashSet<String> events, String eventA, String eventB)
	{
		DCRGraph g = new DCRGraph();
		for(String e : events)
			g.addEvent(e);
		String blockingEvent = g.newEvent();			
		g.marking = g.defaultInitialMarking();
		g.marking.pending.add(blockingEvent);
		g.addCondition(blockingEvent, blockingEvent);		
		g.addExclude(eventA, blockingEvent);
		g.addExclude(eventB, blockingEvent);		
		return g;
	}		
	 
	static DCRGraph exclusiveChoice(HashSet<String> events, String eventA, String eventB)
	{
		DCRGraph g = new DCRGraph();
		for(String e : events)
			g.addEvent(e);
		String blockingEvent = g.newEvent();			
		g.marking = g.defaultInitialMarking();
		g.marking.pending.add(blockingEvent);
		g.addCondition(blockingEvent, blockingEvent);		
		g.addExclude(eventA, blockingEvent);
		g.addExclude(eventB, blockingEvent);		
		g.addExclude(eventB, eventA);		
		g.addExclude(eventA, eventB);		
		return g;
	}
	
	static DCRGraph response(HashSet<String> events, String eventA, String eventB)
	{
		DCRGraph g = new DCRGraph();
		for(String e : events)
			g.addEvent(e);
		g.marking = g.defaultInitialMarking();
		g.addResponse(eventA, eventB);
		return g;
	}	
	
	static DCRGraph precedence(HashSet<String> events, String eventA, String eventB)
	{
		DCRGraph g = new DCRGraph();
		for(String e : events)
			g.addEvent(e);
		g.marking = g.defaultInitialMarking();
		g.addCondition(eventA, eventB);
		return g;
	}
	
	
	static DCRGraph succession(HashSet<String> events, String eventA, String eventB)
	{
		DCRGraph g = new DCRGraph();
		for(String e : events)
			g.addEvent(e);
		g.marking = g.defaultInitialMarking();
		g.addCondition(eventA, eventB);
		g.addResponse(eventA, eventB);
		return g;
	}
	
	static DCRGraph alternateResponse(HashSet<String> events, String eventA, String eventB)
	{
		DCRGraph g = new DCRGraph();
		for(String e : events)
			g.addEvent(e);
		g.marking = g.defaultInitialMarking();
		g.addResponse(eventA, eventB);
		g.addExclude(eventA, eventA);
		g.addInclude(eventB, eventA);
		return g;
	}
	 
	static DCRGraph AlternatePrecedence(HashSet<String> events, String eventA, String eventB)
	{
		DCRGraph g = new DCRGraph();
		for(String e : events)
			g.addEvent(e);
		g.marking = g.defaultInitialMarking();
		g.marking.included.remove(eventB);
		g.addInclude(eventA, eventB);
		g.addExclude(eventB, eventB);
		return g;
	}	 
			
	
	static DCRGraph alternateSuccession(HashSet<String> events, String eventA, String eventB)
	{
		DCRGraph g = new DCRGraph();
		for(String e : events)
			g.addEvent(e);
		g.marking = g.defaultInitialMarking();
		g.marking.included.remove(eventB);
		g.addInclude(eventA, eventB);
		g.addExclude(eventB, eventB);
		g.addResponse(eventA, eventB);
		g.addExclude(eventA, eventA);
		g.addInclude(eventB, eventA);
		return g;
	}
	
	
	static DCRGraph notCoExistence(HashSet<String> events, String eventA, String eventB)
	{
		DCRGraph g = new DCRGraph();
		for(String e : events)
			g.addEvent(e);
		g.marking = g.defaultInitialMarking();
		g.addExclude(eventA, eventB);
		g.addExclude(eventB, eventA);
		return g;
	}		
	
	static DCRGraph notSuccession(HashSet<String> events, String eventA, String eventB)
	{
		DCRGraph g = new DCRGraph();
		for(String e : events)
			g.addEvent(e);
		g.marking = g.defaultInitialMarking();
		g.addExclude(eventA, eventB);		
		return g;
	}
}
