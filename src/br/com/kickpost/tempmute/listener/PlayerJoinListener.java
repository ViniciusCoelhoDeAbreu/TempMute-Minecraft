package br.com.kickpost.tempmute.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import br.com.kickpost.tempmute.TempMute;
import br.com.kickpost.tempmute.dto.MuteDTO;

public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		MuteDTO mute = TempMute.getMySqlManager().loadMute(player);

		if (mute != null) {
			TempMute.getMuteManager().add(player, mute);
		}
	}
}
