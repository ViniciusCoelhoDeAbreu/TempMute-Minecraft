package br.com.kickpost.tempmute.utils;

import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

public class Utils {

	/**
	 * That method is using OfflinePlayer because if Server's owner wants to mute any player which is offline can mute
	 * 
	 * @param argument - Player name argument
	 * @param sender -   CommandSender
	 * @return - Null if is not a player or the OfflinePlayer by argument
	 */
	@SuppressWarnings("deprecation")
	public static OfflinePlayer getPlayer(String argument, CommandSender sender) {
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(argument);
		
		if(offlinePlayer == null) {
			sender.sendMessage(ChatColor.RED  + "Argument is not a player.");
		}
		
		return offlinePlayer;
	}
	
	public static long getTime(String argument, CommandSender sender) {
		String lowerArgument = argument.toLowerCase();
		long stringTime = TimeUtils.getTimeByString(lowerArgument);
		TimeUnit stringTimeUnit = TimeUtils.getTimeUnitByString(lowerArgument);

		if (stringTime > 0 && stringTimeUnit != null) {
			return stringTimeUnit.toMillis(stringTime);
		} else {
			sender.sendMessage(ChatColor.RED + "Invalid time");
			return 0;
		}
	}
}
