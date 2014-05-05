package info.insomniax.owhboop.core;

import java.util.List;

import info.insomniax.owhboop.listeners.ChatListener;
import info.insomniax.owhboop.vault.Permissions;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This class acts as a bridge between the plugin and the server software. When possible, anything that involves
 * referencing bukkit should have to pass through this plugin to make for optimal portability.
 * */
public class BukkitPlugin extends JavaPlugin
{
	
	Permissions p;
	
	public void onEnable()
	{
		p = new Permissions(this);
		p.setupPermissions();
		
		this.getServer().getPluginManager().registerEvents(new ChatListener(this), this);
		
		initializeConfig();
	}
	
	public void onDisable()
	{
		this.saveCensored();
		this.saveExceptions();
		
		this.saveConfig();
	}
	
	public void initializeConfig()
	{
		List<String> configCensorList = this.getConfig().getStringList("OWH.Boop.Censored");
		List<String> configExceptionList = this.getConfig().getStringList("OWH.Boop.Exceptions");
		
		if(!configCensorList.isEmpty())
			OWHBoop.setCensored(configCensorList);
		if(!configExceptionList.isEmpty())
			OWHBoop.setExceptions(configExceptionList);
	}
	
	public void saveCensored()
	{
		this.getConfig().set("OWH.Boop.Censored", OWHBoop.getCensored());
	}
	
	public void saveExceptions()
	{
		this.getConfig().set("OWH.Boop.Exceptions", OWHBoop.getExceptions());
	}
	
	public static void sendMessage(String player, String message)
	{
		Bukkit.getPlayer(player).sendMessage(message);
	}
	
	public static void broadcast(String message)
	{
		Bukkit.broadcastMessage(message);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(p.has(sender, cmd, true))
		{
			if(cmd.getName().equalsIgnoreCase("addcensor"))
			{
				if(args.length > 0)
				{
					OWHBoop.addCensor(StringUtils.join(args, " "));
					sendMessage(sender.getName(), "Censor added");
					
					return true;
				}
			}
			if(cmd.getName().equalsIgnoreCase("listcensors"))
			{
				for(int i = 0; i < OWHBoop.getCensored().size(); i++)
					sendMessage(sender.getName(), i + ": " + OWHBoop.getCensored().get(i));
				
				return true;
			}
		}
		if(p.has(sender, cmd, false))
		{
			if(cmd.getName().equalsIgnoreCase("togglecensor"))
			{
				if(OWHBoop.getExceptions().contains(sender.getName()))
					OWHBoop.removeException(sender.getName());
				else
					OWHBoop.addException(sender.getName());
				
				return true;
			}
		}
		return false;
	}

}
