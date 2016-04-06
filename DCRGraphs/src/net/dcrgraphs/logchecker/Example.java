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
		DCRGraph graph = DCRLogChecker.EmptyGraph(trace);
		
		// Creates a marking for the graph with no events executed or pending and all events included. 
		DCRMarking marking = graph.DefaultInitialMarking();
		
		graph.AddResponse("A", "C");
		graph.AddMilestone("B", "C");
		graph.AddCondition("A", "B");		
		
		System.out.println("Checking for graph:");
		System.out.println(graph.toString());
		
		if (DCRLogChecker.Validate(trace, graph, marking))
			System.out.println("Valid Trace");
		else
			System.out.println("Invalid Trace");
		
		if (DCRLogChecker.AcceptingTrace(trace, graph, marking))
			System.out.println("Accepting Trace");
		else
			System.out.println("Non-accepting Trace");		


		graph.AddResponse("C", "A");
		
		System.out.println("Checking for graph:");
		System.out.println(graph.toString());
		
		if (DCRLogChecker.Validate(trace, graph, marking))
			System.out.println("Valid Trace");
		else
			System.out.println("Invalid Trace");
		
		if (DCRLogChecker.AcceptingTrace(trace, graph, marking))
			System.out.println("Accepting Trace");
		else
			System.out.println("Non-accepting Trace");
		
		
		
		graph.AddExclude("C", "A");
		
		System.out.println("Checking for graph:");
		System.out.println(graph.toString());
		
		if (DCRLogChecker.Validate(trace, graph, marking))
			System.out.println("Valid Trace");
		else
			System.out.println("Invalid Trace");
		
		if (DCRLogChecker.AcceptingTrace(trace, graph, marking))
			System.out.println("Accepting Trace");
		else
			System.out.println("Non-accepting Trace");
		
		graph.AddInclude("C", "A");
		
		System.out.println("Checking for graph:");
		System.out.println(graph.toString());
		
		if (DCRLogChecker.Validate(trace, graph, marking))
			System.out.println("Valid Trace");
		else
			System.out.println("Invalid Trace");
		
		if (DCRLogChecker.AcceptingTrace(trace, graph, marking))
			System.out.println("Accepting Trace");
		else
			System.out.println("Non-accepting Trace");		
	
		
		graph.AddCondition("C", "A");
		
		System.out.println("Checking for graph:");
		System.out.println(graph.toString());
		
		if (DCRLogChecker.Validate(trace, graph, marking))
			System.out.println("Valid Trace");
		else
			System.out.println("Invalid Trace");
		
		if (DCRLogChecker.AcceptingTrace(trace, graph, marking))
			System.out.println("Accepting Trace");
		else
			System.out.println("Non-accepting Trace");				
		
	}
}
