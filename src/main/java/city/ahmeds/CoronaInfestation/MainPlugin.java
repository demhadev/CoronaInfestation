package city.ahmeds.CoronaInfestation;

import city.ahmeds.CoronaInfestation.listeners.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class MainPlugin extends JavaPlugin {
	private static MainPlugin instance;

	@Override
	public void onEnable() {
		instance = this;
		if (!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}
		File file = new File(getDataFolder(), "config.yml");
		if (!file.exists()) {
			getLogger().info("Config.yml not found, creating!");
			saveDefaultConfig();
		} else {
			getLogger().info("Config.yml found, loading!");
		}
		Bukkit.getPluginManager().registerEvents(new ChatListener(), this);

	}
	private void createConfig() {
		try {
			if (!getDataFolder().exists()) {
				getDataFolder().mkdirs();
			}
			File file = new File(getDataFolder(), "config.yml");
			if (!file.exists()) {
				getLogger().info("Config.yml not found, creating!");
				saveDefaultConfig();
			} else {
				getLogger().info("Config.yml found, loading!");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}


	@Override
	public void onDisable() {
		instance = null;
	}

	public static MainPlugin getInstance() {
		return instance;
	}
}
