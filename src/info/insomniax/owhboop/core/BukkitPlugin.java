package info.insomniax.owhboop.core;

import info.insomniax.owhboop.vault.Permissions;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
	}
	
	public void onDisable()
	{
		this.saveConfig();
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
					return true;
				}
			}
		}
		return false;
	}

}
