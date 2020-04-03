package br.com.kickpost.tempmute.manager;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.kickpost.tempmute.TempMute;
import br.com.kickpost.tempmute.dto.MuteDTO;

public class MuteManager {

	private final HashMap<UUID, MuteDTO> MUTE_BY_PLAYER = new HashMap<>();
	
	public MuteManager() {
	}
	
	public void add(Player player, MuteDTO mute) {
		MUTE_BY_PLAYER.put(player.getUniqueId(), mute);
	}
	public void remove(Player player) {
		MUTE_BY_PLAYER.remove(player.getUniqueId());
	}
	
	public void mute(CommandSender sender, OfflinePlayer mutedPlayer, long muteTime) {

		MuteDTO mute = new MuteDTO();

		mute.setTime(muteTime);
		mute.setMutedPlayer(mutedPlayer);
		mute.setSender(sender);

		MUTE_BY_PLAYER.put(mutedPlayer.getUniqueId(), mute);
		TempMute.getMySqlManager().mutePlayer(sender, mutedPlayer, muteTime);
	}

	public boolean unmute(CommandSender sender, OfflinePlayer unmutedPlayer) {
		if (MUTE_BY_PLAYER.containsKey(unmutedPlayer.getUniqueId())) {
			
			MUTE_BY_PLAYER.remove(unmutedPlayer.getUniqueId());
			TempMute.getMySqlManager().unmutePlayer(sender, unmutedPlayer);
			
			return true;
		}
		return false;
	}

	public boolean isMuted(Player player) {
		return MUTE_BY_PLAYER.containsKey(player.getUniqueId()) && MUTE_BY_PLAYER.get(player.getUniqueId()).getTime() > System.currentTimeMillis();
	}
	public long getMutedTime(Player player) {
		return MUTE_BY_PLAYER.get(player.getUniqueId()).getTime() - System.currentTimeMillis();
	}

	public HashMap<UUID, MuteDTO> getMuteMap() {
		return MUTE_BY_PLAYER;
	}
}
