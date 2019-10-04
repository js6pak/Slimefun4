package io.github.thebusybiscuit.slimefun4.api.chunks;

import com.google.gson.JsonObject;

public class SlimefunChunk {
	
	private ChunkLocation location;
	private JsonObject data;
	private boolean dirty = false;
	
	public SlimefunChunk(ChunkLocation l) {
		this(l, new JsonObject());
	}
	
	public SlimefunChunk(ChunkLocation l, JsonObject data) {
		this.location = l;
		this.data = data;
	}

	public void markDirty() {
		dirty = true;
	}
	
	public boolean isDirty() {
		return dirty;
	}
	
	public ChunkLocation getLocation() {
		return location;
	}
	
	@Override
	public String toString() {
		return data.toString();
	}

}
