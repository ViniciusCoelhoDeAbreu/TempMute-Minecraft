package br.com.kickpost.tempmute.runnable;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.kickpost.tempmute.TempMute;
import br.com.kickpost.tempmute.dto.MuteDTO;

public class MuteRunnable extends BukkitRunnable {

	public MuteRunnable(TempMute tempMute) {
		runTaskTimerAsynchronously(tempMute, 0L, 20L);
	}

	@Override
	public void run() {
		Iterator<Entry<UUID, MuteDTO>> iterator = TempMute.getMuteManager().getMuteMap().entrySet().iterator();
		
		while (iterator.hasNext()) {
			Entry<UUID, MuteDTO> entry = iterator.next();

			if (System.currentTimeMillis() >= entry.getValue().getTime()) {

				TempMute.getMySqlManager().unmutePlayer(Bukkit.getConsoleSender(), entry.getValue().getMutedPlayer());
				iterator.remove();

				if (Bukkit.getPlayer(entry.getKey()).isOnline()) {
					Bukkit.getPlayer(entry.getKey()).sendMessage(ChatColor.GREEN + "You're unmuted");
				}
			}
		}
	}

}
