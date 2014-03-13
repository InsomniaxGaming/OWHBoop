package info.insomniax.owhboop.listeners;

import java.util.HashSet;
import java.util.Set;

import info.insomniax.owhboop.core.AsyncBoopChatEvent;
import info.insomniax.owhboop.core.OWHBoop;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener{
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent e)
	{
		if(e.isCancelled())
			return;

		//Only do anything to this event if it wasn't fired by the plugin.
		if(!(e instanceof AsyncBoopChatEvent))
		{
			String message = e.getMessage().toLowerCase();		
			Set<Player> exceptions = new HashSet<Player>();
			Set<Player> normals = new HashSet<Player>();
			
			for(String censor : OWHBoop.getCensored())
			{
				if(message.contains(censor))
				{
					message = message.replaceAll(censor, "boop");
				}
			}
			
			for(Player p : Bukkit.getOnlinePlayers())
			{
				if(OWHBoop.getExceptions().contains(p.getName()))
				{
					exceptions.add(p);
				}
				else
				{
					normals.add(p);
				}
			}

			Bukkit.getPluginManager().callEvent(new AsyncBoopChatEvent(true, e.getPlayer(), e.getMessage(), exceptions));
			Bukkit.getPluginManager().callEvent(new AsyncBoopChatEvent(true, e.getPlayer(), message, normals));	
			
			e.setCancelled(true);
		}
	}
}
