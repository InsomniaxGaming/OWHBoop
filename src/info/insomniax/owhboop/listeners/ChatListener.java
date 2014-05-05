package info.insomniax.owhboop.listeners;

import java.util.HashSet;
import java.util.Set;

import info.insomniax.owhboop.core.BoopChatEvent;
import info.insomniax.owhboop.core.BukkitPlugin;
import info.insomniax.owhboop.core.OWHBoop;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener{
	
	BukkitPlugin plugin;
	
	String lastSender;
	Set<Player> exceptions;
	String censoredMessage;
	String message;
	
	public ChatListener(BukkitPlugin instance)
	{
		plugin = instance;
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent e)
	{
		if(e.isCancelled())
			return;

		//Only do anything to this event if it wasn't fired by the plugin.
		if(!(e instanceof BoopChatEvent))
		{
			message = e.getMessage();
			censoredMessage = e.getMessage();
			
			exceptions = new HashSet<Player>();
			Set<Player> normals = new HashSet<Player>();
			
			
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
			for(Player p : exceptions)
				e.getRecipients().remove(p);
			
			System.out.println(exceptions);
			System.out.println(normals);
			
			plugin.getServer().getScheduler().runTaskAsynchronously(plugin, new Runnable()
			{
			    @Override
			    public void run()
			    {
			        AsyncPlayerChatEvent event = new BoopChatEvent(true, Bukkit.getOnlinePlayers()[0], message, exceptions);
			        plugin.getServer().getPluginManager().callEvent(event);
			 
			        if (event.isCancelled())
			            return;
			        
			        String formattedMessage = String.format(event.getFormat(), event.getPlayer().getDisplayName(), event.getMessage());
			        for(Player p : exceptions)
			        	p.sendMessage(formattedMessage);
			        
			        
			    }
			});
			
			censoredMessage = censoredMessage.toLowerCase();
			
			for(String censor : OWHBoop.getCensored())
			{
				if(censoredMessage.contains(censor))
				{
					censoredMessage = censoredMessage.replaceAll(censor, "boop");
				}
			}
			
			e.setMessage(censoredMessage);
			
		}
	}
}
