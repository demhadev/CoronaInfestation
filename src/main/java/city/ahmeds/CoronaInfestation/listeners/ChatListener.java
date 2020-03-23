package city.ahmeds.CoronaInfestation.listeners;

import city.ahmeds.CoronaInfestation.MainPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Random;

public class ChatListener implements Listener {
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Random random = new Random();
		int chance = random.nextInt(5);
			if (chance <= 50) {
				e.setCancelled(true);
				for (Player player : Bukkit.getOnlinePlayers()) {
					player.sendMessage(e.getMessage() + MainPlugin.getInstance().getConfig().getString("corona-message"));
				}
			}
	}
}
