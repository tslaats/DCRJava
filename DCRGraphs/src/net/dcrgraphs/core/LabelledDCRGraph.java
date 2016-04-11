package net.dcrgraphs.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LabelledDCRGraph extends DCRGraph {
	private HashMap<String, HashSet<String>> labels = new HashMap<String, HashSet<String>>();

	private void addLabel(String event, String label) {
		if (!labels.containsKey(label))
			labels.put(label, new HashSet<String>());
		labels.get(label).add(event);
	}	
	
	@Override
	public void addEvent(String event)
	{
		super.addEvent(event);
		addLabel(event, event);
	}
	
	public void addEvent(String event, String label)
	{
		super.addEvent(event);
		addLabel(event, label);
	}
	
	@Override
	public Boolean enabled(final DCRMarking marking, final String label) {
		for (String event : labels.get(label))
			if (super.enabled(marking, event))
				return true;		
		return false;		
	}
	
	@Override
	public void execute(final String event) {
		marking = this.execute(marking, event);		
	}	

	@Override
	public DCRMarking execute(final DCRMarking marking, final String label) {
		if (!labels.containsKey(label)) { return marking; }
		if (!enabled(marking, label)) { return marking; }		
		
		for (String event : labels.get(label))
		{
			if (super.enabled(marking, event))
				return super.execute(marking, event);			
		}
		
		// shouldn't get here anyway...
		return marking;		
	}
	
	@Override
	public void run(final List<String> trace) {
		marking = this.run(marking, trace);
	}		
	
	@Override
	public DCRMarking run(final DCRMarking marking, List<String> trace)
	{
		DCRMarking m = marking.clone();
		for (String l : trace)
		{
			if (!enabled(m, l))
				return null;
			else
				m = execute(m,l);
		}		
		return m;
	}	
	
}
