package city.ahmeds.CoronaInfestation.listeners;

import city.ahmeds.CoronaInfestation.MainPlugin;
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
				e.setMessage(e.getMessage() + " " + (MainPlugin.getInstance().getConfig().getString("corona-message")));
			}
	}
}
