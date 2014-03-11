package info.insomniax.owhboop.core;

import java.util.ArrayList;
import java.util.List;

public class OWHBoop {
	
	BukkitPlugin plugin;
	
	private static List<String> CENSOR_LIST = new ArrayList<String>();
	
	public OWHBoop(BukkitPlugin instance)
	{
		plugin = instance;
	}
	
	public static List<String> getCensored()
	{
		return CENSOR_LIST;
	}
	
	public static void addCensor(String censor)
	{
		CENSOR_LIST.add(censor);
	}
	
	public static void removeCensor(int index)
	{
		CENSOR_LIST.remove(index);
	}
	
	public static void removeCensor(String censor)
	{
		int index = -1;
		
		for(int i = 0; i < CENSOR_LIST.size(); i++)
		{
			if(CENSOR_LIST.get(i).equalsIgnoreCase(censor))
			{
				index = i;
			}
		}
		
		if(index > -1)
			removeCensor(index);
	}
	
	public static void setCensored(List<String> censored)
	{
		CENSOR_LIST = censored;
	}

}
