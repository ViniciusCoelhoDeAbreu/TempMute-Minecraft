package br.com.kickpost.tempmute.dto;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

public class MuteDTO {

	private CommandSender sender;
	private OfflinePlayer mutedPlayer;
	private long time;
	
	public MuteDTO() {
		
	}

	public CommandSender getSender() {
		return sender;
	}

	public void setSender(CommandSender sender) {
		this.sender = sender;
	}

	public OfflinePlayer getMutedPlayer() {
		return mutedPlayer;
	}

	public void setMutedPlayer(OfflinePlayer mutedPlayer) {
		this.mutedPlayer = mutedPlayer;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
	
	
}
