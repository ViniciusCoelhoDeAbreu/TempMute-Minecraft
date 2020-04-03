package br.com.kickpost.tempmute.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import br.com.kickpost.tempmute.TempMute;
import br.com.kickpost.tempmute.utils.Utils;

public class UnmuteCommand implements CommandExecutor {

	private final String COMMAND_SINTAX = new String("/unmute <player>");

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (cmd.getName().equalsIgnoreCase("unmute")) {

			if (args.length < 1) {
				sender.sendMessage(ChatColor.RED + COMMAND_SINTAX);
				return true;
			}

			if (Utils.getPlayer(args[0], sender) != null) {
				boolean unmuted = TempMute.getMuteManager().unmute(sender, Utils.getPlayer(args[0], sender));

				if (unmuted) {
					sender.sendMessage(ChatColor.GREEN + "User unmuted.");
				} else {
					sender.sendMessage(ChatColor.RED + "That player is not muted yet.");
				}
			}
		}
		return false;
	}
}
