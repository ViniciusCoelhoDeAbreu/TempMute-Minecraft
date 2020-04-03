package br.com.kickpost.tempmute;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.kickpost.tempmute.commands.TempMuteCommand;
import br.com.kickpost.tempmute.commands.UnmuteCommand;
import br.com.kickpost.tempmute.listener.PlayerChatListener;
import br.com.kickpost.tempmute.listener.PlayerJoinListener;
import br.com.kickpost.tempmute.listener.PlayerQuitListener;
import br.com.kickpost.tempmute.manager.MuteManager;
import br.com.kickpost.tempmute.runnable.MuteRunnable;
import br.com.kickpost.tempmute.storage.MySQL;
import br.com.kickpost.tempmute.storage.manager.MySQLManager;

public class TempMute extends JavaPlugin {

	private static MySQLManager mySqlManager;
	private static MuteManager muteManager;

	@Override
	public void onEnable() {
		initialize();
		registerCommands();
		registerListeners();
	}

	private void registerCommands() {
		getCommand("tempmute").setExecutor(new TempMuteCommand());
		getCommand("unmute").setExecutor(new UnmuteCommand());
	}

	private void registerListeners() {
		PluginManager pluginManager = Bukkit.getPluginManager();

		pluginManager.registerEvents(new PlayerChatListener(), this);
		pluginManager.registerEvents(new PlayerJoinListener(), this);
		pluginManager.registerEvents(new PlayerQuitListener(), this);
	}

	private void initialize() {
		muteManager = new MuteManager();

		mySqlManager = new MySQLManager(new MySQL("root", "", "localhost", "kickpost"));
		mySqlManager.createTable();

		new MuteRunnable(this);
	}

	public static final TempMute getInstance() {
		return TempMute.getPlugin(TempMute.class);
	}

	public static final MuteManager getMuteManager() {
		return muteManager;
	}

	public static final MySQLManager getMySqlManager() {
		return mySqlManager;
	}

}
