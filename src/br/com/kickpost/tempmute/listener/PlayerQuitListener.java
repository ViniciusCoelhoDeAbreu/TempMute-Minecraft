package br.com.kickpost.tempmute.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import br.com.kickpost.tempmute.TempMute;

public class PlayerQuitListener implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		TempMute.getMuteManager().remove(e.getPlayer());
	}
}
