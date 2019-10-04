package me.mrCookieSlime.Slimefun.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import io.github.thebusybiscuit.slimefun4.api.researches.PlayerProfile;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;

public class PlayerQuitListener implements Listener {

	public PlayerQuitListener(SlimefunPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onDisconnect(PlayerQuitEvent e) {
		SlimefunPlugin.getUtilities().guideHistory.remove(e.getPlayer().getUniqueId());
		
		if (PlayerProfile.isLoaded(e.getPlayer().getUniqueId())) {
			PlayerProfile.get(e.getPlayer()).markForDeletion();
		}
	}

}
