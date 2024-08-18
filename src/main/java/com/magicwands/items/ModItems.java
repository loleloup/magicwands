package com.magicwands.items;

import net.minecraft.item.Item;
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
}