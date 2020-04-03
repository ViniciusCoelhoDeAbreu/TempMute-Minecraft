package br.com.kickpost.tempmute.storage.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.kickpost.tempmute.dto.MuteDTO;
import br.com.kickpost.tempmute.storage.MySQL;

public class MySQLManager {

	private MySQL mysql;
	
	public MySQLManager(MySQL mysql) {
		this.mysql = mysql;
	}
	
	public void createTable() {
		try {
			mysql.getPreparedStatement(
					"CREATE TABLE IF NOT EXISTS mute(id INT(10) NOT NULL AUTO_INCREMENT,  mutedplayer VARCHAR(40), time long, sender VARCHAR(40), PRIMARY KEY(id)) ")
					.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void mutePlayer(CommandSender sender, OfflinePlayer mutedPlayer, long time) {
		PreparedStatement preparedStatement = mysql.getPreparedStatement("INSERT INTO mute(mutedplayer, time, sender) VALUES(?,?,?)");
		
		try {
		preparedStatement.setString(1, mutedPlayer.getUniqueId().toString());
		preparedStatement.setLong(2, time);
		preparedStatement.setString(3, sender.getName());
		
		preparedStatement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void unmutePlayer(CommandSender sender, OfflinePlayer unmutePlayer) {
		PreparedStatement preparedStatement = mysql.getPreparedStatement("DELETE FROM mute WHERE mutedplayer=?");

		try {
			preparedStatement.setString(1, unmutePlayer.getUniqueId().toString());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("deprecation")
	public MuteDTO loadMute(Player player) {
	PreparedStatement preparedStatement = mysql.getPreparedStatement("SELECT mutedplayer,time,sender FROM mute WHERE mutedplayer=?");
		
		try {
		preparedStatement.setString(1, player.getUniqueId().toString());
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			MuteDTO muteDTO = new MuteDTO();
			
			muteDTO.setMutedPlayer(Bukkit.getOfflinePlayer(resultSet.getString("mutedplayer")));
			muteDTO.setTime(resultSet.getLong("time"));
			muteDTO.setSender(Bukkit.getPlayer(resultSet.getString("sender")));
			
			return muteDTO;
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
