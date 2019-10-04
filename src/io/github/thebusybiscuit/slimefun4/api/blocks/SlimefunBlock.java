package io.github.thebusybiscuit.slimefun4.api.blocks;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntFunction;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import io.github.thebusybiscuit.cscorelib2.collections.OptionalMap;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;

public class SlimefunBlock {
	
	private BlockLocation location;
	private String id;
	private JsonObject data;
	private boolean dirty = false;
	
	private OptionalMap<String, Object> cache;
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

	// Setters
	public void setString(String key, String value) {
		data.addProperty(key, value);
	}
	
	public void setInt(String key, int value) {
		data.addProperty(key, value);
	}
	
	public void setBoolean(String key, boolean value) {
		data.addProperty(key, value);
	}
	
	public void setFloat(String key, float value) {
		data.addProperty(key, value);
	}
	
	public void setStringArray(String key, String[] array) {
		JsonArray json = new JsonArray();
		for (String value: array) json.add(value);
		data.add(key, json);
	}
	
	public void setINtArray(String key, int[] array) {
		JsonArray json = new JsonArray();
		for (int value: array) json.add(value);
		data.add(key, json);
	}
	
	public void setBooleanArray(String key, boolean[] array) {
		JsonArray json = new JsonArray();
		for (boolean value: array) json.add(value);
		data.add(key, json);
	}
	
	public void setFloatArray(String key, float[] array) {
		JsonArray json = new JsonArray();
		for (float value: array) json.add(value);
		data.add(key, json);
	}

	// Getters
	public Optional<String> getString(String key) {
		return getPrimitive(key, json -> json.getAsString());
	}
	
	public Optional<Integer> getInt(String key) {
		return getPrimitive(key, json -> (int) json.getAsNumber());
	}
	
	public Optional<Boolean> getBoolean(String key) {
		return getPrimitive(key, json -> json.getAsBoolean());
	}
	
	public Optional<Float> getFloat(String key) {
		return getPrimitive(key, json -> (float) json.getAsNumber());
	}
	
	public Optional<String[]> getStringArray(String key) {
		return getArray(key, String[]::new, json -> json.getAsString());
	}
	
	public Optional<Integer[]> getIntArray(String key) {
		return getArray(key, Integer[]::new, json -> (int) json.getAsNumber());
	}
	
	public Optional<Boolean[]> getBooleanArray(String key) {
		return getArray(key, Boolean[]::new, json -> json.getAsBoolean());
	}
	
	public Optional<Float[]> getFloatArray(String key) {
		return getArray(key, Float[]::new, json -> (float) json.getAsNumber());
	}
	
	public <T> Optional<T> getCachedObject(String key, Class<T> c) {
		if (cache == null) return Optional.empty();
		return cache.get(key).map(c::cast);
	}
	
	protected <T> Optional<T[]> getArray(String key, IntFunction<T[]> constructor, Function<JsonElement, T> getter) {
		JsonElement element = data.get(key);
		
		if (element == null || !(element instanceof JsonArray)) {
			return Optional.empty();
		}
		else {
			JsonArray json = (JsonArray) element;
			T[] array = constructor.apply(json.size());
			
			for (int i = 0; i < array.length; i++) {
				array[i] = getter.apply(json.get(i));
			}
			
			return Optional.of(array);
		}
	}
	
	protected <T> Optional<T> getPrimitive(String key, Function<JsonElement, T> getter) {
		JsonElement element = data.get(key);
		
		if (element == null || !(element instanceof JsonPrimitive)) {
			return Optional.empty();
		}
		else {
			return Optional.of(getter.apply(element));
		}
	}

}
