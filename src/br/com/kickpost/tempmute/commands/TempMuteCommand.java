package br.com.kickpost.tempmute.commands;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import br.com.kickpost.tempmute.TempMute;
import br.com.kickpost.tempmute.utils.Utils;

public class TempMuteCommand implements CommandExecutor {
	
/**
 * Tempmute 
 * Permission is in plugin.yml
 */
	private final String COMMAND_SINTAX = new String("/tempmute <player> <time>");
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tempmute")) {
			
			if (args.length < 2) {
				sender.sendMessage(ChatColor.RED + COMMAND_SINTAX);
				return true;
			}
			
			if(Utils.getPlayer(args[0], sender) != null && Utils.getTime(args[1], sender) != 0) {
				OfflinePlayer mutedPlayer = Utils.getPlayer(args[0], sender);
				long time = Utils.getTime(args[1], sender) + System.currentTimeMillis();
				
				TempMute.getMuteManager().mute(sender, mutedPlayer, time);
				sender.sendMessage(ChatColor.GREEN + mutedPlayer.getName() + " has been TempMuted.");
			}
		}
		return false;
	}
}
