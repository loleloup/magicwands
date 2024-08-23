package com.magicwands.item;

import com.magicwands.spell.ExplosionSpell;
import com.magicwands.spell.ThunderSpell;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModItems {
	
	public static final String MOD_ID = "magicwands";
	
	public static Item register(Item item, String id) {
		// Create the identifier for the item.
		Identifier itemID = Identifier.of(MOD_ID, id);

		// Register the item.
		Item registeredItem = Registry.register(Registries.ITEM, itemID, item);

		// Return the registered item!
		return registeredItem;
	}

	public static void initialize() {
	}

	public static final Item AMETHYST_POWDER = register(
		new Item(new Item.Settings()),
		"amethyst_powder"
	);
	
	public static final Item TESTER_WAND = register(
		new WandItem(WandMaterials.BLAZE, new Item.Settings(), new ThunderSpell()),
		"tester_wand"
	);
	

	public static final Item FIRE_WAND = register(
		new WandItem(WandMaterials.BLAZE, new Item.Settings(), new ExplosionSpell()),
		"explosion_wand"
	);
	
}