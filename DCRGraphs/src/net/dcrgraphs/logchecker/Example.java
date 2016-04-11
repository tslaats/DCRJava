package net.dcrgraphs.logchecker;
import org.deckfour.xes.model.impl.*;

import java.util.Arrays;
import java.util.LinkedList;

import org.deckfour.xes.extension.std.XExtendedEvent;

import net.dcrgraphs.core.*;

public class Example {
	public static void main(String [ ] args)
	{
		LinkedList<String> test = new LinkedList<String>(Arrays.asList("A", "B", "A", "C"));		
		XTraceImpl trace = new XTraceImpl(new XAttributeMapImpl());
		
		for (String e : test)
		{
			XExtendedEvent event = new XExtendedEvent(new XEventImpl());
			event.setName(e);		
			trace.add(event);
		}
		// Creates a graph with the events from the log and no relations
		DCRGraph graph = DCRLogChecker.emptyGraph(trace);
		
		// Creates a marking for the graph with no events executed or pending and all events included. 
		DCRMarking marking = graph.defaultInitialMarking();
		
		graph.addResponse("A", "C");
		graph.addMilestone("B", "C");
		graph.addCondition("A", "B");		
		
		System.out.println("Checking for graph:");
		System.out.println(graph.toString());
		
		if (DCRLogChecker.validate(trace, graph, marking))
			System.out.println("Valid Trace");
		else
			System.out.println("Invalid Trace");
		
		if (DCRLogChecker.acceptingTrace(trace, graph, marking))
			System.out.println("Accepting Trace");
		else
			System.out.println("Non-accepting Trace");		


		graph.addResponse("C", "A");
		
		System.out.println("Checking for graph:");
		System.out.println(graph.toString());
		
		if (DCRLogChecker.validate(trace, graph, marking))
			System.out.println("Valid Trace");
		else
			System.out.println("Invalid Trace");
		
		if (DCRLogChecker.acceptingTrace(trace, graph, marking))
			System.out.println("Accepting Trace");
		else
			System.out.println("Non-accepting Trace");
		
		
		
		graph.addExclude("C", "A");
		
		System.out.println("Checking for graph:");
		System.out.println(graph.toString());
		
		if (DCRLogChecker.validate(trace, graph, marking))
			System.out.println("Valid Trace");
		else
			System.out.println("Invalid Trace");
		
		if (DCRLogChecker.acceptingTrace(trace, graph, marking))
			System.out.println("Accepting Trace");
		else
			System.out.println("Non-accepting Trace");
		
		graph.addInclude("C", "A");
		
		System.out.println("Checking for graph:");
		System.out.println(graph.toString());
		
		if (DCRLogChecker.validate(trace, graph, marking))
			System.out.println("Valid Trace");
		else
			System.out.println("Invalid Trace");
		
		if (DCRLogChecker.acceptingTrace(trace, graph, marking))
			System.out.println("Accepting Trace");
		else
			System.out.println("Non-accepting Trace");		
	
		
		graph.addCondition("C", "A");
		
		System.out.println("Checking for graph:");
		System.out.println(graph.toString());
		
		if (DCRLogChecker.validate(trace, graph, marking))
			System.out.println("Valid Trace");
		else
			System.out.println("Invalid Trace");
		
		if (DCRLogChecker.acceptingTrace(trace, graph, marking))
			System.out.println("Accepting Trace");
		else
			System.out.println("Non-accepting Trace");				
		
	}
}
