package dnacraft.common;

import java.util.HashMap;
import java.util.Map;

public class Tally extends HashMap<Integer, Integer> {
	
	public Map.Entry<Integer, Integer> largest() {
		Map.Entry<Integer, Integer> maxEntry = null;
		for (Map.Entry<Integer, Integer> entry : entrySet())
		{
		    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
		    {
		        maxEntry = entry;
		    }
		}
		return maxEntry;
	}
	
	public double average() {
		int total = 0;
		int count = 0;
		for (Map.Entry<Integer, Integer> entry : entrySet())
		{
			count += entry.getValue();
			total += entry.getKey() * entry.getValue();
		}
		return (double)total / count;
	}

	public Map.Entry<Integer, Integer> randomWeighted() {
		return randomWeighted(1);
	}
	
	public Map.Entry<Integer, Integer> randomWeighted(double pow) {
		double totalWeight = 0;
		for (Map.Entry<Integer, Integer> entry : entrySet())
		{
			totalWeight += Math.pow(entry.getValue(), pow);
		}
		int randomIndex = -1;
		double random = Math.random() * totalWeight;
		for (Map.Entry<Integer, Integer> entry : entrySet())
		{
			random -= Math.pow(entry.getValue(), pow);
		    if (random <= 0.0d)
		    {
		        return entry;
		    }
		}
		return null;
	}

	public void increment(int key) {
		int val = 0;
		if (containsKey(key)) {
			val = get(key);
		}
		put(key, val + 1);
	}
	
	public String toString() {
		String str = "";
		String[] parts = new String[size()];
		int i = 0;
		for (Map.Entry<Integer, Integer> entry : entrySet()) {
			parts[i] = entry.getKey() + ":"  + entry.getValue();
			i++;
		}
		i = 0;
		StringBuilder sb = new StringBuilder();
        for(i = 0; i < parts.length-1; i++)
            sb.append(parts[i] + ",");
        return sb.toString() + parts[i];
	}
}
