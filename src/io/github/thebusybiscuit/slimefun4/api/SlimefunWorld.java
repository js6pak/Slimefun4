package io.github.thebusybiscuit.slimefun4.api;

import java.util.HashMap;
import java.util.Optional;

import org.bukkit.World;

import io.github.thebusybiscuit.cscorelib2.collections.OptionalMap;
import io.github.thebusybiscuit.slimefun4.api.blocks.BlockLocation;
import io.github.thebusybiscuit.slimefun4.api.blocks.SlimefunBlock;
import io.github.thebusybiscuit.slimefun4.api.chunks.ChunkLocation;
import io.github.thebusybiscuit.slimefun4.api.chunks.SlimefunChunk;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;

public class SlimefunWorld {
	
	private final OptionalMap<BlockLocation, SlimefunBlock> blocks;
	private final OptionalMap<ChunkLocation, SlimefunChunk> chunks;
	
	private SlimefunWorld(World world) {
		blocks = new OptionalMap<>(HashMap::new);
		chunks = new OptionalMap<>(HashMap::new);
		
		SlimefunPlugin.getUtilities().worlds.put(world.getUID(), this);
	}
	
	public Optional<SlimefunBlock> getBlockAt(BlockLocation l) {
		return blocks.get(l);
	}
	
	public Optional<SlimefunChunk> getChunkAt(ChunkLocation l) {
		return chunks.get(l);
	}
	
	public SlimefunBlock set(BlockLocation l, String id) {
		SlimefunBlock block = new SlimefunBlock(l, id);
		blocks.put(l, block);
		return block;
	}
	
	public void move(SlimefunBlock b, BlockLocation newLocation) {
		blocks.remove(b.getLocation());
		b.setLocation(newLocation);
		blocks.put(newLocation, b);
	}
	
	public void clear(BlockLocation l) {
		blocks.remove(l);
	}
	
	public static Optional<SlimefunWorld> getFromWorld(World world) {
		return SlimefunPlugin.getUtilities().worlds.get(world.getUID());
	}
	
	public static SlimefunWorld fromWorld(World world, boolean createIfNoneExists) {
		Optional<SlimefunWorld> optional = SlimefunPlugin.getUtilities().worlds.get(world.getUID());
		
		if (optional.isPresent()) {
			return optional.get();
		}
		else if (createIfNoneExists) {
			return new SlimefunWorld(world);
		}
		else {
			return null;
		}
	}

}
