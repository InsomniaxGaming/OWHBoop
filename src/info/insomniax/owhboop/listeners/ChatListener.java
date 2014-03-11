package info.insomniax.owhboop.listeners;

import info.insomniax.owhboop.core.OWHBoop;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener{
	
	/**Regex Wildcard*/
	private String wild = "(.*)";
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e)
	{
		if(e.isCancelled())
			return;
		
		String message = e.getMessage().toLowerCase();
		
		
		for(String censor : OWHBoop.getCensored())
		{
			if(message.matches(wild+censor+wild))
			{
				message.replace(censor, "boop");
			}
		}
		
		e.setMessage(message);
	}

}
