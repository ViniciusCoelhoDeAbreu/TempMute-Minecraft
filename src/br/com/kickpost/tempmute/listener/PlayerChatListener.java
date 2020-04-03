package br.com.kickpost.tempmute.listener;

import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import br.com.kickpost.tempmute.TempMute;

public class PlayerChatListener implements Listener {

	
	@EventHandler
  public void onChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		
		if(TempMute.getMuteManager().isMuted(player)) {
			e.setCancelled(true);
			player.sendMessage(ChatColor.RED + "You're TempMuted for " + TimeUnit.MILLISECONDS.toSeconds(TempMute.getMuteManager().getMutedTime(player)) + " seconds");
		}
	}
}
