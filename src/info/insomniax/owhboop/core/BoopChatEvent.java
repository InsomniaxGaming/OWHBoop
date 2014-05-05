package info.insomniax.owhboop.core;

import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * This class exists purely for the sake of keeping track of the events fired by this plugin.
 * */
public class BoopChatEvent extends AsyncPlayerChatEvent{

	public BoopChatEvent(boolean async, Player who, String message,
			Set<Player> players) {
		super(async, who, message, players);
		// TODO Auto-generated constructor stub
	}
}
