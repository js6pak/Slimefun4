package io.github.thebusybiscuit.slimefun4.api.chunks;

import java.util.Objects;

import org.bukkit.Chunk;

public final class ChunkLocation {
	
	private final int x;
	private final int z;
	
	public ChunkLocation(Chunk chunk) {
		this(chunk.getX(), chunk.getZ());
	}
	
	public ChunkLocation(int x, int z) {
		this.x = x;
		this.z = z;
	}
	
	public int getX() {
		return x;
	}
	
	public int getZ() {
		return z;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ChunkLocation) {
			ChunkLocation l = (ChunkLocation) obj;
			return l.getX() == x && l.getZ() == z;
		}
		else return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, z);
	}
	
	@Override
	public String toString() {
		return "[" + x + " | " + z + "]";
	}

}
