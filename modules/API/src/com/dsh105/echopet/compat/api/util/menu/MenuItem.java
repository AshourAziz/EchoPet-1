/*
 * This file is part of EchoPet.
 *
 * EchoPet is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * EchoPet is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with EchoPet.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.dsh105.echopet.compat.api.util.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.dsh105.echopet.compat.api.util.Version;
import com.dsh105.echopet.compat.api.util.menu.DataMenu.DataMenuType;
import com.dsh105.echopet.compat.api.util.wrapper.WrappedMaterial;

public enum MenuItem{
	HORSE_TYPE("HAY_BLOCK", 1, (short) 0, DataMenuType.HORSE_TYPE, "Type", "Horse"),
	HORSE_VARIANT("LEASH", 1, (short) 0, DataMenuType.HORSE_VARIANT, "Variant", "Horse"),
	HORSE_MARKING("INK_SACK", 1, (short) 0, DataMenuType.HORSE_MARKING, "Marking", "Horse"),
	HORSE_ARMOUR("IRON_CHESTPLATE", 1, (short) 0, DataMenuType.HORSE_ARMOUR, "Armour", "Horse"),
	RABBIT_TYPE("RABBIT_HIDE", 1, (short) 0, DataMenuType.RABBIT_TYPE, "Bunny type", "Rabbit"),
	CHESTED("CHEST", 1, (short) 0, DataMenuType.BOOLEAN, "Chested", "Horse"),
	FIRE("FIREBALL", 1, (short) 0, DataMenuType.BOOLEAN, "Fire", "Blaze"),
	SADDLE("SADDLE", 1, (short) 0, DataMenuType.BOOLEAN, "Saddle", "Horse", "Pig"),
	SHEARED("SHEARS", 1, (short) 0, DataMenuType.BOOLEAN, "Sheared", "Sheep", "Snowman"),
	SCREAMING("ENDER_PEARL", 1, (short) 0, DataMenuType.BOOLEAN, "Screaming", "Enderman"),
	POTION("POTION", 1, (short) 0, DataMenuType.BOOLEAN, "Potion", "Witch"),
	SHIELD("GLASS", 1, (short) 0, DataMenuType.BOOLEAN, "Shield", "Wither"),
	POWER("BEACON", 1, (short) 0, DataMenuType.BOOLEAN, "Powered", "Creeper", "Vex"),
	SIZE("SLIME_BALL", 1, (short) 0, DataMenuType.SIZE, "Size", "Slime", "MagmaCube"),
	BABY("WHEAT", 1, (short) 0, DataMenuType.BOOLEAN, "Baby", "PigZombie", "Zombie", "Chicken", "Cow", "Horse", "MushroomCow", "Ocelot", "Pig", "PolarBear", "Sheep", "Wolf", "Villager"),
	CAT_TYPE("RAW_FISH", 1, (short) 0, DataMenuType.CAT_TYPE, "Cat Type", "Ocelot"),
	ANGRY("BONE", 1, (short) 0, DataMenuType.BOOLEAN, "Angry", "Wolf"),
	TAMED("BONE", 1, (short) 0, DataMenuType.BOOLEAN, "Tamed", "Wolf"),
	ZOMBIE_PROFESSION("EMERALD", 1, (short) 0, DataMenuType.ZOMBIE_PROFESSION, "Profession", "Zombie"),
	ELDER("SEA_LANTERN", 1, (short) 0, DataMenuType.BOOLEAN, "Elder", new Version("1.10-R1"), "Guardian"),
	COLOR("WOOL", 1, (short) 0, DataMenuType.COLOR, "Color", "Sheep", "Wolf"),
	PROFESSION("IRON_AXE", 1, (short) 0, DataMenuType.PROFESSION, "Profession", "Villager", "Zombie"),
	STANDING_UP("RAW_FISH", 1, (short) 0, DataMenuType.BOOLEAN, "Standing Up", "PolarBear"),
	SKELETON_TYPE("BONE", 1, (short) 0, DataMenuType.SKELETON_TYPE, "Skeleton Type", "Skeleton"),
	LLAMA_VARIANT("LEATHER", 1, (short) 0, DataMenuType.LLAMA_VARIANT, "Llama Variant", "Llama"),
	LLAMA_COLOR("WOOL", 1, (short) 0, DataMenuType.LLAMA_COLOR, "Color", "Llama"),
	OPEN("TRAP_DOOR", 1, (short) 0, DataMenuType.BOOLEAN, "Open", "Shulker"),
	PARROT_VARIANT("WOOL", 1, (short) 4, DataMenuType.PARROT_VARIANT, "Variant", "Parrot"),
	LEFT_SHOULDER("SADDLE", 1, (short) 0, DataMenuType.BOOLEAN, "Left Shoulder", "Put a pet on your Left Shoulder"),
	RIGHT_SHOULDER("SADDLE", 1, (short) 0, DataMenuType.BOOLEAN, "Right Shoulder", "Put a pet on your Right Shoulder"),
	RIDE("CARROT_STICK", 1, (short) 0, DataMenuType.BOOLEAN, "Ride Pet", "Control your pet."),
	HAT("IRON_HELMET", 1, (short) 0, DataMenuType.BOOLEAN, "Hat Pet", "Wear your pet on your head.");

	private WrappedMaterial wrappedMaterial;
	private String name;
	private int amount;
	private List<String> lore;
	private short data;
	private DataMenuType menuType;
	private Version version;

	MenuItem(String materialName, int amount, short data, DataMenuType menuType, String name, String... lore){
		this(materialName, amount, data, menuType, name, new Version(), lore);
	}

	MenuItem(String materialName, int amount, short data, DataMenuType menuType, String name, Version version, String... lore){
		this.wrappedMaterial = new WrappedMaterial(materialName);
		this.name = name;
		this.amount = amount;
		this.data = data;
		List<String> list = new ArrayList<String>();
		list.addAll(Arrays.asList(lore));
		this.lore = list;
		this.menuType = menuType;
		this.version = version;
	}

	public ItemStack getItem(){
		if(this.wrappedMaterial.get() == null){ // Spigot 1.8 item that's not supported?
			return null;
		}
		ItemStack i = new ItemStack(this.wrappedMaterial.get(), this.amount, this.data);
		ItemMeta meta = i.getItemMeta();
		meta.setDisplayName(ChatColor.RED + this.name);
		meta.setLore(this.lore);
		i.setItemMeta(meta);
		return i;
	}

	public ItemStack getBoolean(boolean flag){
		if(this.wrappedMaterial.get() == null){ // Spigot 1.8 item that's not supported?
			return null;
		}
		ItemStack i = new ItemStack(this.wrappedMaterial.get(), this.amount, this.data);
		ItemMeta meta = i.getItemMeta();
		meta.setDisplayName(ChatColor.RED + this.name + (flag ? ChatColor.GREEN + " [TOGGLE ON]" : ChatColor.YELLOW + " [TOGGLE OFF]"));
		meta.setLore(this.lore);
		i.setItemMeta(meta);
		return i;
	}

	public DataMenuType getMenuType(){
		return this.menuType;
	}

	public boolean isSupported(){
		return version.isSupported(new Version());
	}
}