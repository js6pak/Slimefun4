package me.mrCookieSlime.Slimefun.Objects.SlimefunItem;

import me.mrCookieSlime.Slimefun.Lists.Categories;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;

public class Jetpack extends DamagableChargableItem {
	
	private final double thrust;

	public Jetpack(SlimefunItemStack item, ItemStack[] recipe, double thrust) {
		super(Categories.TECH, item, RecipeType.ENHANCED_CRAFTING_TABLE, recipe, "Jetpack");
		this.thrust = thrust;
	}
	
	public double getThrust() {
		return thrust;
	}
	

}
