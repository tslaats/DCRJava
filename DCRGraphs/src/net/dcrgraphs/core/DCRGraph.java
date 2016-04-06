package net.dcrgraphs.core; 

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class DCRGraph {
	private HashSet<String> events = new HashSet<String>();	
	private HashMap<String, HashSet<String>> conditionsFor = new HashMap<String, HashSet<String>>();
	private HashMap<String, HashSet<String>> milestonesFor = new HashMap<String, HashSet<String>>();	
	private HashMap<String, HashSet<String>> responsesTo = new HashMap<String, HashSet<String>>();
	private HashMap<String, HashSet<String>> excludesTo = new HashMap<String, HashSet<String>>();
	private HashMap<String, HashSet<String>> includesTo = new HashMap<String, HashSet<String>>();
	
	public void AddEvent(String e)
	{
		events.add(e);
		conditionsFor.put(e, new HashSet<String>());
		milestonesFor.put(e, new HashSet<String>());
		responsesTo.put(e, new HashSet<String>());
		excludesTo.put(e, new HashSet<String>());
		includesTo.put(e, new HashSet<String>());		
	}
	
	
	public void AddCondition(String src, String trg)
	{
		conditionsFor.get(trg).add(src);
	}
	
	public void AddMilestone(String src, String trg)
	{
		milestonesFor.get(trg).add(src);
	}
	
	public void AddResponse(String src, String trg)
	{
		responsesTo.get(src).add(trg);
	}	
	
	public void AddExclude(String src, String trg)
	{
		excludesTo.get(src).add(trg);
	}	
	
	public void AddInclude(String src, String trg)
	{
		includesTo.get(src).add(trg);
	}		
	
	

	public Boolean Enabled(final DCRMarking m, final String e) {
		if (!events.contains(e)) { return true; }
		// check included
		if (!m.included.contains(e)) { return false;
		// check conditions
		}

		// if (!m.executed.containsAll(RelationsFor(conditions, e)))
		final Set<String> inccon = new HashSet<String>(conditionsFor.get(e));
		inccon.retainAll(m.included);
		if (!m.executed.containsAll(inccon)) { return false; }

		// check milestones

		final Set<String> incmil = new HashSet<String>(milestonesFor.get(e));
		incmil.retainAll(m.included);

		for (final String p : m.pending) {
			if (incmil.contains(p)) { return false; }
		}
		return true;
	}

	public DCRMarking Execute(final DCRMarking m, final String e) {
		if (!events.contains(e)) { return m; }

		if (!Enabled(m, e)) { return m; }

		// DCRMarking result = new DCRMarking();

		// for (String s : m.executed)

		m.executed.add(e);

		m.pending.remove(e);
		m.pending.addAll(responsesTo.get(e));
		m.included.removeAll(excludesTo.get(e));
		m.included.addAll(includesTo.get(e));

		// TODO
		return m;
	}
	
	public DCRMarking Run(final DCRMarking marking, List<String> trace)
	{
		DCRMarking m = marking.clone();
		for (String e : trace)
		{
			if (!Enabled(m, e))
				return null;
			else
				m = Execute(m,e);
		}		
		return m;
	}

	public DCRMarking DefaultInitialMarking() {
		final DCRMarking result = new DCRMarking();
		for (final String e : events) {
			result.included.add(e);
		}
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder();
		final String NEW_LINE = System.getProperty("line.separator");

		result.append(this.getClass().getName() + " DCR Graph {" + NEW_LINE);
		result.append(" Events: ");
		for (final String e : events) {
			result.append(e + "; ");
		}
		result.append(NEW_LINE);

		result.append(" Conditions: ");
		for (Entry<String, HashSet<String>> r : conditionsFor.entrySet()) {
			String trg = r.getKey();
			for (String src : r.getValue())
				result.append(src + " ->* " + trg + ";");
		}
		result.append(NEW_LINE);
		

		result.append(" Repsonses: ");
		for (Entry<String, HashSet<String>> r : responsesTo.entrySet()) {
			String src = r.getKey();
			for (String trg : r.getValue())
				result.append(src + " *-> " + trg + ";");
		}
		result.append(NEW_LINE);		
		
		result.append(" Exclusions: ");
		for (Entry<String, HashSet<String>> r : excludesTo.entrySet()) {
			String src = r.getKey();
			for (String trg : r.getValue())
				result.append(src + " ->% " + trg + ";");
		}
		result.append(NEW_LINE);
		
		result.append(" Inclusions: ");
		for (Entry<String, HashSet<String>> r : includesTo.entrySet()) {
			String src = r.getKey();
			for (String trg : r.getValue())
				result.append(src + " ->+ " + trg + ";");
		}
		result.append(NEW_LINE);		
		
				
		result.append(" Milestones: ");
		for (Entry<String, HashSet<String>> r : milestonesFor.entrySet()) {
			String trg = r.getKey();
			for (String src : r.getValue())
				result.append(src + " -><> " + trg + ";");
		}
		result.append(NEW_LINE);		
		
		// Note that Collections and Maps also override toString
		// result.append(" RelationID: " + relationID.toString() + NEW_LINE);
		result.append("}");

		return result.toString();
	}
}
