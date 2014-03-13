package info.insomniax.owhboop.core;

import java.util.ArrayList;
import java.util.List;

public class OWHBoop {
	
	BukkitPlugin plugin;
	
	private static List<String> CENSOR_LIST = new ArrayList<String>();
	private static List<String> NO_CENSOR_PEEPS = new ArrayList<String>();
	
	public OWHBoop(BukkitPlugin instance)
	{
		plugin = instance;
	}
	
	public static List<String> getCensored()
	{
		return CENSOR_LIST;
	}
	
	public static List<String> getExceptions()
	{
		return NO_CENSOR_PEEPS;
	}
	
	public static void addCensor(String censor)
	{
		CENSOR_LIST.add(censor.toLowerCase());
	}
	
	public static void addException(String player)
	{
		NO_CENSOR_PEEPS.add(player);
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
	
	public static void removeException(String player)
	{
		NO_CENSOR_PEEPS.remove(player);
	}
	
	public static void setCensored(List<String> censored)
	{
		CENSOR_LIST = censored;
	}
	
	public static void setExceptions(List<String> dontCensor)
	{
		NO_CENSOR_PEEPS = dontCensor;
	}

}
