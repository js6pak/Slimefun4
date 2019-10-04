package io.github.thebusybiscuit.slimefun4.api.blocks;

import java.util.Optional;

import com.google.gson.JsonObject;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;

public class SlimefunBlock {
	
	private BlockLocation location;
	private String id;
	private JsonObject data;
	private boolean dirty = false;
	
	private Optional<DirtyChestMenu> inventory;
	
	public SlimefunBlock(BlockLocation l, String id) {
		this(l, id, new JsonObject());
	}
	
	public SlimefunBlock(BlockLocation l, String id, JsonObject data) {
		this.location = l;
		this.id = id;
		this.data = data;
	}
	
	public void setID(String id) {
		if (id == null) throw new IllegalArgumentException("ID is not allowed to be null!");
		else this.id = id;
		
		markDirty();
	}
	
	public boolean isItem(String id) {
		return this.id.equals(id);
	}
	
	public boolean isItem(SlimefunItemStack item) {
		return item.getItemID().equals(id);
	}
	
	public boolean isItem(SlimefunItem item) {
		return item.getID().equals(id);
	}

	public void setLocation(BlockLocation newLocation) {
		this.location = newLocation;
		markDirty();
	}

	public void markDirty() {
		dirty = true;
	}
	
	public boolean isDirty() {
		return dirty;
	}
	
	public String getID() {
		return id;
	}
	
	public BlockLocation getLocation() {
		return location;
	}
	
	public Optional<DirtyChestMenu> getInventory() {
		return inventory;
	}
	
	@Override
	public String toString() {
		data.addProperty("id", id);
		return data.toString();
	}

}
