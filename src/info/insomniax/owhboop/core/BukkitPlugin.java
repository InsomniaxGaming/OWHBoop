package info.insomniax.owhboop.core;

import info.insomniax.owhboop.vault.Permissions;

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
		//TODO setup plugin.yml
		
		p = new Permissions(this);
		p.setupPermissions();
	}
	
	public void onDisable()
	{
		this.saveConfig();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		return false;
	}

}
