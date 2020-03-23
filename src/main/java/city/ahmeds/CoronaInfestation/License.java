package city.ahmeds.CoronaInfestation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class License {

	private String pluginName;
	private String code;
	private JavaPlugin plugin;

	public License(JavaPlugin plugin, String pluginName, String code) {
		this.plugin = plugin;
		this.code = code;
		this.pluginName = pluginName;
	}

	public License(JavaPlugin plugin, String pluginName, int code) {
		this.plugin = plugin;
		this.code = String.valueOf(code);
		this.pluginName = pluginName;
	}

	public boolean check() {
		try {
			String url = "https://license.bghddevelopment.com/dev/admin/api.php?plugin=" + this.pluginName + "&license=" + this.code;
			URL obj = new URL(url);

			HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");

			InputStream inputStream = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

			if (!url.startsWith("https://license.bghddevelopment.com")) return false;

			boolean passed = false;

			String line;
			while ((line = reader.readLine()) != null) {
				passed = line.contains("true");
			}

			reader.close();
			inputStream.close();

			if (!passed) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&cLicense for &a" + this.plugin.getDescription().getName() +
								" v" + this.plugin.getDescription().getVersion() + " &ccouldn't be passed. " +
								"&cAre you using latest version of it? &eContact plugin developer if you think this is an issue."));
				Bukkit.getPluginManager().disablePlugin(this.plugin);
			}
			return passed;
		} catch (Exception ignored) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&cLicense for &a" + this.plugin.getDescription().getName() +
							" v" + this.plugin.getDescription().getVersion() + " &ccouldn't be passed. " +
							"&cIt seems that you're not online. Are you?"));
			Bukkit.getPluginManager().disablePlugin(this.plugin);
			return false;
		}
	}
}
